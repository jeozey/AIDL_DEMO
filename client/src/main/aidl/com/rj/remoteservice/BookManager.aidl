// BookManager.aidl
package com.rj.remoteservice;
import com.rj.remoteservice.Book;
// Declare any non-default types here with import statements

interface BookManager {
    List<Book> getBooks();
    void addBook(in Book book);
}
