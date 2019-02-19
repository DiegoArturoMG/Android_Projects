package com.example.r2d2.finalproject.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName="message")
public class Message implements Serializable{

    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "uid")
    private String id;

    @ColumnInfo(name = "message")
    private String message;

    @ColumnInfo(name = "emisor")
    private String emisor;

    @ColumnInfo(name = "remitente")
    private String remitente;

    public Message() {}

    public Message(String message, String emisor, String remitente) {
        this.message = message;
        this.emisor = emisor;
        this.remitente = remitente;
    }

    public Message(String id, String message, String emisor, String remitente) {
        this.id = id;
        this.message = message;
        this.emisor = emisor;
        this.remitente = remitente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    @Override
    public String toString() {
        return "Message{" +
                "uid=" + id +
                ", message='" + message + '\'' +
                ", emisor='" + emisor + '\'' +
                ", remitente='" + remitente + '\'' +
                '}';
    }
}
