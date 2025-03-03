package com.peterpreneur.books.services.impl;

import com.peterpreneur.books.domain.Book;
import com.peterpreneur.books.domain.BookEntity;
import com.peterpreneur.books.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}