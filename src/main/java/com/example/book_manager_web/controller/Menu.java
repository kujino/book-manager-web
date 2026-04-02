package com.example.book_manager_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String showRegisterForm(@ModelAttribute BookForm form, Model model) {
        return "/register-form";
    }

        @PostMapping("/register-form")
    public String registerBook(@Validated BookForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showRegisterForm(form, model);
        }
        model.addAttribute("bookForm", form);

        return "confirm-register";
    }

    @PostMapping("/confirm-register")
    public String confirmRegisterBook(@Validated BookForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showRegisterForm(form, model);
        }
        
        bookService.Register(form.toEntity());
        
        return "redirect:/booklists";
    }

    @PostMapping("/return-register-form")
    public String showRegisterFormRet(@ModelAttribute BookForm form) {
        return "/register-form";
    }

    @GetMapping("/booklists")
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.selectBooks());
        
        return "/booklists";
    }
}
