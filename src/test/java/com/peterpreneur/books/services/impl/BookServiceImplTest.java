package com.peterpreneur.books.services.impl;

import com.peterpreneur.books.domain.Book;
import com.peterpreneur.books.domain.BookEntity;
import com.peterpreneur.books.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.peterpreneur.books.TestData.testBook;
import static com.peterpreneur.books.TestData.testBookEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl underTest;

    @Test
    public void testThatBookIsSave() {
        final Book book = testBook();

        final BookEntity bookEntity = testBookEntity();

        when(bookRepository.save(eq(bookEntity))).thenReturn(bookEntity);
        final Book result = underTest.create(book);
        assertEquals(book, result);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoBook() {
        final String isbn = "123123123";
        when(bookRepository.findById(eq(isbn))).thenReturn(Optional.empty());

        final Optional<Book> result = underTest.findById(isbn);
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testThatFindByIdReturnsBookWhenExists() {
        final Book book = testBook();
        final BookEntity bookEntity = testBookEntity();
        when(bookRepository.findById(eq(book.getIsbn()))).thenReturn(Optional.of(bookEntity));

        final Optional<Book> result = underTest.findById(book.getIsbn());
        assertEquals(Optional.empty(), result);
    }
}