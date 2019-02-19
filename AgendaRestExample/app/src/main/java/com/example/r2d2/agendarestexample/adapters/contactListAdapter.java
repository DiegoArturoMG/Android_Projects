package com.example.r2d2.agendarestexample.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.r2d2.agendarestexample.R;
import com.example.r2d2.agendarestexample.model.ContactModel;

import java.util.List;

public class contactListAdapter extends BaseAdapter{

    private final List<ContactModel> contacts;

    public contactListAdapter(List<ContactModel> contacts) {
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return this.contacts.size();
    }

    @Override
    public ContactModel getItem(int i) {
        return this.contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).hashCode();
    }

    @Override
    public View getView(int i, View reusableView, ViewGroup viewGroup) {

        if(reusableView == null){
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            reusableView = inflater.inflate(
                    R.layout.contact_list_item,
                    viewGroup,
                    false
            );
        }

        TextView tvName = reusableView.findViewById(R.id.tvName);
        TextView tvLastname = reusableView.findViewById(R.id.tvLastName);
//      TextView tvPhone = reusableView.findViewById(R.id.tvPhone);
//      TextView tvMail = reusableView.findViewById(R.id.tvMail);

        ContactModel contact = this.getItem(i);

        tvName.setText(contact.getName());
        tvLastname.setText(contact.getLastname());
//      tvPhone.setText(contact.getPhone());
//      tvMail.setText(contact.getMail());

        return reusableView;
    }
}
