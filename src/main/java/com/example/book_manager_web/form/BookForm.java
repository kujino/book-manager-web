package com.example.book_manager_web.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.book_manager_web.entity.BookEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookForm (

    @NotBlank(message = "タイトルを入力してください" )
    @Size(max = 256, message = "256文字以内で入力してください")
    String title,

    @NotNull(message = "購入日を選択してください")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate purchaseDate
) {
    public BookEntity toEntity() {
        BookEntity entity = new BookEntity();
        entity.setTitle(title);
        entity.setPurchaseDate(purchaseDate);

        return entity;
    }

    public static BookForm fromEntity(BookEntity entity) {
        return new BookForm(entity.getTitle(), entity.getPurchaseDate());
    }
}
