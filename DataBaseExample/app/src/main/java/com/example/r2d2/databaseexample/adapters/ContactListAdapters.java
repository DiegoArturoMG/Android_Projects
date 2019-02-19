package com.example.r2d2.databaseexample.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.r2d2.databaseexample.R;
import com.example.r2d2.databaseexample.entity.UserEntity;

import java.util.List;

public class ContactListAdapters extends BaseAdapter{

    private final List<UserEntity> contacts;

    public ContactListAdapters(List<UserEntity> contacts){
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return this.contacts.size();
    }

    @Override
    public UserEntity getItem(int i) {
        return this.contacts.get(i);
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
                    R.layout.list_user,
                    viewGroup,
                    false);

        }

        TextView tvName = reusableView.findViewById(R.id.tvName);
        TextView tvLastname = reusableView.findViewById(R.id.tvLastName);

        UserEntity contact = this.getItem(i);

        tvName.setText(contact.getFirstName());
        tvLastname.setText(contact.getLastName());

        return reusableView;
    }
}
