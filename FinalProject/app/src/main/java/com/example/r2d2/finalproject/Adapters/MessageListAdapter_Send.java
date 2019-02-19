package com.example.r2d2.finalproject.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.r2d2.finalproject.Entity.Message;
import com.example.r2d2.finalproject.R;

import java.util.List;

public class MessageListAdapter_Send extends BaseAdapter{

    private final List<Message> messages;

    public MessageListAdapter_Send(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return this.messages.size();
    }

    @Override
    public Message getItem(int i) {
        return this.messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).hashCode();
    }

    @Override
    public View getView(int i, View reusableView, ViewGroup viewGroup) {
        if (reusableView == null) {

            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

            reusableView = inflater.inflate(
                    R.layout.list_messages,
                    viewGroup,
                    false);

        }

        TextView tvMessage = reusableView.findViewById(R.id.tvMensajeList);
        TextView tvTipo = reusableView.findViewById(R.id.tvEmisorList);

        Message message = this.getItem(i);

        tvMessage.setText(message.getMessage());
        tvTipo.setText(message.getRemitente());

        return reusableView;
    }

}
