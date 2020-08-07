package com.example.chat_demo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    RequestQueue rq;
    String UserEMAIL="vkherani@gmail.com";
    EditText Chat_msg;
    Chat chat;
    DatabaseReference reff;
    Button chat_send;


    MessageAdapter messageAdapter;
    List<Chat> mchat;
    RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readMessage();

        reff = FirebaseDatabase.getInstance().getReference().child("Chat");
        @SuppressLint("SimpleDateFormat") String date = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Calendar.getInstance().getTime());


        chat_send = findViewById(R.id.button_chatbox_send);
        recyclerView = findViewById(R.id.reyclerview_message_list);
        Chat_msg = findViewById(R.id.edittext_chatbox);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);

        chat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("Message",Chat_msg.getText().toString());
                map.put("MessageReceiver","admin@gmail.com");
                map.put("MessageSender",UserEMAIL);
                map.put("Time",date);
                /*chat.setMessage(Chat_msg.getText().toString());
                chat.setMessageReceiver("admin@gmail.com");
                chat.setMessageSender(UserEMAIL);
                chat.setTime(ts);*/
                reff.push().setValue(map);
                //Toast.makeText(getApplicationContext(),"sended",Toast.LENGTH_SHORT).show();
                readMessage();
                Chat_msg.setText("");
            }
        });
    }


    public void readMessage(){
        mchat = new ArrayList<>();

        reff = FirebaseDatabase.getInstance().getReference("Chat");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchat.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    mchat.add(chat);

                    messageAdapter = new MessageAdapter(MainActivity.this,mchat);
                    recyclerView.setAdapter(messageAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}