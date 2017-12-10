package com.qds.optimize.zuul.hi.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建hi对外提供的服务
 *
 * @author neutron
 */
@RestController
@RequestMapping(value = "/hi")
public class HiApi {

    @RequestMapping(value = "/hiKitty", method = {RequestMethod.GET})
    public String hiKitty() {
        System.err.println("say hi kitty");
        return "say hi kitty";
    }

}
