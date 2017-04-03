package org.engdream.sys.service.impl;

import org.engdream.sys.entity.User;
import org.engdream.sys.mapper.UserMapper;
import org.engdream.sys.service.UserService;
import org.engdream.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author Heyx
 * @since 2017-04-03
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    public UserMapper getUserMapper(){
        return (UserMapper)baseMapper;
    }

    @Override
    public void markDelete(Long id){
        getUserMapper().markDelete(id);
    }
}
