package com.example.r2d2.finalproject.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.r2d2.finalproject.Adapters.MessageListAdapter_Receive;
import com.example.r2d2.finalproject.Adapters.MessageListAdapter_Send;
import com.example.r2d2.finalproject.Entity.Message;
import com.example.r2d2.finalproject.Entity.Student;
import com.example.r2d2.finalproject.R;
import com.example.r2d2.finalproject.database.AppDatabase;

import java.util.List;

public class ViewReceiveMessage extends Fragment {

    private ListView lvMensajes;

    private final static String CONTACT_ARG = "CONTACT_ARG";
    private Student student;

    public ViewReceiveMessage() {
        // Required empty public constructor
    }

    public static ViewReceiveMessage newInstance(Student student) {
        ViewReceiveMessage fragment = new ViewReceiveMessage();
        Bundle args = new Bundle();
        args.putSerializable(CONTACT_ARG,student);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();
        if (getArguments() != null) {
            this.student = (Student) args.getSerializable(CONTACT_ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_receive_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        lvMensajes = view.findViewById(R.id.lvMensajes);

        AppDatabase db = AppDatabase.sharedInstance();
        List<Message> consulted = db.messageDao().loadAllMessagesById_Receive(student.getMail());
        MessageListAdapter_Receive adapter = new MessageListAdapter_Receive(consulted);
        lvMensajes.setAdapter(adapter);

        lvMensajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long itemId) {
                Message user = (Message)lvMensajes.getAdapter().getItem(position);

                Log.d("TAG","------LVMessage-------------"+user.toString()+"----------------");

                Fragment contactDetail = answer_Question.newInstance(user,student);
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.container, contactDetail);
                transaction.commit();
            }
        });

    }
}
