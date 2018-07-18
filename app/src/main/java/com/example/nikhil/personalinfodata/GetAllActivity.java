package com.example.nikhil.personalinfodata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class GetAllActivity extends AppCompatActivity {

    DBHelper dbHelper;
    RecyclerView rvPersonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Enabling Up / Back navigation

        rvPersonList = findViewById(R.id.rv_PersonList);
        dbHelper = new DBHelper(this);

        List<Person> personList=dbHelper.getPersonList();

        rvPersonList.setLayoutManager(new LinearLayoutManager(this));

        PersonListAdapter adapter = new PersonListAdapter(personList);

        rvPersonList.setAdapter(adapter);
    }
}
