package com.example.book_manager_web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.book_manager_web.dto.BookDTO;
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

    public List<BookDTO> selectBooks() {
        return repository.findAll()
        .stream()
        .map(BookDTO::toDTO)
        .toList();

    }

}
