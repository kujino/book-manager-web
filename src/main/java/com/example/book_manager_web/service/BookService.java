package com.example.book_manager_web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.book_manager_web.dto.BookDTO;
import com.example.book_manager_web.entity.BookEntity;
import com.example.book_manager_web.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    @Transactional
    public void Register(BookEntity newEntity) {
        repository.insert(newEntity);
    }

    public List<BookDTO> selectBooks() {
        return repository.findAll()
        .stream()
        .map(BookDTO::toDTO)
        .toList();
    }

    public Optional<BookEntity> findById(int bookId) {
        return Optional.ofNullable(repository.selectById(bookId));
    }

    @Transactional
    public void update(BookEntity entity) {
        repository.update(entity);
    }

}
