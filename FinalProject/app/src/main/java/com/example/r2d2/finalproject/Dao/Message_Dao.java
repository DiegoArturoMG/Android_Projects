package com.example.r2d2.finalproject.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.r2d2.finalproject.Entity.Message;
import com.example.r2d2.finalproject.Entity.Student;

import java.util.List;

@Dao
public interface Message_Dao {

    @Query("SELECT m.* FROM message m, student_message sm, student s WHERE s.sid LIKE (:studentId) AND sm.sid LIKE (:studentId) and sm.mid LIKE m.uid")
    List<Message> loadAllMessagesById_Send(String studentId);

    @Query("SELECT m.* FROM message m WHERE m.remitente LIKE (:studentId)")
    List<Message> loadAllMessagesById_Receive(String studentId);

    @Insert
    void insert(Message message);

}
