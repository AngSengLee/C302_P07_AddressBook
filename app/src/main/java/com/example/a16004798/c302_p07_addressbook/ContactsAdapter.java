package com.example.a16004798.c302_p07_addressbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;

    //Step3a define ArrayList by changing the type e.g to shape

    ArrayList<Contact> contactList; //variable name give it something meaningful

    public ContactsAdapter(Context context, int resource,
                         ArrayList<Contact> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        contactList = objects;
    }

    //template
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInfalter object
        LayoutInflater inflater = (LayoutInflater) parent_context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate a new view hierarchy from the specified xml resource (layout_id)
        // and return the root View of the inflated hierarchy.
        View rowView = inflater.inflate(layout_id, parent, false);

        //codes here STEP 3b bind ALL the UI elements in [xx]row_xml
        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);
        TextView tvNumber = (TextView) rowView.findViewById(R.id.tvNumber);

        Contact currentItem =contactList.get(position);  //STANDARD CODE ("Contact" is the CLASS NAME)
        tvName.setText(currentItem.getFirstname() + " " + currentItem.getLastname());
        tvNumber.setText(currentItem.getMobile());


        return rowView; //STANDARD CODE
    }
}


