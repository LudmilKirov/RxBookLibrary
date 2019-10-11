package com.example.rxbooklibrary.network;

import com.example.rxbooklibrary.models.GoogleBookRetrofit;
import com.example.rxbooklibrary.models.Item;
import com.example.rxbooklibrary.models.VolumeInfo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

//Set the isbn number of the book hardcode
public interface ApiCallInterface {
    @GET("books/v1/volumes?q=isbn:9780321532053&key=AIzaSyDyxZ6jE8CZ1WF-a4fgMM6L0UjZ4-gC_yc")
    Observable<GoogleBookRetrofit> getResponse();
}
