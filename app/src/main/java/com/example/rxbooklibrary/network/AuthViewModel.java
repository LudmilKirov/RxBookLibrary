//package com.example.rxbooklibrary.network;
//
//import android.nfc.Tag;
//import android.util.Log;
//
//import androidx.lifecycle.ViewModel;
//
//import com.example.rxbooklibrary.models.GoogleBookRetrofit;
//
//import io.reactivex.Observer;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
//
//public class AuthViewModel extends ViewModel {
//    private static final String TAG = "AuthViewModel";
//
//    private final ApiCallInterface restClient;
//
//    public  AuthViewModel(ApiCallInterface restClient){
//        this.restClient=restClient;
//        Log.d(TAG, "AuthViewModel: viewModel is working");
//        restClient.getResponse()
//                .toObservable()
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<GoogleBookRetrofit>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(GoogleBookRetrofit googleBookRetrofit) {
//                        Log.d(TAG, "onNext: "+googleBookRetrofit.getKind());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, "onError: ",e );
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
//    }
//}
