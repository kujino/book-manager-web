package com.example.book_manager_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Top {

    @GetMapping("/")
    public String TopMenu() {
        return "top-menu";
    }

}
