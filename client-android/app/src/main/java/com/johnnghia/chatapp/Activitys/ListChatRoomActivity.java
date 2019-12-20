package com.johnnghia.chatapp.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;
import com.johnnghia.chatapp.Adapters.AdapterChatroom;
import com.johnnghia.chatapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import com.johnnghia.chatapp.Server.Constants;

public class ListChatRoomActivity extends Activity {
    ListView listView;
    AdapterChatroom adapter;
    ArrayList<Integer>  listUser;

    private Socket mSocket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        listView = findViewById(R.id.listRoom);

        // Cai dat adapter cho Listview
        listUser = new ArrayList<>();
        adapter = new AdapterChatroom(getApplication(), listUser);
        listView.setAdapter(adapter);

        // lay du lieu ve, xac nhan so phong de dua vao User
        try {
            mSocket = IO.socket(new Constants().SERVER_URL);
            Log.i("uri", new Constants().SERVER_URL);

        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("URI", e.toString());
        }

        mSocket.connect();
        mSocket.emit("get-list-user-event");


        synchronized (listUser){
            mSocket.on("client-get-list", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.i("size: ", ""+args.length);
                    Log.i("size_1: ", args[0].toString());
                    JSONArray jsonArray = (JSONArray) args[0];
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Integer temp = jsonObject.getInt("id_user");
                            Log.i("int: ", ""+temp);
                            listUser.add(temp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    synchronized (listUser){
                        listUser.notify();
                    }

                }
            });
            try {
                listUser.wait();
                adapter.notifyDataSetChanged();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        mSocket.disconnect();
        mSocket.close();
    }
}
