package com.yunzainfo.oa.auth.web;

import com.yunzainfo.oa.auth.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/20
 * Email:old_camel@126.com
 */
@RestController
@RefreshScope
public class UserController {

    @GetMapping("/user")
    public Principal user(Principal user){
        System.out.println(SecurityUtils.getCurrentUserUsername());
        return user;
    }

}
