package com.example.rxbooklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.rxbooklibrary.models.VolumeInfo;

public class detailBookActivity extends AppCompatActivity {
    private static final String TAG = "detailBookActivity";

    //Ui components
    private TextView mTitle;
    private TextView mSubtitle;
    private TextView mDescription;

    //vars
    private boolean mIsNewBook;
    private VolumeInfo mVolumeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        mDescription = findViewById(R.id.book_description);
        mSubtitle = findViewById(R.id.book_subtitle);
        mTitle = findViewById(R.id.book_title);

//        if(getIntent().hasExtra("selected_note")) {
//            VolumeInfo volumeInfo = getIntent().getParcelableExtra("selected_note");
//        }
        if(getIncomingIntent()){
            setNewBook();
        }


    }

    private void setNewBook() {
        mTitle.setText(mVolumeInfo.getTitle());
        mSubtitle.setText(mVolumeInfo.getSubtitle());
        mDescription.setText(mVolumeInfo.getDescription());
    }

    private boolean getIncomingIntent(){
        if(getIntent().hasExtra("selected_note")){
            VolumeInfo incomingVolumeInfo = getIntent().getParcelableExtra("selected_note");
            mIsNewBook = false;
            return false;
        }
        mIsNewBook = true;
        return true;
    }
}
