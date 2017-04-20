package org.engdream.sys.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import org.engdream.base.service.impl.BaseServiceImpl;
import org.engdream.sys.entity.User;
import org.engdream.sys.mapper.UserMapper;
import org.engdream.sys.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Heyx
 * @since 2017-04-05
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {
    private UserMapper getUserMapper(){
        return (UserMapper)baseMapper;
    }

    @Override
    public void markDelete(Long id){
        getUserMapper().markDelete(id);
    }

    @Override
    public User newUser(User user) {
        insert(user);
        return user;
    }

    @Override
    @Cacheable("user")
    public User findByUsername(String username) {
        return super.selectOne(new Wrapper<User>() {
            @Override
            public String getSqlSegment() {
                return String.format(" where username='%s'", username);
            }
        });
    }


}
