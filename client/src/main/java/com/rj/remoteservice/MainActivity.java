package com.rj.remoteservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.rj.remoteservice.client.R;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    int index = 0;

    @Override
    public void onClick(View v) {
        startService();
    }

    public void addBook(View view) {
        try {
            Log.e(TAG, "addBooks: ");
            bookManager.addBook(new Book("test" + index, index++));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getBooks(View view) {
        try {
            Log.e(TAG, "getBooks: " + bookManager.getBooks());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection s = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected: " );
            bookManager = BookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: " );
            bookManager = null;
        }
    };
    private BookManager bookManager;

    void startService() {
        Intent intent = new Intent();
        intent.setAction("com.jeozey");
        intent.setPackage("com.rj.remoteservice.server");
        bindService(intent,s,BIND_AUTO_CREATE);
    }
}
