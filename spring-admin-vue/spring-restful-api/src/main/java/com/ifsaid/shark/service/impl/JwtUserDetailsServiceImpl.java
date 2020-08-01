package com.ifsaid.shark.service.impl;

import com.ifsaid.shark.common.jwt.JwtUser;
import com.ifsaid.shark.entity.SysUser;
import com.ifsaid.shark.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**

 * <p>
 * spring security 认证 UserDetailsService 实现类
 * </p>
 *
 * @author LinZhaoBing <316638644@qq.com>
 * @version 2.0
 * @date 2019/4/18 11:45

 */


@Slf4j
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.find(SysUser.builder().username(username).build());
        if (user == null || StringUtils.isEmpty(user.getUid())) {
            throw new UsernameNotFoundException(String.format("'%s'.这个用户不存在", username));
        } else {
            return new JwtUser(user.getUid(), user.getUsername(), user.getPassword(), user.getStatus(), null);
        }
    }


}
