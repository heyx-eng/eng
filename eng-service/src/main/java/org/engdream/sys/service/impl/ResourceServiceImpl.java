package org.engdream.sys.service.impl;

import org.engdream.sys.entity.Resource;
import org.engdream.sys.mapper.ResourceMapper;
import org.engdream.sys.service.ResourceService;

import java.util.List;
import java.util.Set;

import org.engdream.base.service.impl.BaseServiceImpl;
import org.engdream.base.service.impl.BaseTreeableServiceImpl;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author Heyx
 * @since 2017-04-05
 */
@Service
public class ResourceServiceImpl extends BaseTreeableServiceImpl<Resource, Long> implements ResourceService {

    public ResourceMapper getResourceMapper(){
        return (ResourceMapper)baseMapper;
    }

	@Override
	public List<Resource> findChildren(List<Resource> parents, Wrapper<Resource> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> findRootAndChild(Wrapper<Resource> wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Long> findAncestorIds(Long currentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> findAncestor(String parentIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void markDelete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
