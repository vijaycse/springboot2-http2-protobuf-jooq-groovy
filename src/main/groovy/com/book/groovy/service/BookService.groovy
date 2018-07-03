package com.book.groovy.service

import com.jooq.h2.spring.BookDataProto

interface BookService {

    BookDataProto.BookList findAll()

    BookDataProto.Book findBooksById(Integer bookId)

}