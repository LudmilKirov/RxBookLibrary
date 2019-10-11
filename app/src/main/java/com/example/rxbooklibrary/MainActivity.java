package com.example.rxbooklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.rxbooklibrary.adaptors.BookRecyclerAdaptor;
import com.example.rxbooklibrary.models.GoogleBookRetrofit;
import com.example.rxbooklibrary.models.Item;
import com.example.rxbooklibrary.models.VolumeInfo;
import com.example.rxbooklibrary.network.ApiCallInterface;
import com.example.rxbooklibrary.network.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BookRecyclerAdaptor.OnBookListener {
    private static final String TAG = "MainActivity";

    //UI Components
    private RecyclerView mRecyclerView;

    //Vars
    private ArrayList<VolumeInfo> mVolumeInfos = new ArrayList<>();
    private BookRecyclerAdaptor mBookRecyclerAdaptor;
    private VolumeInfo mVolumeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();
        fakeBooks();
        getBooks();

    }

    private void getBooks() {
        getObservable().subscribeWith(getObserver());
    }

    //Create a disposable observer that first
    // go through the pojos that needed
    private DisposableObserver<GoogleBookRetrofit> getObserver() {
        return new DisposableObserver<GoogleBookRetrofit>() {
            @Override
            public void onNext(GoogleBookRetrofit googleBookRetrofit) {
                //Log.d(TAG, "onNext: "+googleBookRetrofit.getItems());
                for (Item item1 : googleBookRetrofit.getItems()) {
                    //Log.d(TAG, "onNext: item"+item1);
                    VolumeInfo volumeInfo = item1.getVolumeInfo();
                    Log.d(TAG, "onNext: volumeInfo " + volumeInfo.getAuthors().toString());
                }
            }


            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: complete");
            }
        };
    }

    //Create observable that call the retrofit client
    private Observable<GoogleBookRetrofit> getObservable() {
        return RestClient.getClient().create(ApiCallInterface.class)
                .getResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void fakeBooks() {
        for (int i = 0; i < 1000; i++) {
            VolumeInfo volumeInfo = new VolumeInfo();
            volumeInfo.setTitle("kind @" + i);
            mVolumeInfos.add(volumeInfo);
        }
        mBookRecyclerAdaptor.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mBookRecyclerAdaptor = new BookRecyclerAdaptor(mVolumeInfos, this);
        mRecyclerView.setAdapter(mBookRecyclerAdaptor);
    }

    @Override
    public void onBookClick(int position) {
        Log.d(TAG, "onBookClick: clicked");

        Intent intent = new Intent(this, detailBookActivity.class);
        intent.putExtra("selected_note", mVolumeInfos.get(position));
        startActivity(intent);
    }


}
