package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SearchForContact extends AppCompatActivity {

    Spinner spin_searchParameterOptions;

    ImageButton ibtn_home, ibtn_addContact;

    EditText et_userSearch;

    Button btn_searchButton;

    ListView lv_searchResults;

    SearchResultsAdapter searchResultsAdapter;

    ContactList contactList;

    ArrayList<Contact> resultsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_contact);

        final String searchOptions[] = getResources().getStringArray(R.array.searchParameterOptions);

        spin_searchParameterOptions = (Spinner)findViewById(R.id.spin_searchParameterOptions);
        ibtn_home = findViewById(R.id.ibtn_home);
        ibtn_addContact = findViewById(R.id.ibtn_addContact);

        btn_searchButton = findViewById(R.id.btn_searchButton);

        et_userSearch = findViewById(R.id.et_userSearch);

        lv_searchResults = findViewById(R.id.lv_searchResults);

        contactList = new ContactList(((GlobalVariables) this.getApplication()).getContactList().getListOfContacts());

        searchResultsAdapter = new SearchResultsAdapter(SearchForContact.this, resultsList);

        lv_searchResults.setAdapter(searchResultsAdapter);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, searchOptions);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_searchParameterOptions.setAdapter(myAdapter);



        btn_searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultsList.clear();
                String selectedSearchParameter = spin_searchParameterOptions.getSelectedItem().toString();
                String itemToSearchFor = et_userSearch.getText().toString();


                if (selectedSearchParameter.equals("Name")){
                    for(int i = 0; i < contactList.getListOfContacts().size(); i++){
                        if(contactList.getListOfContacts().get(i).getName().toLowerCase().equals(itemToSearchFor.toLowerCase())){
                            resultsList.add(contactList.getListOfContacts().get(i));
                            Toast.makeText(SearchForContact.this, "Found Name", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (selectedSearchParameter.equals("Phone Number")){
                    for(int i = 0; i < contactList.getListOfContacts().size(); i++){
                        if(contactList.getListOfContacts().get(i).getPhoneNumber().toLowerCase().equals(itemToSearchFor.toLowerCase())){
                            resultsList.add(contactList.getListOfContacts().get(i));
                            Toast.makeText(SearchForContact.this, "Found Phone Number", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (selectedSearchParameter.equals("Email")){
                    for(int i = 0; i < contactList.getListOfContacts().size(); i++){
                        if(contactList.getListOfContacts().get(i).getEmail().toLowerCase().equals(itemToSearchFor.toLowerCase())){
                            resultsList.add(contactList.getListOfContacts().get(i));
                            Toast.makeText(SearchForContact.this, "Found Email", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (selectedSearchParameter.equals("Address")){
                    for(int i = 0; i < contactList.getListOfContacts().size(); i++){
                        if(contactList.getListOfContacts().get(i).getAddress().toLowerCase().equals(itemToSearchFor.toLowerCase())){
                            resultsList.add(contactList.getListOfContacts().get(i));
                            Toast.makeText(SearchForContact.this, "Found Address", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (selectedSearchParameter.equals("Website URL")){
                    for(int i = 0; i < contactList.getListOfContacts().size(); i++){
                        if(contactList.getListOfContacts().get(i).getWebsiteURL().toLowerCase().equals(itemToSearchFor.toLowerCase())){
                            resultsList.add(contactList.getListOfContacts().get(i));
                            Toast.makeText(SearchForContact.this, "Found Website URL", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (selectedSearchParameter.equals("Birthday")){
                    for(int i = 0; i < contactList.getListOfContacts().size(); i++){
                        if(contactList.getListOfContacts().get(i).getBirthday().toLowerCase().equals(itemToSearchFor.toLowerCase())){
                            resultsList.add(contactList.getListOfContacts().get(i));
                            Toast.makeText(SearchForContact.this, "Found Birthday", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


                searchResultsAdapter.notifyDataSetChanged();
            }
        });

        //When Click Home
        ibtn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goToMain);
            }
        });

        //When Click Add Contact 
        ibtn_addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddContact = new Intent(getApplicationContext(), NewContactForm.class);
                startActivity(goToAddContact);
            }
        });

        lv_searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editContact(position);
            }
        });
    }

    public void editContact (int position){
        Intent goToNewContactForm = new Intent(getApplicationContext(), NewContactForm.class);
        Contact c1 = resultsList.get(position);
        Contact c2 = new Contact();

        int index = 0;
        for(int i = 0; i<contactList.getListOfContacts().size(); i++){
            c2 = contactList.getListOfContacts().get(i);
            if(c1.toString().equals(c2.toString())){
                index = i;
            }

        }

        Contact c = contactList.getListOfContacts().get(index);

        goToNewContactForm.putExtra("edit", index);
        goToNewContactForm.putExtra("name", c.getName());
        goToNewContactForm.putExtra("group", c.getGroup());
        goToNewContactForm.putExtra("phoneNumber", c.getPhoneNumber());
        goToNewContactForm.putExtra("pictureNumber", c.getPictureNumber());
        goToNewContactForm.putExtra("address", c.getAddress());
        goToNewContactForm.putExtra("email", c.getEmail());
        goToNewContactForm.putExtra("birthday", c.getBirthday());
        goToNewContactForm.putExtra("websiteURL", c.getWebsiteURL());
        goToNewContactForm.putExtra("businessHours", c.getBusinessHours());
        goToNewContactForm.putExtra("description", c.getDescription());

        startActivity(goToNewContactForm);
    }
}