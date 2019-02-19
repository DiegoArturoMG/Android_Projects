package com.example.r2d2.databaseexample.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.r2d2.databaseexample.R;
import com.example.r2d2.databaseexample.adapters.ContactListAdapters;
import com.example.r2d2.databaseexample.adapters.DocumentListAdapters;
import com.example.r2d2.databaseexample.database.AppDatabase;
import com.example.r2d2.databaseexample.entity.DocumentEntity;
import com.example.r2d2.databaseexample.entity.UserEntity;

import java.util.List;

public class UserDetailFragment extends Fragment {
    private final static String CONTACT_ARG = "CONTACT_ARG";
    private UserEntity usuario;
    private TextView tvName;
    private TextView tvLastname;
    private ListView lvDocuments;


    public UserDetailFragment() {
        // Required empty public constructor
    }

    public static UserDetailFragment newInstance(UserEntity usuario) {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(CONTACT_ARG,usuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();
        if (args != null) {
            this.usuario = (UserEntity) args.getSerializable(CONTACT_ARG);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = view.findViewById(R.id.tvName);
        tvLastname = view.findViewById(R.id.tvLastName);

        tvName.setText(this.usuario.getFirstName());
        tvLastname.setText(this.usuario.getLastName());

        lvDocuments = view.findViewById(R.id.lvDocumentos);
        AppDatabase db = AppDatabase.sharedInstance();
        List<DocumentEntity> consulted = db.documentDao().getAllByUserID(this.usuario.getId());
        DocumentListAdapters adapter = new DocumentListAdapters(consulted);
        lvDocuments.setAdapter(adapter);

        ImageButton btnNavega = view.findViewById(R.id.ibAdd);

        btnNavega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserDetailFragment.this.navigateToCreateNewDocument();

            }
        });

    }

    private void navigateToCreateNewDocument(){
        Fragment second = NewDocumentFragment.newInstance(usuario);

        FragmentManager fragmentManager =
                this.getFragmentManager();

        FragmentTransaction transaction =
                fragmentManager.beginTransaction();

        transaction.replace(R.id.container, second);

        transaction.addToBackStack(null);

        transaction.commit();
    }
}
