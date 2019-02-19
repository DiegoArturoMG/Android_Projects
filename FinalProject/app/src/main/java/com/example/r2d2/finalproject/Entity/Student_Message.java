package com.example.r2d2.finalproject.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(
        tableName="student_message",
        foreignKeys =
        {
            @ForeignKey(entity = Student.class, parentColumns = "sid", childColumns = "sid", onDelete = CASCADE, onUpdate = CASCADE),
            @ForeignKey(entity = Message.class, parentColumns = "uid", childColumns = "mid", onDelete = CASCADE, onUpdate = CASCADE)
        }
)
public class Student_Message implements Serializable{

    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "smid")
    private String id;

    @ColumnInfo(name = "sid") //emisor
    private String sid;

    @ColumnInfo(name = "mid") //mensaje que se le envia al remitente
    private String mid;

    public Student_Message() {}

    public Student_Message(String sid, String mid) {
        this.sid = sid;
        this.mid = mid;
    }

    public Student_Message(String smid, String sid, String mid) {
        this.id = smid;
        this.sid = sid;
        this.mid = mid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "Student_Message{" +
                "smid=" + id +
                ", sid='" + sid + '\'' +
                ", mid=" + mid +
                '}';
    }
}
