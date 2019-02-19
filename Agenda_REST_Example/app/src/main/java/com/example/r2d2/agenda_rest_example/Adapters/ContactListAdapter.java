package com.example.r2d2.agenda_rest_example.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.r2d2.agenda_rest_example.R;
import com.example.r2d2.agenda_rest_example.model.ContactModel;

import java.util.List;

public class ContactListAdapter extends BaseAdapter{

    private final List<ContactModel> contacts;

    public ContactListAdapter(List<ContactModel> contacts) {

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
            LayoutInflater inflanter = LayoutInflater.from(viewGroup.getContext());
            reusableView = inflanter.inflate(R.layout.contact_list_item,viewGroup,false);
        }
        TextView tvName = reusableView.findViewById(R.id.tvName);
        TextView tvLastName = reusableView.findViewById(R.id.tvLastName);
        TextView tvPhone = reusableView.findViewById(R.id.tvPhone);
        TextView tvMail = reusableView.findViewById(R.id.tvMail);

        ContactModel contact = this.getItem(i);

        tvName.setText(contact.getName());
        tvLastName.setText(contact.getLastName());
        tvPhone.setText(contact.getPhone());
        tvMail.setText(contact.getMail());

        return reusableView;

    }
}
