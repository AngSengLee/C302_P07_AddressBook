package com.example.a16004798.c302_p07_addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContactDisplay extends AppCompatActivity {

    Button btnCreate;
    ListView lvContacts;
    ArrayList<Contact> alContacts = new ArrayList<Contact>();
    ArrayAdapter<Contact> aaContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_display);
    }

    @Override
    protected void onResume() {
        super.onResume();
        alContacts.clear();

        lvContacts = findViewById(R.id.listView);
        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactDisplay.this, CreateContact.class);
                startActivity(intent);
            }
        });

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), UpdateDeleteContact.class);
                Contact curritem = alContacts.get(position);
                intent.putExtra("id", curritem.getContactId());
                intent.putExtra("firstName", curritem.getFirstname());
                intent.putExtra("lastName", curritem.getLastname());
                intent.putExtra("mobile", curritem.getMobile());
                startActivity(intent);

            }
        });
        //  aaContacts = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alContacts);
        lvContacts.setAdapter(aaContacts);

        lvContacts = (ListView) findViewById(R.id.listView);
        aaContacts = new ContactsAdapter(this, R.layout.row, alContacts);
        lvContacts.setAdapter(aaContacts);

        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("http://10.0.2.2/C302_CloudAddressBook/getContactDetails.php");
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();
        // Code for step 1 end
    }


    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

                    // process response here
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            String firstname = jsonObj.getString("firstname");
                            String lastname = jsonObj.getString("lastname");
                            String mobile = jsonObj.getString("mobile");
                            int contactId = jsonObj.getInt("id");

                            Contact displayResults = new Contact(firstname, lastname, mobile, contactId);

                            alContacts.add(displayResults);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    aaContacts.notifyDataSetChanged();
                }
            };
}
