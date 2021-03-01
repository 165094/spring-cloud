package com.test.auth.security;

import com.test.auth.model.UserDto;
import com.test.auth.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailService")
public class BaseUserDetailService implements UserDetailsService {

    @Autowired
    private IAuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(username);
        UserDto userDto = authService.queryUserByName(username);
        if (userDto == null){
            throw new UsernameNotFoundException("用户不存在!");
        }
//      IntegrationAuthentication auth = IntegrationAuthenticationContext.get();
        //这里可以通过auth 获取 user 值
        //然后根据当前登录方式type 然后创建一个sysuserauthentication 重新设置 username 和 password
        //比如使用手机验证码登录的， username就是手机号 password就是6位的验证码{noop}000000
        List<GrantedAuthority> list = AuthorityUtils.createAuthorityList("admin_role"); //所谓的角色，只是增加ROLE_前缀
        SysUserAuthentication user = new SysUserAuthentication();
        user.setUsername(username);
        user.setPassword(userDto.getPassWord());
        user.setAuthorities(list);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        //user = new User(username, "{noop}123456", list);
        //这里会根据user属性抛出锁定，禁用等异常
    //返回UserDetails的实现user不为空，则验证通过
        return user;
    }
}
