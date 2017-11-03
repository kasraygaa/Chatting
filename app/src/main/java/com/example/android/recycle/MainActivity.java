package com.example.android.recycle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 03/11/2017.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "List";
    Database DatabaseKu;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.List);
        DatabaseKu = new Database(this);

        populateList();
    }

    private void populateList() {
        Log.d(TAG, "populateList: Displaying data in List");
        Cursor data = DatabaseKu.getData();
        List<String> listData = new ArrayList<>();

        int i = 1;
        while(data.moveToNext()){
            listData.add("User-" + i + "  : " + data.getString(1));
            listData.add("Message-" + i + "  : " + data.getString(2));
            i++;
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
    }

    public void tambah(View view) {
        Intent it;
        it = new Intent(this, Chat.class);
        startActivity(it);
    }
}
