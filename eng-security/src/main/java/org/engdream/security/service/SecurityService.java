package org.engdream.security.service;

import org.engdream.security.entity.Menu;
import org.engdream.sys.entity.User;

import java.util.List;

/**
 * Created by heyx on 2017/4/16.
 */
public interface SecurityService {
    void checkPaaword(String username, String password);
    List<Menu> findMenus(User user);
}
