package com.estiam.mybestyoutube.recyclerviewadapter;

import android.app.Activity;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estiam.mybestyoutube.R;
import com.estiam.mybestyoutube.YoutubeVideoDetail;
import com.estiam.mybestyoutube.database.YoutubeVideoDatabase;
import com.estiam.mybestyoutube.pojos.YoutubeVideo;

import java.util.List;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeVideoViewHolder>{

    private List<YoutubeVideo> youtubeVideos;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(YoutubeVideo item);
        void onItemClickDelete(YoutubeVideo item);

    }

    @NonNull
    @Override
    public YoutubeVideoAdapter.YoutubeVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.youtube_video_item,parent,false);
        return new YoutubeVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeVideoAdapter.YoutubeVideoViewHolder holder, int position) {
        holder.bind(youtubeVideos.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return youtubeVideos.size();
    }

    public static class YoutubeVideoViewHolder extends RecyclerView.ViewHolder{

        public TextView tvTitre;
        public ImageButton imBtnDelete;

        public YoutubeVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitre=itemView.findViewById(R.id.tvTitre);
            imBtnDelete=itemView.findViewById(R.id.imBtnDelete);
        }
        public void bind(final YoutubeVideo item, final OnItemClickListener listener) {

            tvTitre.setText(item.getTitre());
            //Mettre la première lettre en majuscule
            tvTitre.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

            tvTitre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
            imBtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickDelete(item);
                }
            });
        }
    }

    public YoutubeVideoAdapter(List<YoutubeVideo> youtubeVideos, OnItemClickListener listener){
        this.youtubeVideos=youtubeVideos;
        this.listener = listener;
    }
}
