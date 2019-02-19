package com.example.r2d2.finalproject.fragments;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.r2d2.finalproject.Entity.Message;
import com.example.r2d2.finalproject.Entity.Student;
import com.example.r2d2.finalproject.Entity.Student_Message;
import com.example.r2d2.finalproject.R;
import com.example.r2d2.finalproject.database.AppDatabase;

import java.util.Calendar;
import java.util.Date;

public class answer_Question extends Fragment {

    private final static String CONTACT_ARG = "CONTACT_ARG";
    private final static String CONTACT_ARG_2 = "CONTACT_ARG_2";
    Message student_Remitente;
    Student student_Emisor;

    private TextView tvRemitente;
    private EditText etAnswer;
    private Button btnSendAnswer;

    public answer_Question() {
        // Required empty public constructor
    }

    public static answer_Question newInstance(Message user,Student student) {
        answer_Question fragment = new answer_Question();
        Bundle args = new Bundle();
        args.putSerializable(CONTACT_ARG,user);
        args.putSerializable(CONTACT_ARG_2,student);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();
        if (getArguments() != null) {
            this.student_Emisor = (Student) args.getSerializable(CONTACT_ARG_2);
            this.student_Remitente = (Message) args.getSerializable(CONTACT_ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_answer__question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvRemitente = view.findViewById(R.id.tvRemitenteAnswer);
        etAnswer = view.findViewById(R.id.etAnswerMessage);
        btnSendAnswer = view.findViewById(R.id.btnAnswerQuestion);

        tvRemitente.setText(student_Remitente.getEmisor());

        btnSendAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = AppDatabase.sharedInstance();

                Date currentTime = Calendar.getInstance().getTime();

                Message mensaje = new Message();

                mensaje.setId(currentTime.toString()+"_"+student_Emisor.getMail()+"_"+student_Remitente.getEmisor());
                mensaje.setMessage(etAnswer.getText().toString());
                mensaje.setEmisor(student_Emisor.getMail());
                mensaje.setRemitente(student_Remitente.getEmisor());

                db.messageDao().insert(mensaje);
                Log.d("TAG","------Msg-------------"+mensaje.toString()+"----------------");

                Student_Message student_message = new Student_Message();
                student_message.setId(currentTime.toString()+"_"+student_Emisor.getMail()+"_"+student_Remitente.getEmisor());
                student_message.setSid(student_Emisor.getMail());
                student_message.setMid(currentTime.toString()+"_"+student_Emisor.getMail()+"_"+student_Remitente.getEmisor());

                db.studentmessageDao().insert(student_message);
                Log.d("TAG","------student_message-------------"+student_message.toString()+"----------------");

                Toast.makeText(getActivity(), "You have sent the message to "+student_Remitente.getEmisor(), Toast.LENGTH_LONG).show();

                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });

    }
}
