package com.example.book_manager_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.book_manager_web.form.BookForm;
import com.example.book_manager_web.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Menu {

    private final BookService bookService;

    @GetMapping("/")
    public String TopMenu() {
        return "top-menu";
    }
    
    @GetMapping("/register-form")
    public String showRegisterForm(@ModelAttribute BookForm form) {
        return "/register-form";
    }

    @PostMapping("/register-form")
    public String registerBook(@Validated BookForm form) {
        bookService.Register(form.toEntity());

        return "redirect:/registered";
    }

    @GetMapping("/registered")
    public String registered() {
        return "/registered";

    }
}
