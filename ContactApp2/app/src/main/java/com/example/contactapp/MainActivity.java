package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    ImageButton ibtn_home, ibtn_addContact, ibtn_search;

    Button btn_sortByName, btn_sortByGroup;

    ListView lv_contactListview;

    ContactAdapter contactAdapter;

    ContactList contactList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibtn_home = findViewById(R.id.ibtn_home);
        ibtn_addContact = findViewById(R.id.ibtn_addContact);
        ibtn_search = findViewById(R.id.ibtn_search);
        btn_sortByName = findViewById(R.id.btn_soryByName);
        btn_sortByGroup = findViewById(R.id.btn_sortByGroup);
        contactList = ((GlobalVariables) this.getApplication()).getContactList();
        lv_contactListview = findViewById(R.id.lv_contactListView);


        contactAdapter = new ContactAdapter(MainActivity.this, contactList);

        lv_contactListview.setAdapter(contactAdapter);

        Bundle incomingMessages = getIntent().getExtras();
        if(incomingMessages != null){
            // 2. capture incoming data
            int positionEdited = incomingMessages.getInt("edit");
            int delete = incomingMessages.getInt("delete");
            String name = incomingMessages.getString("name");
            String group = incomingMessages.getString("group");
            String phoneNumber = incomingMessages.getString("phoneNumber");
            int pictureNumber = incomingMessages.getInt("pictureNumber");
            String address = incomingMessages.getString("address");
            String email = incomingMessages.getString("email");
            String birthday = incomingMessages.getString("birthday");
            String websiteURL = incomingMessages.getString("websiteURL");
            String businessHours = incomingMessages.getString("businessHours");
            String description = incomingMessages.getString("description");

            // 3. create new person object
            Contact c = new Contact(name, group, phoneNumber, pictureNumber, address, email, birthday, websiteURL, businessHours, description);

            // 4. add person to the list and update the adapter
            if (delete == 1){
                contactList.getListOfContacts().remove(positionEdited);
            } else if (positionEdited > -1) {
                contactList.getListOfContacts().remove(positionEdited);
                contactList.getListOfContacts().add(c);
            } else {
                contactList.getListOfContacts().add(c);
            }
            contactAdapter.notifyDataSetChanged();
        }



        ibtn_addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNewContactForm = new Intent(getApplicationContext(), NewContactForm.class);
                startActivity(goToNewContactForm);
            }
        });

        ibtn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSearchForContact = new Intent(getApplicationContext(), SearchForContact.class);
                startActivity(goToSearchForContact);
            }
        });

        btn_sortByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(contactList.getListOfContacts());
                contactAdapter.notifyDataSetChanged();
            }
        });

        btn_sortByGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(contactList.getListOfContacts(), new Comparator<Contact>() {
                    @Override
                    public int compare(Contact o1, Contact o2) {
                        if(o1.getGroup() != null && o2.getGroup() != null){
                            return o1.getGroup().compareTo(o2.getGroup());
                        } else if (o1.getGroup() != null && o2.getGroup() == null){
                            return 1;
                        } else if (o1.getGroup() == null && o2.getGroup() != null){
                            return -1;
                        } else {
                            return 0;
                        }

                    }
                });
                contactAdapter.notifyDataSetChanged();
            }
        });

        lv_contactListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goToViewContact = new Intent (getApplicationContext(), ViewContact.class);
                goToViewContact.putExtra("position", position);
                goToViewContact.putExtra("name", contactList.getListOfContacts().get(position).getName());
                goToViewContact.putExtra("pictureNumber", contactList.getListOfContacts().get(position).getPictureNumber());


                startActivity(goToViewContact);

            }
        });
    }

}
