package com.handong.rebon.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/admin")
@Controller
public class AdminHomeController {

    @GetMapping("")
    public String home() {
        log.info("homeController");
        return "home";
    }
}
