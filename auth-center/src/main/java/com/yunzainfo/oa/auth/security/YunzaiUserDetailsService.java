package com.yunzainfo.oa.auth.security;

import com.yunzainfo.oa.auth.entity.BaseRole;
import com.yunzainfo.oa.auth.entity.BaseUser;
import com.yunzainfo.oa.auth.repository.BaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.EhCacheBasedUserCache;

import java.util.Optional;
import java.util.Set;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/21
 * Email:old_camel@126.com
 */
public class YunzaiUserDetailsService implements UserDetailsService {
    @Autowired
    private BaseUserRepository baseUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<BaseUser> baseUser = baseUserRepository.findBaseUserByUsername(userName);
        //Set<GrantedAuthority> authorities = baseUser.get().getAuthorities();
        //Set<BaseRole> roles = baseUser.get().getRoles();
        return baseUser.map(user -> {
            Set<GrantedAuthority> grantedAuthorities = user.getAuthorities();
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }).orElseThrow(() -> new UsernameNotFoundException("用户" + userName + "不存在!"));
    }
}
