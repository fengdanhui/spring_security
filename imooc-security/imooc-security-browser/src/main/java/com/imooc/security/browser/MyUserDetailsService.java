package com.imooc.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @date 2020/5/19
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //3.
    @Autowired
    private PasswordEncoder passwordEncoder;
    //2.
//    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //PasswordEncoderFactories  加密盐值
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名：" + username);
        //根据用户名查找用户信息
        //根据查找到的用户信息判断用户是否被冻结
        //3.bcrypt密码处理：@Bean （PasswordEncode） + passwordEncoder.encode("123456")
        return new User(username, passwordEncoder.encode("123456"),
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        //2.bcrypt密码处理：new BCryptPasswordEncoder() + "{bcrypt}"  + passwordEncoder.encode("123456")
//        return new User(username, "{bcrypt}"  + passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
