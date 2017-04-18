package org.engdream.sys.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import org.engdream.sys.entity.Role;
import org.engdream.sys.mapper.RoleMapper;
import org.engdream.sys.service.RoleService;
import org.engdream.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Heyx
 * @since 2017-04-05
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    public RoleMapper getRoleMapper(){
        return (RoleMapper)baseMapper;
    }

    @Override
    public void markDelete(Long id){
        getRoleMapper().markDelete(id);
    }

}
