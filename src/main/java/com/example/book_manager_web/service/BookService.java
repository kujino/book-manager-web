package com.example.book_manager_web.service;

import org.springframework.stereotype.Service;

import com.example.book_manager_web.entity.BookEntity;
import com.example.book_manager_web.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public void Register(BookEntity newEntity) {
        repository.insert(newEntity);
    }

}
