package com.peterpreneur.books;

import com.peterpreneur.books.domain.Book;
import com.peterpreneur.books.domain.BookEntity;

public class TestData {
    private TestData() {
    }

    public static Book testBook() {
        return Book.builder()
                .isbn("123456")
                .author("Virginia")
                .title("The Waves").build();
    }

    public static BookEntity testBookEntity() {
        return BookEntity.builder()
                .isbn("123456")
                .author("Virginia")
                .title("The Waves")
                .build();
    }
}
