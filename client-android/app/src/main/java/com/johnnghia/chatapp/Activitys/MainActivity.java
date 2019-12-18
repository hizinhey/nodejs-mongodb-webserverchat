package com.johnnghia.chatapp.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.johnnghia.chatapp.R;

public class MainActivity extends AppCompatActivity {
    Button btnUser, btnAdmin;
    EditText EdtUser;

    // variable to save number in edtUser
    static int tempUser; // it is converted in checkNumber


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect all widgets in layout with their id
        ConnectWidget();

        // Main action
        ControllerMain();

    }

    private void ConnectWidget(){
        btnUser = findViewById(R.id.btnUser);
        btnAdmin = findViewById(R.id.btnAdmin);
        EdtUser = findViewById(R.id.edtUser);
    }

    // function check value is number or not;
    private void ControllerMain(){
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(convertNumber()){
                    Intent intentUser = new Intent(MainActivity.this, ChatActivity.class);
                    intentUser.putExtra("id", tempUser);
                    intentUser.putExtra("idOpposite", 1);
                    startActivity(intentUser);
                }
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdmin = new Intent(MainActivity.this, ListChatRoomActivity.class);
                startActivity(intentAdmin);
            }
        });
    }

    private boolean convertNumber(){
        String user = EdtUser.getText().toString();
        try {
            tempUser = Integer.parseInt(user);
            return true;
        } catch (Exception ex){
            return false;
        }
    }
}
