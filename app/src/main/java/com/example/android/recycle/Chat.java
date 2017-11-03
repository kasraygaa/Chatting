package com.example.android.recycle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 03/11/2017.
 */
public class Chat extends AppCompatActivity {
    Database DatabaseKu;
    private EditText et1, et2;
    Button nextAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        et1 = (EditText)findViewById(R.id.user);
        et2 = (EditText)findViewById(R.id.pesan);
        nextAct = (Button)findViewById(R.id.kirim);
        DatabaseKu = new Database(this);

        nextAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = et1.getText().toString();
                String message = et2.getText().toString();
                if(et1.length() != 0){
                    AddData(user, message);
                    et1.setText("");
                    et2.setText("");
                    Intent intent = new Intent(Chat.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    toastMessage("Username can't be empty");
                }
            }
        });
    }

    public void AddData(String user, String message){
        boolean insertData = DatabaseKu.addData(user, message);
        if(insertData){
            toastMessage("Data Sucessfully Inserted!");
        }else{
            toastMessage("Data Already Inserted!");
        }
    }

    private void toastMessage(String Message){
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }
}
