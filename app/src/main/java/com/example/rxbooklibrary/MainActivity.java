package com.example.rxbooklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.rxbooklibrary.adaptors.BookRecyclerAdaptor;
import com.example.rxbooklibrary.database.LibraryDBHelper;
import com.example.rxbooklibrary.models.GoogleBookRetrofit;
import com.example.rxbooklibrary.models.VolumeInfo;
import com.example.rxbooklibrary.network.ApiCallInterface;
import com.example.rxbooklibrary.network.AuthViewModel;
import com.example.rxbooklibrary.network.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Single;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BookRecyclerAdaptor.OnBookListener {
    private static final String TAG = "MainActivity";

    //UI Components
    private RecyclerView mRecyclerView;

    //Vars
    private ArrayList<VolumeInfo> mVolumeInfos = new ArrayList<>();
    private BookRecyclerAdaptor mBookRecyclerAdaptor;
    LibraryDBHelper myDatabase;
    AuthViewModel authViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=findViewById(R.id.recyclerView);
        myDatabase = new LibraryDBHelper(this);
        addData();
        initRecyclerView();
        fakeBooks();
    }

    private void addData() {

//        VolumeInfo volumeInfo;
//        myDatabase.insertData(volumeInfo.getTitle(),volumeInfo.getPublisher(),volumeInfo.getDescription());
    }

    private void fakeBooks(){
        for (int i = 0; i <1000 ; i++) {
            VolumeInfo volumeInfo = new VolumeInfo();
            volumeInfo.setTitle("kind @"+i);
            mVolumeInfos.add(volumeInfo);
        }
        mBookRecyclerAdaptor.notifyDataSetChanged();
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mBookRecyclerAdaptor = new BookRecyclerAdaptor(mVolumeInfos,this);
        mRecyclerView.setAdapter(mBookRecyclerAdaptor);
    }

    @Override
    public void onBookClick(int position) {
        Log.d(TAG, "onBookClick: clicked");

        Intent intent = new Intent(this,detailBookActivity.class);
        intent.putExtra("selected_note",mVolumeInfos.get(position));
        startActivity(intent);
    }


}
