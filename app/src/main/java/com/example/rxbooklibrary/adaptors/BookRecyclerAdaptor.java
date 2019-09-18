package com.example.rxbooklibrary.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxbooklibrary.R;
import com.example.rxbooklibrary.models.GoogleBookRetrofit;

import java.util.ArrayList;

public class BookRecyclerAdaptor extends RecyclerView.Adapter<BookRecyclerAdaptor.ViewHolder> {
    private ArrayList<GoogleBookRetrofit> mGoogleBookRetrofits = new ArrayList<>();

    public BookRecyclerAdaptor(ArrayList<GoogleBookRetrofit> googleBookRetrofits) {
        mGoogleBookRetrofits = googleBookRetrofits;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.string_text_front_view);
        }
    }
//From extending bookRecyclerView
    @NonNull
    @Override
    //Will be same for every recycler view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_book_list_item, parent, false);
        return new ViewHolder(view);
    }

    //Set the things
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(mGoogleBookRetrofits.get(position).getKind());
    }

    //Return the count
    @Override
    public int getItemCount() {
        return mGoogleBookRetrofits.size();
    }


}
