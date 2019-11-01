package com.example.rxbooklibrary.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxbooklibrary.R;
import com.example.rxbooklibrary.models.GoogleBookRetrofit;
import com.example.rxbooklibrary.models.VolumeInfo;

import java.util.ArrayList;

public class BookRecyclerAdaptor extends RecyclerView.Adapter<BookRecyclerAdaptor.ViewHolder> {
    private ArrayList<VolumeInfo> mVolumeInfos;
    private OnBookListener mOnBookListener;

    public BookRecyclerAdaptor(ArrayList<VolumeInfo> googleBookRetrofits,OnBookListener onBookListener) {
        this.mVolumeInfos = googleBookRetrofits;
        this.mOnBookListener = onBookListener;
    }

    //From extending bookRecyclerView
    @NonNull
    @Override
    //Will be same for every recycler view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_book_list_item, parent, false);
        return new ViewHolder(view,mOnBookListener);
    }

    //Set the things
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(mVolumeInfos.get(position).getTitle());
    }

    //Return the count
    @Override
    public int getItemCount() {
        return mVolumeInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title;
        OnBookListener mOnBookListener;

        public ViewHolder(@NonNull View itemView,OnBookListener onBookListener) {
            super(itemView);
            title = itemView.findViewById(R.id.string_text_front_view);
            this.mOnBookListener = onBookListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnBookListener.onBookClick(getAdapterPosition());
        }
    }

    public interface OnBookListener {
        void onBookClick(int position);
    }
}
