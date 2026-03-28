package com.example.book_manager_web.form;

import java.time.LocalDate;

import com.example.book_manager_web.entity.BookEntity;

import jakarta.validation.constraints.NotBlank;

public record BookForm (

    @NotBlank
    String title,

    @NotBlank
    String purchaseDate
) {
    public BookEntity toEntity() {
        BookEntity entity = new BookEntity();
        entity.setTitle(title);
        entity.setPurchaseDate(LocalDate.parse(purchaseDate));

        return entity;
    }
}
