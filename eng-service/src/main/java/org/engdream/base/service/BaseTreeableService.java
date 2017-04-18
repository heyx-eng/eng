package org.engdream.base.service;

import com.baomidou.mybatisplus.mapper.Wrapper;

import org.engdream.base.entity.BaseEntity;
import org.engdream.base.entity.Treeable;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface BaseTreeableService<M extends BaseEntity<ID> & Treeable<ID>, ID extends Serializable>
        extends BaseService<M, ID> {

    void deleteSelfAndChild(M m);

    void deleteSelfAndChild(List<M> mList);

    void appendChild(M parent, M child);

    int nextWeight(ID id);


    /**
     * 移动节点
     * 根节点不能移动
     *
     * @param source   源节点
     * @param target   目标节点
     * @param moveType 位置
     */
    void move(M source, M target, String moveType);



    /**
     * 查找目标节点及之后的兄弟  注意：值与越小 越排在前边
     *
     * @param parentIds
     * @param currentWeight
     * @return
     */
    List<M> findSelfAndNextSiblings(String parentIds, int currentWeight);


    /**
     * 查看与name模糊匹配的名称
     *
     * @param name
     * @return
     */
    Set<String> findNames(Wrapper<M> wrapper, String name, ID excludeId);


    /**
     * 查询子子孙孙
     *
     * @return
     */
    List<M> findChildren(List<M> parents, Wrapper<M> wrapper);

    List<M> findAllByName(Wrapper<M> wrapper, M excludeM);

    /**
     * 查找根和一级节点
     *
     * @return
     */
    List<M> findRootAndChild(Wrapper<M> wrapper);

    Set<ID> findAncestorIds(Iterable<ID> currentIds);

    Set<ID> findAncestorIds(ID currentId);

    /**
     * 递归查询祖先
     *
     * @param parentIds
     * @return
     */
    List<M> findAncestor(String parentIds);


    void addExcludeSearchFilter(Wrapper<M> wrapper, M excludeM);

	boolean hasChildren(ID id);

}
