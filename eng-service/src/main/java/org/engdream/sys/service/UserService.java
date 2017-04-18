package org.engdream.sys.service;

import org.engdream.sys.entity.User;
import org.engdream.base.service.BaseService;

/**
* <p>
    *  服务类
    * </p>
*
* @author Heyx
* @since 2017-04-05
*/
public interface UserService extends BaseService<User, Long> {

    void markDelete(Long id);

    User newUser(User user);

    User findByUsername(String username);

}
