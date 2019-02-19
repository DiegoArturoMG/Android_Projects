package com.example.r2d2.agenda_rest_example.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r2d2.agenda_rest_example.R;
import com.example.r2d2.agenda_rest_example.model.ContactModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactDetailFragment extends Fragment {

    private ContactModel contact;
    private final static String CONTACT_ARG = "CONTACT_ARG";

    public ContactDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();
        if(args != null){
            this.contact = (ContactModel) args.getSerializable(CONTACT_ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_detail, container, false);
    }

}
