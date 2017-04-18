package org.engdream.sys.service.impl;

import org.engdream.sys.entity.Permission;
import org.engdream.sys.mapper.PermissionMapper;
import org.engdream.sys.service.PermissionService;
import org.engdream.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Heyx
 * @since 2017-04-16
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Long> implements PermissionService {

    private PermissionMapper getPermissionMapper(){
        return (PermissionMapper)baseMapper;
    }

}
