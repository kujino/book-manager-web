package com.example.book_manager_web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.book_manager_web.entity.BookEntity;
import com.example.book_manager_web.form.BookForm;
import com.example.book_manager_web.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

        @GetMapping("/booklists")
    public String showBooks(Model model) {
        model.addAttribute("books", bookService.selectBooks());
        
        return "/booklists";
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

        return "/confirm-register";
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

    @PostMapping("/return-edit-form/{id}")
    public String showEditFormRet(@PathVariable("id") int id, @ModelAttribute BookForm form, Model model) {
        model.addAttribute("bookForm", form);
        model.addAttribute("bookId", id);
        model.addAttribute("mode", "EDIT");
        return "/register-form";
    }

    @GetMapping("/editForm/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        BookForm form = bookService.findById(id)
                        .map(BookForm::fromEntity)
                        .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "指定されたIDは見つかりませんでした"
                        ));
        
        model.addAttribute("bookForm", form);
        model.addAttribute("bookId", id);
        model.addAttribute("mode", "EDIT");
        
        return "register-form";
    }

    @PostMapping("confirm-update/{id}")
    public String confirmUpdate(@PathVariable("id") int id, @Validated BookForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookId", id);
            model.addAttribute("mode", "EDIT");
            model.addAttribute("bookForm", form);
            return "register-form";
        }

        model.addAttribute("bookForm", form);
        model.addAttribute("bookId", id);
        model.addAttribute("mode", "EDIT");
        return "/confirm-register";
    }

    @PostMapping("update/{id}")
    public String applyUpdate(@PathVariable("id") int id, @Validated BookForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookId", id);
            model.addAttribute("mode", "EDIT");
            model.addAttribute("bookForm", form);
            
            return "register-form";
        }

        BookEntity entity = form.toEntity();
        entity.setId(id);
        bookService.update(entity);
        return "redirect:/booklists";
    }

}
