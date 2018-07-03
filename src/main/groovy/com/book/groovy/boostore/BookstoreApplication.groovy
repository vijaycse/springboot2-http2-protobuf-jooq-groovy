package com.book.groovy.boostore

import com.book.groovy.service.BookService
import com.jooq.h2.spring.BookDataProto
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
@CompileStatic
@RestController
class BookstoreApplication {

    @Autowired
    BookService bookService

	static void main(String[] args) {
		SpringApplication.run BookstoreApplication, args
	}

    @GetMapping
    def sayHello(){
        "hello"
    }


    @GetMapping("/book")
    BookDataProto.BookList getBooks(){
       return bookService.findAll()
    }

    @GetMapping("/book/{bookId}")
    BookDataProto.Book getBooksById(@PathVariable Integer bookId){
       return bookService.findBooksById(bookId)
    }





}
