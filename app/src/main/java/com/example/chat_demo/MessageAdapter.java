package com.example.chat_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    public static final int MSG_TYPE_SENT = 0;
    public static final int MSG_TYPE_RECEIVE = 1;
    private Context mContext;
    private List<Chat> mChat;

    public MessageAdapter(Context mContext,List<Chat> mChat)
    {
        this.mContext = mContext;
        this.mChat = mChat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        if(viewType == MSG_TYPE_RECEIVE){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_message_sent, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_message_received, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat chat = mChat.get(position);

        holder.show_msg.setText(chat.getMessage());
        holder.show_time.setText(chat.getTime());

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_msg,show_time,show_name;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            show_msg = itemView.findViewById(R.id.text_message_body);
            show_time = itemView.findViewById(R.id.text_message_time);
            show_name = itemView.findViewById(R.id.text_message_name);
        }
    }

    @Override
    public int getItemViewType(int position) {
        String UserEmail = "vkherani@gmail.com";
        if(mChat.get(position).getMessageSender().equals(UserEmail)){
            return MSG_TYPE_RECEIVE;
        }
        else{
            return MSG_TYPE_SENT;
        }
    }
}
