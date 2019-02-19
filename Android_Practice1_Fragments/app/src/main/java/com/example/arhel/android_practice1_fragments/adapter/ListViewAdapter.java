package com.example.arhel.android_practice1_fragments.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.arhel.android_practice1_fragments.R;
import com.example.arhel.android_practice1_fragments.models.SongModel;

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

       if(reusableview == null){//Nos aseguramos de que exista si no es el caso lo creamos
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            //generamos la instaci{on a partir de los cml que estan en layout
            reusableview = inflater.inflate(R.layout.fragment_listview_item,viewGroup,false);

        }
        //Obtenemos la referencia de los campos
        //Obtenemos la referencia del textview a partir de su id
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
