package com.yunzainfo.oa.config.filter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO:TODO
 * Auther:徐成
 * Date:2017/12/19
 * Email:old_camel@126.com
 */



@Component
public class AccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        //Object token = request.getParameter("token");

       /* //校验token
        if (token == null) {
            logger.info("token为空，禁止访问!");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        } else {
            //TODO 根据token获取相应的登录信息，进行校验（略）
        }*/
        //添加Basic Auth认证信息
        //ctx.addZuulRequestHeader("Authorization","Basic "  +getBase64Credentials("xucheng", "123"));

        return null;
    }

    private String getBase64Credentials(String username, String password) {
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        System.out.println(new String(base64CredsBytes));
        return new String(base64CredsBytes);
    }
}