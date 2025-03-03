package com.peterpreneur.books.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterpreneur.books.TestData;
import com.peterpreneur.books.domain.Book;
import com.peterpreneur.books.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.peterpreneur.books.TestData.testBook;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class BookControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Test
    public void testThatBookIsCreated() throws Exception {
        final Book book = testBook();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(put("/books/" + book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
                .andExpect(jsonPath("$.author").value(book.getAuthor()));
    }

    @Test
    public void testThatRetrieveBookReturns404WhenBookNotFound() throws Exception {
        mockMvc.perform(get("/books/123123123"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testThatRetrieveBookReturns200AndBookWhenExists() throws Exception {
        final Book book = TestData.testBook();
        bookService.create(book);

        mockMvc.perform(get("/books/" + book.getIsbn()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
                .andExpect(jsonPath("$.author").value(book.getAuthor()));
    }

}