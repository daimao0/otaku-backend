package com.otaku.otaku.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author:daMao
 * @Date: Created in 23:12 2020/8/11
 */
@Controller
public class RouterController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/main")
    public String main(){
        return "main";
    }
    @GetMapping("/soft")
    public String soft(){
        return "soft_index";
    }
    @GetMapping("/add")
    public String add(){
        return "add";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
