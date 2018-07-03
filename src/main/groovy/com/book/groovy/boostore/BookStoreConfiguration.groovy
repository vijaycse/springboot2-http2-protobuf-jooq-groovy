package com.book.groovy.boostore

import com.book.groovy.service.BookService
import com.book.groovy.service.BookServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter


@Configuration
class BookStoreConfiguration {

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter()
    }


    @Bean
    BookService getBookService(){
        return new BookServiceImpl()
    }
}
