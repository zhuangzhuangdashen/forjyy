package com.example.admincf.lx.shiro;

import com.example.admincf.lx.pojo.po.User;
import com.example.admincf.lx.service.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;
public class Realm extends AuthorizingRealm{

	private static Logger logger=Logger.getLogger(Realm.class);
	
	@Resource
	private UserService userService;
	
	/**
     * 用户授权认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("======用户授权认证======");
        String userName = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> set =this.userService.getByUserNameRole(userName);
        simpleAuthorizationInfo.setRoles(set);
        Set<String> set1 = new HashSet<String>();
        set1.add("select");
        simpleAuthorizationInfo.addStringPermissions(set1);//这里添加用户有的权限列表
        return simpleAuthorizationInfo;
    }
    /**
     * 用户登陆认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("======用户登陆认证======");
        String userName = authenticationToken.getPrincipal().toString();
        User user = userService.getByUserName(userName);
        if (user!=null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPwd(), getName());
            return authenticationInfo;
        }
        return null;
    }
}
