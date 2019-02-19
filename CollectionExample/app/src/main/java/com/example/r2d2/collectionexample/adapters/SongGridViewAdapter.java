package com.example.r2d2.collectionexample.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.r2d2.collectionexample.R;
import com.example.r2d2.collectionexample.model.SongModel;

import java.util.List;

public class SongGridViewAdapter extends BaseAdapter{

    private final List<SongModel> songs;

    public SongGridViewAdapter(List<SongModel> songs){
        this.songs = songs;
    }

    @Override
    public int getCount() {
        return this.songs.size();
    }

    @Override
    public Object getItem(int position) {
        return this.songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.songs.get(position).hashCode();
    }

    @Override
    public View getView(int position, View reusableView, ViewGroup parent) {
        if(reusableView == null){
            LayoutInflater inflanter = LayoutInflater.from(parent.getContext());
            reusableView = inflanter.inflate(R.layout.gridview_item_layout,parent,false);
        }

        TextView tvTitle = reusableView.findViewById(R.id.tvTitle);
        TextView tvArtist = reusableView.findViewById(R.id.tvArtist);
        TextView tvDuration = reusableView.findViewById(R.id.tvTime);

        SongModel song = (SongModel)this.getItem(position);

        tvTitle.setText(song.getTitle());
        tvArtist.setText(song.getArtist());
        tvDuration.setText(song.getDuration());

        return reusableView;
    }

}
