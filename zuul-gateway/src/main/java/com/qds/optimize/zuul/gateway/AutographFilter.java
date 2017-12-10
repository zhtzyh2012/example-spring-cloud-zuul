package com.qds.optimize.zuul.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 设置签名过滤器<br/>
 * 使用component注解表示需要注入到spring容器中
 *
 * @author neutron
 */
@Component
public class AutographFilter extends ZuulFilter {
    /**
     * pre:在请求被路由之前被调用
     * routing:在请求被路由中被调用
     * post:在请求被路由之后被调用
     * error:处理请求发生错误时被调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder表示执行的优先级,值越小表示优先级越高<br/>
     * 多个filter的执行顺序控制
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldFilter则表示该filter是否需要执行<br/>
     * true表示需要被执行<br/>
     * false表示不需要执行<br/>
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 执行Filter真正执行的方法<br/>
     * http请求,run方法,RequestContext
     */
    @Override
    public Object run() {
        // 根据netflix的请求上下文对象来获取请求
        RequestContext context = RequestContext.getCurrentContext();
        // 获取request请求对象
        HttpServletRequest request = context.getRequest();
        // 获取实际请求参数数据
        Map<String, String[]> params = request.getParameterMap();
        // 对数据进行签名验证

        // 比如获取token数据
        String token = request.getHeader("x-auth-token");
        if (StringUtils.isBlank(token)) {
            // 设置返回值信息
            context.setSendZuulResponse(false);
            // 设置返回值状态码
            context.setResponseStatusCode(401);
            // 设置返回值body
            context.setResponseBody("we need token value");
            // 返回null值即可
            return null;
        } else {
            // 如果token是加密,需要解密处理,解密后数据继续处理,然后传递到后面请求处理中
            context.addZuulRequestHeader("x-auth-token", token);
        }

        // 如果最后处理成功,返回上线文对象
        return context;
    }
}
