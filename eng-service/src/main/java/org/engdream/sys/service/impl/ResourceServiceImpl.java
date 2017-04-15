package org.engdream.sys.service.impl;

import org.engdream.sys.entity.Resource;
import org.engdream.sys.mapper.ResourceMapper;
import org.engdream.sys.service.ResourceService;
import org.engdream.base.service.impl.BaseTreeableServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author Heyx
 * @since 2017-04-06
 */
@Service
public class ResourceServiceImpl extends BaseTreeableServiceImpl<Resource, Long> implements ResourceService {

    public ResourceMapper getResourceMapper(){
        return (ResourceMapper)baseMapper;
    }

}
