package com.example.nikhil.personalinfodata;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtname, txtaddress, txtphone;
    Button btnsave, btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Enabling Up / Back navigation


        txtname = findViewById(R.id.edtName);
        txtphone = findViewById(R.id.edtPhone);
        txtaddress = findViewById(R.id.edtAddress);

        btnsave = findViewById(R.id.btnSave);
        btnback = findViewById(R.id.btnBack);
        final DBHelper dbHelper = new DBHelper(this);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtname.getText().toString().trim();
                String phone = txtphone.getText().toString().trim();
                String address = txtaddress.getText().toString().trim();


                if (TextUtils.isEmpty(name)) {
                    txtname.setError("Please enter a name");
                    txtname.requestFocus();

                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    txtphone.setError("Please enter Phone");
                    txtphone.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    txtaddress.setError("Please enter address");
                    txtaddress.requestFocus();
                    return;
                }


                Person person = new Person(name, phone, address);
                boolean isSuccess = dbHelper.addPerson(person);

                if (isSuccess) {
                    showtoast("Data Stored");

                    txtname.setText("");
                    txtphone.setText("");
                    txtaddress.setText("");
                } else {
                    showtoast("Insertion failed");
                }
            }

        });


    }


    public void showtoast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
