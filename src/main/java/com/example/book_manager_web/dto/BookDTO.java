package com.example.book_manager_web.dto;

import com.example.book_manager_web.entity.BookEntity;

public record BookDTO(
    int id,
    String title,
    String purchaseDate
) {

    public static BookDTO toDTO(BookEntity entity) {
        return new BookDTO (
            entity.getId(),
            entity.getTitle(),
            entity.getPurchaseDate().toString()
        );
    }

}
