package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class NewContactForm extends AppCompatActivity {

    Button btn_cancel, btn_ok, btn_editPhoto;

    ImageView iv_imageIcon;

    ImageButton ibtn_delete;

    EditText et_enterName, et_enterPhoneNumber, et_enterPictureNumber,
            et_enterAddress, et_enterEmail, et_enterBirthday, et_enterWebsiteURL,
            et_enterBusinessHours, et_enterDescription;

    Spinner spin_selectContactType;

    int positionToEdit = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact_form);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_ok = findViewById(R.id.btn_ok);
        btn_editPhoto = findViewById(R.id.btn_editPhoto);

        ibtn_delete = findViewById(R.id.ibtn_delete);

        iv_imageIcon = findViewById(R.id.iv_imageIcon);

        et_enterName = findViewById(R.id.et_enterName);
        et_enterPhoneNumber = findViewById(R.id.et_enterPhoneNumber);
        et_enterPictureNumber = findViewById(R.id.et_enterPictureNumber);
        et_enterAddress = findViewById(R.id.et_enterAddress);
        et_enterEmail = findViewById(R.id.et_enterEmail);
        et_enterBirthday = findViewById(R.id.et_enterBirthday);
        et_enterWebsiteURL = findViewById(R.id.et_enterWebsiteURL);
        et_enterBusinessHours = findViewById(R.id.et_enterBusinessHours);
        et_enterDescription = findViewById(R.id.et_enterDescription);

        spin_selectContactType = findViewById(R.id.spin_selectContactType);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(NewContactForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.typesOfContacts));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_selectContactType.setAdapter(myAdapter);

        //listening for incoming data
        Bundle incomingIntent = getIntent().getExtras();

        if(incomingIntent != null){

            String name = incomingIntent.getString("name");
            String group = incomingIntent.getString("group");
            String phoneNumber = incomingIntent.getString("phoneNumber");
            int pictureNumber = incomingIntent.getInt("pictureNumber");
            String address = incomingIntent.getString("address");
            String email = incomingIntent.getString("email");
            String birthday = incomingIntent.getString("birthday");
            String websiteURL = incomingIntent.getString("websiteURL");
            String businessHours = incomingIntent.getString("businessHours");
            String description = incomingIntent.getString("description");
            positionToEdit = incomingIntent.getInt("edit");

            int spinnerPosition = 0;
            for(int i = 0; i < getResources().getStringArray(R.array.typesOfContacts).length; i++){
                if((getResources().getStringArray(R.array.typesOfContacts))[i].equals(group)){
                    spinnerPosition = i;
                }
            }

            //fill in the data to the form

            iv_imageIcon.setImageResource(icon_resource_numbers[pictureNumber]);
            et_enterName.setText(name);
            spin_selectContactType.setSelection(spinnerPosition);
            et_enterPhoneNumber.setText(phoneNumber);
            et_enterPictureNumber.setText(Integer.toString(pictureNumber));
            et_enterAddress.setText(address);
            et_enterEmail.setText(email);
            et_enterBirthday.setText(birthday);
            et_enterWebsiteURL.setText(websiteURL);
            et_enterBusinessHours.setText(businessHours);
            et_enterDescription.setText(description);
        }





        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMainActivity = new Intent(v.getContext(), MainActivity.class);
                startActivity(goToMainActivity);
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newName = et_enterName.getText().toString();
                String newGroup = spin_selectContactType.getSelectedItem().toString();
                String newPhoneNumber = et_enterPhoneNumber.getText().toString();
                int newPictureNumber = Integer.valueOf(et_enterPictureNumber.getText().toString());
                String newAddress = et_enterAddress.getText().toString();
                String newEmail = et_enterEmail.getText().toString();
                String newBirthday = et_enterBirthday.getText().toString();
                String newWebsiteURL = et_enterWebsiteURL.getText().toString();
                String newBusinessHours = et_enterBusinessHours.getText().toString();
                String newDescription = et_enterDescription.getText().toString();

                Intent goToMainActivity = new Intent(v.getContext(), MainActivity.class);

                goToMainActivity.putExtra("edit", positionToEdit);
                goToMainActivity.putExtra("name", newName);
                goToMainActivity.putExtra("group", newGroup);
                goToMainActivity.putExtra("phoneNumber", newPhoneNumber);
                goToMainActivity.putExtra("pictureNumber", newPictureNumber);
                goToMainActivity.putExtra("address", newAddress);
                goToMainActivity.putExtra("email", newEmail);
                goToMainActivity.putExtra("birthday", newBirthday);
                goToMainActivity.putExtra("websiteURL", newWebsiteURL);
                goToMainActivity.putExtra("businessHours", newBusinessHours);
                goToMainActivity.putExtra("description", newDescription);

                Toast.makeText(NewContactForm.this, "", Toast.LENGTH_SHORT).show();
                startActivity(goToMainActivity);

            }
        });

        btn_editPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSelectAContactPicture = new Intent(v.getContext(), SelectAContactPicture.class);
                startActivity(goToSelectAContactPicture);
            }
        });

        ibtn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMainActivity = new Intent(v.getContext(), MainActivity.class);
                if(positionToEdit >= -1){
                    goToMainActivity.putExtra("delete", 1);
                    goToMainActivity.putExtra("edit", positionToEdit);
                }
                startActivity(goToMainActivity);
            }
        });
    }


    // contact pictures
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
}
