package org.engdream.sys.service;

import org.engdream.sys.entity.User;
import org.engdream.base.service.BaseService;

/**
* <p>
    * 用户信息 服务类
    * </p>
*
* @author Heyx
* @since 2017-04-03
*/
public interface UserService extends BaseService<User, Long> {
    void markDelete(Long id);
}
