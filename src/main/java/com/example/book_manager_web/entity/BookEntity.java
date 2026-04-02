package com.example.book_manager_web.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookEntity {

    private int id;
    private String title;
    private LocalDate purchaseDate;
    private LocalDateTime createdAt;
}
