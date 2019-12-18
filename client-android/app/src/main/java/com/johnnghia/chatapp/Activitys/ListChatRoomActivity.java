package com.johnnghia.chatapp.Activitys;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.johnnghia.chatapp.Adapters.AdapterChatroom;
import com.johnnghia.chatapp.R;
import com.johnnghia.chatapp.Subjects.User;

import java.util.ArrayList;

public class ListChatRoomActivity extends AppCompatActivity {
     ListView list;
     AdapterChatroom adapter;
     ArrayList<User> listUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        list = findViewById(R.id.listRoom);


    }
}
