package org.engdream.security.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.engdream.common.util.security.Md5Utils;
import org.engdream.security.entity.Menu;
import org.engdream.security.service.SecurityService;
import org.engdream.sys.entity.Resource;
import org.engdream.sys.entity.Role;
import org.engdream.sys.entity.User;
import org.engdream.sys.enums.ResourceType;
import org.engdream.sys.service.ResourceService;
import org.engdream.sys.service.RoleService;
import org.engdream.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by heyx on 2017/4/16.
 */
@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private UserService userService;
    
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleService roleService;
    @Override
    public void checkPaaword(String username, String password) {
        User user = userService.findByUsername(username);
        if(user == null){
            throw new UnknownAccountException();
        }
        if(user.getLocked()){
            throw new DisabledAccountException();
        }
        if(!matches(user, password)){
            throw new AuthenticationException();
        }
    }

    /**
     * 得到真实的资源标识  即 父亲:儿子
     * @param resource
     * @return
     */
    public String findActualResourceIdentity(Resource resource) {

        if(resource == null) {
            return null;
        }

        StringBuilder s = new StringBuilder(resource.getIdentity());

        boolean hasResourceIdentity = !StringUtils.isEmpty(resource.getIdentity());

        Resource parent = resourceService.selectById(resource.getParentId());
        while(parent != null) {
            if(!StringUtils.isEmpty(parent.getIdentity())) {
                s.insert(0, parent.getIdentity() + ":");
                hasResourceIdentity = true;
            }
            parent = resourceService.selectById(parent.getParentId());
        }

        //如果用户没有声明 资源标识  且父也没有，那么就为空
        if(!hasResourceIdentity) {
            return "";
        }


        //如果最后一个字符是: 因为不需要，所以删除之
        int length = s.length();
        if(length > 0 && s.lastIndexOf(":") == length - 1) {
            s.deleteCharAt(length - 1);
        }

        //如果有儿子 最后拼一个*
        boolean hasChildren = false;
        for(Resource r : resourceService.selectList(null)) {
            if(resource.getId().equals(r.getParentId())) {
                hasChildren = true;
                break;
            }
        }
        if(hasChildren) {
            s.append(":*");
        }

        return s.toString();
    }

    public List<Menu> findMenus(User user) {

        List<Resource> resources = resourceService.selectList(new Wrapper<Resource>() {
            @Override
            public String getSqlSegment() {
                return String.format(" where available=%s and type='%s' order by parent_id desc,weight desc",1, ResourceType.menu.name());
            }
        });

        Set<String> userPermissions = findUserPermissions(user);

        Iterator<Resource> iter = resources.iterator();
        while (iter.hasNext()) {
            if (!hasPermission(iter.next(), userPermissions)) {
                iter.remove();
            }
        }

        return convertToMenus(resources);
    }

    private Set<String> findUserPermissions(User user) {
        Set<String> perms = new HashSet<>();
        List<Role> roles = roleService.selectBatchIds(user.getRoleIds());
        for(Role role : roles){
            List<Resource> resourceList = resourceService.selectBatchIds(role.getResourceIds());
            for(Resource resource : resourceList){
                perms.add(findActualResourceIdentity(resource));
            }
        }
        return perms;
    }

    private boolean hasPermission(Resource resource, Set<String> userPermissions) {
        String actualResourceIdentity = findActualResourceIdentity(resource);
        if (StringUtils.isEmpty(actualResourceIdentity)) {
            return true;
        }

        for (String permission : userPermissions) {
            if (hasPermission(permission, actualResourceIdentity)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasPermission(String permission, String actualResourceIdentity) {

        String permissionResourceIdentity = permission.substring(0, permission.lastIndexOf(":"));

        if(permissionResourceIdentity.startsWith(actualResourceIdentity)) {
            return true;
        }

        //模式匹配
        WildcardPermission p1 = new WildcardPermission(permissionResourceIdentity);
        WildcardPermission p2 = new WildcardPermission(actualResourceIdentity);

        return p1.implies(p2) || p2.implies(p1);
    }

    public static List<Menu> convertToMenus(List<Resource> resources) {

        if (resources.size() == 0) {
            return Collections.EMPTY_LIST;
        }

        Menu root = convertToMenu(resources.remove(resources.size() - 1));

        recursiveMenu(root, resources);
        List<Menu> menus = root.getChildren();
        removeNoLeafMenu(menus);

        return menus;
    }

    private static void removeNoLeafMenu(List<Menu> menus) {
        if (menus.size() == 0) {
            return;
        }
        for (int i = menus.size() - 1; i >= 0; i--) {
            Menu m = menus.get(i);
            removeNoLeafMenu(m.getChildren());
            if (!m.isHasChildren() && StringUtils.isEmpty(m.getUrl())) {
                menus.remove(i);
            }
        }
    }

    private static void recursiveMenu(Menu menu, List<Resource> resources) {
        for (int i = resources.size() - 1; i >= 0; i--) {
            Resource resource = resources.get(i);
            if (resource.getParentId().equals(menu.getId())) {
                menu.getChildren().add(convertToMenu(resource));
                resources.remove(i);
            }
        }

        for (Menu subMenu : menu.getChildren()) {
            recursiveMenu(subMenu, resources);
        }
    }

    private static Menu convertToMenu(Resource resource) {
        return new Menu(resource.getId(), resource.getName(), resource.getIcon(), resource.getUrl());
    }

    private boolean matches(User user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getUsername(), newPassword, user.getSalt()));
    }

    private String encryptPassword(String username, String password, String salt) {
        return Md5Utils.hash(username + password + salt);
    }

}