package com.example.book_manager_web.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.book_manager_web.entity.BookEntity;

@Mapper
public interface BookRepository {

    @Insert("""
            INSERT INTO books (title, purchase_date)
            VALUES (#{title}, #{purchaseDate})
            """)

    void insert(BookEntity newEntity);
}
