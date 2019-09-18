package com.example.rxbooklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.rxbooklibrary.adaptors.BookRecyclerAdaptor;
import com.example.rxbooklibrary.models.GoogleBookRetrofit;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //UI Components
    private RecyclerView mRecyclerView;

    //Vars
    private ArrayList<GoogleBookRetrofit> mGoogleBookRetrofitsList = new ArrayList<>();
    private BookRecyclerAdaptor mBookRecyclerAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=findViewById(R.id.recyclerView);
        initRecyclerView();
        fakeBooks();

    }

    private void fakeBooks(){
        for (int i = 0; i <1000 ; i++) {
            GoogleBookRetrofit googleBookRetrofit = new GoogleBookRetrofit();
            googleBookRetrofit.setKind("kind @"+i);
            mGoogleBookRetrofitsList.add(googleBookRetrofit);
        }
        mBookRecyclerAdaptor.notifyDataSetChanged();
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mBookRecyclerAdaptor = new BookRecyclerAdaptor(mGoogleBookRetrofitsList);
        mRecyclerView.setAdapter(mBookRecyclerAdaptor);
    }
}
