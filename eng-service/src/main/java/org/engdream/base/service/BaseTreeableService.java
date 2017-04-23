package org.engdream.base.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.engdream.base.entity.BaseEntity;
import org.engdream.base.entity.Treeable;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class BaseTreeableService<M extends BaseEntity<ID> & Treeable<ID>, ID extends Serializable>
        extends BaseService<M, ID> {

    @Override
    public M save(M m) {
        if (m.getWeight() == null) {
            m.setWeight(nextWeight(m.getParentId()));
        }
        return super.save(m);
    }

    public void deleteSelfAndChild(M m) {
        EntityWrapper<M> wrapper = new EntityWrapper<>();
        wrapper.eq("id", m.getId()).or("parent_ids like {0}", m.makeSelfAsNewParentIds()+"%");
        baseMapper.delete(wrapper);
    }

    public void deleteSelfAndChild(List<M> mList) {
        for (M m : mList) {
            deleteSelfAndChild(m);
        }
    }

    public void appendChild(M parent, M child) {
        child.setParentId(parent.getId());
        child.setParentIds(parent.makeSelfAsNewParentIds());
        child.setWeight(nextWeight(parent.getId()));
        this.save(child);
    }

    public int nextWeight(ID id) {
        EntityWrapper<M> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id", id);
        return super.findCount(wrapper);
    }

    /**
     * 移动节点
     * 根节点不能移动
     *
     * @param source   源节点
     * @param target   目标节点
     * @param moveType 位置
     */
    public void move(M source, M target, String moveType) {
        if (source == null || target == null || source.isRoot()) { //根节点不能移动
            return;
        }

        //如果是相邻的兄弟 直接交换weight即可
        boolean isSibling = source.getParentId().equals(target.getParentId());
        boolean isNextOrPrevMoveType = "next".equals(moveType) || "prev".equals(moveType);
        if (isSibling && isNextOrPrevMoveType && Math.abs(source.getWeight() - target.getWeight()) == 1) {

            //无需移动
            if ("next".equals(moveType) && source.getWeight() > target.getWeight()) {
                return;
            }
            if ("prev".equals(moveType) && source.getWeight() < target.getWeight()) {
                return;
            }


            int sourceWeight = source.getWeight();
            source.setWeight(target.getWeight());
            target.setWeight(sourceWeight);
            return;
        }

        //移动到目标节点之后
        if ("next".equals(moveType)) {
            List<M> siblings = findSelfAndNextSiblings(target.getParentIds(), target.getWeight());
            siblings.remove(0);//把自己移除

            if (siblings.size() == 0) { //如果没有兄弟了 则直接把源的设置为目标即可
                int nextWeight = nextWeight(target.getParentId());
                updateSelftAndChild(source, target.getParentId(), target.getParentIds(), nextWeight);
                return;
            } else {
                moveType = "prev";
                target = siblings.get(0); //否则，相当于插入到实际目标节点下一个节点之前
            }
        }

        //移动到目标节点之前
        if ("prev".equals(moveType)) {

            List<M> siblings = findSelfAndNextSiblings(target.getParentIds(), target.getWeight());
            //兄弟节点中包含源节点
            if (siblings.contains(source)) {
                // 1 2 [3 source] 4
                siblings = siblings.subList(0, siblings.indexOf(source) + 1);
                int firstWeight = siblings.get(0).getWeight();
                for (int i = 0; i < siblings.size() - 1; i++) {
                    siblings.get(i).setWeight(siblings.get(i + 1).getWeight());
                }
                siblings.get(siblings.size() - 1).setWeight(firstWeight);
            } else {
                // 1 2 3 4  [5 new]
                int nextWeight = nextWeight(target.getParentId());
                int firstWeight = siblings.get(0).getWeight();
                for (int i = 0; i < siblings.size() - 1; i++) {
                    siblings.get(i).setWeight(siblings.get(i + 1).getWeight());
                }
                siblings.get(siblings.size() - 1).setWeight(nextWeight);
                source.setWeight(firstWeight);
                updateSelftAndChild(source, target.getParentId(), target.getParentIds(), source.getWeight());
            }

            return;
        }
        //否则作为最后孩子节点
        int nextWeight = nextWeight(target.getId());
        updateSelftAndChild(source, target.getId(), target.makeSelfAsNewParentIds(), nextWeight);
    }

    /**
     * 把源节点全部变更为目标节点
     *
     * @param source
     * @param newParentIds
     */
    private void updateSelftAndChild(M source, ID newParentId, String newParentIds, int newWeight) {
        String oldSourceChildrenParentIds = source.makeSelfAsNewParentIds();
        source.setParentId(newParentId);
        source.setParentIds(newParentIds);
        source.setWeight(newWeight);
        super.updateById(source);
        String newSourceChildrenParentIds = source.makeSelfAsNewParentIds();
        //todo
        //String.format("update %s set parentIds=(?1 || substring(parentIds, length(?2)+1)) where parentIds like concat(?2, %s)", entityName, "'%'");
        //repositoryHelper.batchUpdate(UPDATE_CHILDREN_PARENT_IDS_QL, newSourceChildrenParentIds, oldSourceChildrenParentIds);
    }

    /**
     * 查找目标节点及之后的兄弟  注意：值与越小 越排在前边
     *
     * @param parent_ids
     * @param currentWeight
     * @return
     */
    public List<M> findSelfAndNextSiblings(String parent_ids, int currentWeight) {
        EntityWrapper<M> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_ids", parent_ids).ge("weight", currentWeight).orderBy("weight", true);
        return findList(wrapper);
    }


    /**
     * 查看与name模糊匹配的名称
     *
     * @param name
     * @return
     */
    public Set<String> findNames(Wrapper<M> wrapper, String name, ID excludeId) {
        M excludeM = findById(excludeId);
        wrapper.addFilter("name like '%?%'", name);
        addExcludeSearchFilter(wrapper, excludeM);

        return Sets.newHashSet(
                Lists.transform(
                        findList(wrapper),
                        new Function<M, String>() {
                            @Override
                            public String apply(M input) {
                                return input.getName();
                            }
                        }
                )
        );

    }


    public List<M> findAllByName(Wrapper<M> wrapper, M excludeM) {
        addExcludeSearchFilter(wrapper, excludeM);
        return findList(wrapper);
    }


    public Set<ID> findAncestorIds(Iterable<ID> currentIds) {
        Set<ID> parents = Sets.newHashSet();
        for (ID currentId : currentIds) {
            parents.addAll(findAncestorIds(currentId));
        }
        return parents;
    }

    public boolean hasChildren(ID id){
        EntityWrapper<M> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id", id);
        return findCount(wrapper) > 0;
    }

    public void addExcludeSearchFilter(Wrapper<M> wrapper, M excludeM) {
        if (excludeM == null) {
            return;
        }
        wrapper.addFilter("id != ?", excludeM.getId());
        wrapper.addFilter("parent_ids not like {0}", excludeM.makeSelfAsNewParentIds()+"%");
    }

    public List<M> findChildren(List<M> parents, Wrapper<M> wrapper) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<M> findRootAndChild(Wrapper<M> wrapper) {
        // TODO Auto-generated method stub
        return null;
    }

    public Set<ID> findAncestorIds(ID currentId) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<M> findAncestor(String parentIds) {
        // TODO Auto-generated method stub
        return null;
    }

}
