package com.example.r2d2.databaseexample.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.r2d2.databaseexample.R;
import com.example.r2d2.databaseexample.entity.DocumentEntity;
import com.example.r2d2.databaseexample.entity.UserEntity;

import java.util.List;

public class DocumentListAdapters extends BaseAdapter{
    private final List<DocumentEntity> documents;

    public DocumentListAdapters(List<DocumentEntity> documents) {
        this.documents = documents;
    }

    @Override
    public int getCount() {
        return this.documents.size();
    }

    @Override
    public DocumentEntity getItem(int i) {
        return this.documents.get(i);
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
                    R.layout.list_documents,
                    viewGroup,
                    false);

        }

        TextView tvDocument = reusableView.findViewById(R.id.tvDocument);

        DocumentEntity document = this.getItem(i);

        tvDocument.setText(document.getName());

        return reusableView;
    }
}
