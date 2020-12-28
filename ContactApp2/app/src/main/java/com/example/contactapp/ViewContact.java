package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewContact extends AppCompatActivity {

    TextView tv_viewContact_name;
    Button btn_viewContact_editContact;
    ImageView iv_viewContact_imageIcon;
    ImageButton ibtn_viewContact_phoneCall, ibtn_viewContact_textMessage, ibtn_viewContact_email, ibtn_viewContact_map;

    ContactList contactList;

    int position;
    String name;
    int pictureNumber;
    final static int PERMISSION_TO_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        tv_viewContact_name = findViewById(R.id.tv_viewContact_name);
        btn_viewContact_editContact = findViewById(R.id.btn_viewContact_editContact);
        iv_viewContact_imageIcon = findViewById(R.id.iv_viewContact_imageIcon);
        ibtn_viewContact_phoneCall = findViewById(R.id.ibtn_viewContact_phoneCall);
        ibtn_viewContact_textMessage = findViewById(R.id.ibtn_viewContact_textMessage);
        ibtn_viewContact_email = findViewById(R.id.ibtn_viewContact_email);
        ibtn_viewContact_map = findViewById(R.id.ibtn_viewContact_map);

        contactList = ((GlobalVariables) this.getApplication()).getContactList();

        Bundle incomingMessages = getIntent().getExtras();
        if (incomingMessages != null){
            position = incomingMessages.getInt("position");
            name = incomingMessages.getString("name");
            pictureNumber = incomingMessages.getInt("pictureNumber");
        }

        tv_viewContact_name.setText(name);
        iv_viewContact_imageIcon.setImageResource(icon_resource_numbers[pictureNumber]);

        btn_viewContact_editContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editContact(position);
            }
        });


        ibtn_viewContact_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] addresses = new String [1];
                addresses[0] = contactList.getListOfContacts().get(position).getEmail();
                composeEmail(addresses, "Enter Subject");
            }
        });

        ibtn_viewContact_phoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(contactList.getListOfContacts().get(position).getPhoneNumber());
            }
        });

        ibtn_viewContact_textMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeMmsMessage(contactList.getListOfContacts().get(position).getPhoneNumber(), "");
            }
        });

        ibtn_viewContact_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mapsQuery = "geo:0,0?q=" + contactList.getListOfContacts().get(position).getAddress();
                Uri mapuri = Uri.parse(mapsQuery);
                showMap(mapuri);
            }
        });
    }

    int icon_resource_numbers[] = {
            R.drawable.icon01_01,
            R.drawable.icon01_02,
            R.drawable.icon01_03,
            R.drawable.icon01_04,
            R.drawable.icon01_05,
            R.drawable.icon01_06,
            R.drawable.icon01_07,
            R.drawable.icon01_08,
            R.drawable.icon01_09,
            R.drawable.icon01_10,
            R.drawable.icon01_11,
            R.drawable.icon01_12,
            R.drawable.icon01_13,
            R.drawable.icon01_14,
            R.drawable.icon01_15,
            R.drawable.icon01_16,
            R.drawable.icon01_17,
            R.drawable.icon01_18,
            R.drawable.icon01_19,
            R.drawable.icon01_20,
            R.drawable.icon01_21,
            R.drawable.icon01_22,
            R.drawable.icon01_23,
            R.drawable.icon01_24,
            R.drawable.icon01_25,
            R.drawable.icon01_26,
            R.drawable.icon01_27,
            R.drawable.icon01_28,
            R.drawable.icon01_29,
            R.drawable.icon01_30
    };

    public void editContact (int position){
        Intent goToNewContactForm = new Intent(getApplicationContext(), NewContactForm.class);

        Contact c = contactList.getListOfContacts().get(position);

        goToNewContactForm.putExtra("edit", position);
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




    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeMmsMessage(String phoneNumber, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
















}