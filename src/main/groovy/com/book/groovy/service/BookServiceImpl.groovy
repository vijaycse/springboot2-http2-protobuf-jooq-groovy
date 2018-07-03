package com.book.groovy.service

import com.jooq.h2.spring.BookData
import com.jooq.h2.spring.BookDataProto
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import static org.jooq.example.db.h2.tables.Book.BOOK

import java.util.stream.Collectors

@Service
class BookServiceImpl implements BookService {


    @Autowired
    DSLContext dslContext

    @Override
    BookDataProto.BookList findAll() {
        return convertToBookListProto(getCollectBooks())
    }

    @Override
    BookDataProto.Book findBooksById(Integer bookId) {
        return convertToBookProto(getBookData(bookId))
    }


    BookDataProto.Book convertToBookProto(BookData bookData) {
        return extractBookData(bookData)
    }


    List<BookData> getCollectBooks() {
        return dslContext.select(BOOK.fields())
                .from(BOOK)
                .fetchInto(BookData.class)
                .stream()
                .collect(Collectors.toList())
    }

    BookDataProto.BookList convertToBookListProto(List<BookData> bookDataList) {
        println(" book " + bookDataList.toString())
        def bookProtoList = BookDataProto.BookList.newBuilder().build()
        bookDataList.forEach { bookData ->
            bookProtoList.toBuilder().addBook(
                    extractBookData(bookData)
                    )
        }
        return bookProtoList
    }

    BookDataProto.Book extractBookData(BookData bookData) {
        return BookDataProto.Book.newBuilder()
                .setAuthorId(bookData.authorId)
                .setPublished(bookData.publishedIn)
                .setBookTitle(bookData.title)
                .build()
    }


    BookData getBookData(Integer bookId) {
        return dslContext.select(BOOK.fields()).from(BOOK).where(BOOK.ID.eq(bookId))
                .fetchInto(BookData.class)
                .stream()
                .findFirst()
                .get()
    }

}



