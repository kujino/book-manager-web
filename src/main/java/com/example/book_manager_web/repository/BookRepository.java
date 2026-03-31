package com.example.book_manager_web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.book_manager_web.entity.BookEntity;

@Mapper
public interface BookRepository {

    @Insert("""
            INSERT INTO books (title, purchase_date)
            VALUES (#{title}, #{purchaseDate})
            """)

    void insert(BookEntity newEntity);

    @Select("""
            SELECT id, title, purchase_date AS purchaseDate
            FROM books
            """)

    List<BookEntity> findAll();
}
