package org.engdream.sys.service;

import org.engdream.sys.entity.Resource;
import org.engdream.base.service.BaseTreeableService;

/**
* <p>
    * 资源 服务类
    * </p>
*
* @author Heyx
* @since 2017-04-05
*/
public interface ResourceService extends BaseTreeableService<Resource, Long> {
    void markDelete(Long id);
}
