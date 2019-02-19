package com.example.r2d2.fragments.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.r2d2.fragments.R;
import com.example.r2d2.fragments.models.SongModel;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private final List<SongModel> songs;

    public ListViewAdapter(List<SongModel>songs) {
        this.songs = songs;
    }

    @Override
    public int getCount() {
        return this.songs.size();
    }

    @Override
    public SongModel getItem(int i) {
        return this.songs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.getItem(i).hashCode();
    }

    @Override
    public View getView(int i, View reusableview, ViewGroup viewGroup) {

       if(reusableview == null){
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            reusableview = inflater.inflate(R.layout.fragment_listview_item,viewGroup,false);
        }

        TextView tvTitle = reusableview.findViewById(R.id.tvTitulo);
        TextView tvArtist = reusableview.findViewById(R.id.tvArtista);
        TextView tvDuracion = reusableview.findViewById(R.id.tvDuracion);

        SongModel song = this.getItem(i);

        tvTitle.setText(song.getTitle());
        tvArtist.setText(song.getArtist());
        tvDuracion.setText(song.getDuration());

        return reusableview;
    }
}
