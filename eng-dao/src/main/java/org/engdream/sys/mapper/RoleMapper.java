package org.engdream.sys.mapper;

import org.engdream.sys.entity.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.engdream.base.mapper.BaseLogicDeleteMapper;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Heyx
 * @since 2017-04-05
 */
public interface RoleMapper extends BaseMapper<Role>,BaseLogicDeleteMapper<Long> {

}