package org.engdream.sys.mapper;

import org.engdream.sys.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.engdream.base.mapper.BaseLogicDeleteMapper;
/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author Heyx
 * @since 2017-04-03
 */
public interface UserMapper extends BaseMapper<User>,BaseLogicDeleteMapper<Long> {

}