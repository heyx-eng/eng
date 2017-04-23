package org.engdream.sys.service;

import org.engdream.base.service.BaseService;
import org.engdream.sys.entity.Permission;
import org.engdream.sys.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* <p>
    *  服务类
    * </p>
*
* @author Heyx
* @since 2017-04-16
*/
@Service
public class PermissionService extends BaseService<Permission, Long> {
    private PermissionMapper getPermissionMapper(){
        return (PermissionMapper)baseMapper;
    }
}
