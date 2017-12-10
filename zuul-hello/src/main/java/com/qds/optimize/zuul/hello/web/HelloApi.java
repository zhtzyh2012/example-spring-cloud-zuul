package com.qds.optimize.zuul.hello.web;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建hello对外提供的服务
 *
 * @author neutron
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloApi {

    @RequestMapping(value = "/helloKitty", method = {RequestMethod.GET})
    public String helloKitty(@RequestHeader("x-auth-token") String token) {
        System.err.println("say hello kitty");
        return "say hello kitty: " + token;
    }

}
