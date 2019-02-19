package com.example.r2d2.practicagps.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.r2d2.practicagps.Entity.Point;
import com.example.r2d2.practicagps.R;

import java.util.List;

public class Adaptador extends BaseAdapter{

    private final List<Point> puntos;

    public Adaptador(List<Point> puntos) {
        this.puntos = puntos;
    }

    @Override
    public int getCount() {
        return this.puntos.size();
    }

    @Override
    public Point getItem(int i) {
        return this.puntos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).hashCode();
    }

    @Override
    public View getView(int i, View reusableView, ViewGroup viewGroup) {
        if (reusableView == null) {

            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

            reusableView = inflater.inflate(
                    R.layout.list_points,
                    viewGroup,
                    false);

        }

        TextView tvLatitud = reusableView.findViewById(R.id.lv_tvLatitud);
        TextView tvLongitud = reusableView.findViewById(R.id.lv_tvLongitud);

        Point punto = this.getItem(i);

        tvLatitud.setText(""+punto.getLatitud());
        tvLongitud.setText(""+punto.getLongitud());

        return reusableView;
    }
}
