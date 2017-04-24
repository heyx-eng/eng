package org.engdream.sys.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.engdream.base.service.BaseTreeableService;
import org.engdream.sys.entity.Resource;
import org.engdream.sys.mapper.ResourceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* <p>
    * 资源 服务类
    * </p>
*
* @author Heyx
* @since 2017-04-06
*/
@Service
public class ResourceService extends BaseTreeableService<Resource, Long> {
    public ResourceMapper getResourceMapper(){
        return (ResourceMapper)baseMapper;
    }

    public List<Resource> findAllMenu() {
        EntityWrapper<Resource> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("available", 1).
                orderBy("parent_id", false).orderBy("weight", false);
        return findList(entityWrapper);
    }

}
