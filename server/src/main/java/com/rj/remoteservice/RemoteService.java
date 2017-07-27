package com.rj.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */

public class RemoteService extends Service {
    private List<Book> books;
    String TAG = RemoteService.class.getSimpleName();
    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: ");
        super.onCreate();
        books = new ArrayList<>();
        books.add(new Book("书1",12));
    }

    private BookManager.Stub bookManager = new BookManager.Stub(){
        @Override
        public void addBook(Book book) throws RemoteException {
            Log.e(TAG, "addBook: "+book );
            books.add(book);
        }

        @Override
        public List<Book> getBooks() throws RemoteException {
            Log.e(TAG, "getBooks: " );
            return books;
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        return bookManager;
    }
}
