package com.peterpreneur.books.services;


import com.peterpreneur.books.domain.Book;

import java.util.Optional;

public interface BookService {

    Book create(Book book);

    Optional<Book> findById(String isbn);

}
