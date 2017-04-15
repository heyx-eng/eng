package org.engdream.sys.service;

import org.engdream.sys.entity.Role;
import org.engdream.base.service.BaseService;

/**
* <p>
    *  服务类
    * </p>
*
* @author Heyx
* @since 2017-04-05
*/
public interface RoleService extends BaseService<Role, Long> {
    void markDelete(Long id);
}
