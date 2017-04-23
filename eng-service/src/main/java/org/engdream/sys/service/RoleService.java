package org.engdream.sys.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.engdream.base.service.BaseService;
import org.engdream.sys.entity.Role;
import org.engdream.sys.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* <p>
    *  服务类
    * </p>
*
* @author Heyx
* @since 2017-04-05
*/
@Service
public class RoleService extends BaseService<Role, Long> {
    private RoleMapper getRoleMapper(){
        return (RoleMapper)baseMapper;
    }

    public void markDelete(Long id){
        getRoleMapper().markDelete(id);
    }

    @Override
    public List<Role> findAll() {
        EntityWrapper<Role> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("available", 1);
        return getRoleMapper().selectList(entityWrapper);
    }
}
