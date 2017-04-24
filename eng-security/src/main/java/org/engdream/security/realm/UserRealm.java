package org.engdream.security.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.engdream.security.service.SecurityService;
import org.engdream.sys.entity.User;
import org.engdream.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securiryService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        User user = userService.findByUsername(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(securiryService.findUserRoles(user.getRoleIds()));
        authorizationInfo.setStringPermissions(securiryService.findUserPermissions(user.getRoleIds()));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        try{
            securiryService.checkPaaword(username, String.valueOf(upToken.getPassword()));
        } catch (UnknownAccountException | DisabledAccountException e){
            throw e;
        } catch (AuthenticationException e){
            throw e;
        }
        return new SimpleAuthenticationInfo(
                username,
                upToken.getPassword(), //密码
                getName()  //realm name
        );
    }

}
