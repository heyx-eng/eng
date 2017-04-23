package org.engdream.sys.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.engdream.base.service.BaseService;
import org.engdream.sys.entity.User;
import org.engdream.sys.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* <p>
    *  服务类
    * </p>
*
* @author Heyx
* @since 2017-04-05
*/
@Service
public class UserService extends BaseService<User, Long> {

    private UserMapper getUserMapper(){
        return (UserMapper)baseMapper;
    }

    public void markDelete(Long id){
        getUserMapper().markDelete(id);
    }

    @Override
    public User save(User user) {
        super.save(user);
        return user;
    }

    public User findByUsername(String username) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("username", username);
        return super.findOne(wrapper);
    }

}
