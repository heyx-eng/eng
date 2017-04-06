package org.engdream.sys.mapper;

import org.engdream.sys.entity.Resource;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.engdream.base.mapper.BaseLogicDeleteMapper;
/**
 * <p>
 * 资源 Mapper 接口
 * </p>
 *
 * @author Heyx
 * @since 2017-04-05
 */
public interface ResourceMapper extends BaseMapper<Resource>,BaseLogicDeleteMapper<Long> {

}