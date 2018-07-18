package com.example.nikhil.personalinfodata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePersonActivity extends AppCompatActivity {

    EditText edtName, edtAddress, edtPhone;
    Button btnSearch, btnUpdate, btnBack;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_person);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Enabling Up / Back navigation

        dbHelper = new DBHelper(this);

        edtName = findViewById(R.id.edt_name);
        edtAddress = findViewById(R.id.edt_address);
        edtPhone = findViewById(R.id.edt_phone);
        btnSearch = findViewById(R.id.btn_search);
        btnUpdate = findViewById(R.id.btn_update);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdatePersonActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    edtName.setError("Please enter a name");
                    edtName.requestFocus();

                    return;
                }
                Person person = dbHelper.getPersonData(name);

                if (person == null) {
                    showtoast("No record found");
                } else {
                    edtAddress.setText(person.getAddress());
                    edtPhone.setText(person.getPhone());

                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    edtName.setError("Please enter name");
                    edtName.requestFocus();

                    return;
                }

                if (TextUtils.isEmpty(address)) {
                    edtAddress.setError("Please enter address");
                    edtAddress.requestFocus();

                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    edtPhone.setError("Please enter phone");
                    edtPhone.requestFocus();
                    return;
                }
                Person person = new Person(name, phone, address);
                boolean no = dbHelper.updatePerson(person);

                if (no) {
                    showtoast("Data updated successfully");
                    edtName.setText("");
                    edtPhone.setText("");
                    edtAddress.setText("");
                } else {
                    showtoast("Data not updated");
                }

            }
        });
    }

    public void showtoast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
