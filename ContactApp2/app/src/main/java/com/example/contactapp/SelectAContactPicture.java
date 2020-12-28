package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class SelectAContactPicture extends AppCompatActivity {

    Button btn_selectFromPhotos, btn_takeAPhoto;
    ImageView iv_selectContactPic_imageIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_a_contact_picture);

        btn_selectFromPhotos = findViewById(R.id.btn_selectFromPhotos);
        btn_takeAPhoto = findViewById(R.id.btn_takeAPhoto);
        iv_selectContactPic_imageIcon = findViewById(R.id.iv_viewContact_imageIcon);


    }
}
