package com.example.r2d2.databaseexample.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.r2d2.databaseexample.R;
import com.example.r2d2.databaseexample.database.AppDatabase;
import com.example.r2d2.databaseexample.entity.DocumentEntity;
import com.example.r2d2.databaseexample.entity.UserEntity;

import java.util.List;

public class NewDocumentFragment extends Fragment {
    private final static String CONTACT_ARG = "CONTACT_ARG";
    private UserEntity usuario;
    private EditText etNombreDocumento;


    public NewDocumentFragment() {
        // Required empty public constructor
    }

    public static NewDocumentFragment newInstance(UserEntity usuario) {
        NewDocumentFragment fragment = new NewDocumentFragment();
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
        return inflater.inflate(R.layout.fragment_new_document, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnCrear =  view.findViewById(R.id.btnAgregar);

        etNombreDocumento = view.findViewById(R.id.etNombreDocumento);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etNombreDocumento.getText().toString();

                if(name.length() != 0){
                    AppDatabase db = AppDatabase.sharedInstance();

                    DocumentEntity document = new DocumentEntity();

                    document.setImagePath("image.jpg");
                    document.setName(name);
                    document.setUser_id(usuario.getId());

                    db.documentDao().insert(document);

                    Toast.makeText(getActivity(), "Documento agregado con exito",
                            Toast.LENGTH_LONG).show();

                    FragmentManager fm = getFragmentManager();

                    fm.popBackStack();
                }else{
                    Toast.makeText(getActivity(), "Complete los campos",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
