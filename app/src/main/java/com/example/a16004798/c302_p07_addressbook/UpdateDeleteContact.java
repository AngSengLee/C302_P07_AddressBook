package com.example.a16004798.c302_p07_addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class UpdateDeleteContact extends AppCompatActivity {

    EditText etUDFirstName, etUDLastName, etUDMobile;
    Button btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_contact);
    }

    @Override
    protected void onResume() {
        super.onResume();

        etUDFirstName = findViewById(R.id.etFirstNameUD);
        etUDLastName = findViewById(R.id.etLastNameUD);
        etUDMobile = findViewById(R.id.etMobileUD);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        Intent i = getIntent();

        String firstName = i.getStringExtra("firstName");
        String lastName = i.getStringExtra("lastName");
        String mobile = i.getStringExtra("mobile");

        etUDFirstName.setText(firstName);
        etUDLastName.setText(lastName);
        etUDMobile.setText(mobile);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int id = intent.getIntExtra("id", -1);
                String fName = etUDFirstName.getText().toString();
                String lName = etUDLastName.getText().toString();
                String phoneNo = etUDMobile.getText().toString();

                // Code for step 1 start
                HttpRequest request = new HttpRequest
                        ("http://10.0.2.2/C302_CloudAddressBook/updateContact.php?id=" + id + "&firstname=" + fName + "&lastname=" + lName + "&mobile=" + phoneNo);
                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("GET");
                request.execute();
                // Code for step 1 end
                finish();
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int id = intent.getIntExtra("id", -1);

                // Code for step 1 start
                HttpRequest request = new HttpRequest
                        ("http://10.0.2.2/C302_CloudAddressBook/deleteContact.php?id=" + id);
                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("GET");
                request.execute();
                // Code for step 1 end
                finish();
            }
        });
    }
    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

                    // process response here
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        jsonObject.get("status");
                        String message = (String)jsonObject.get("message");
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
}
