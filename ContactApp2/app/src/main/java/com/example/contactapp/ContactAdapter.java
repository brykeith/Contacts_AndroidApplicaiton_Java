package com.example.contactapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ContactAdapter extends BaseAdapter {

    Activity mActivity;
    ContactList contactList;

    public ContactAdapter(Activity mActivity, ContactList contactList) {
        this.mActivity = mActivity;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.getListOfContacts().size();
    }

    @Override
    public Contact getItem(int position) {
        return contactList.getListOfContacts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View contactCardLayout;
        LayoutInflater inflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contactCardLayout = inflater.inflate(R.layout.contact_card_layout, parent, false);

        ImageView iv_picture = contactCardLayout.findViewById(R.id.iv_picture);
        TextView tv_name = contactCardLayout.findViewById(R.id.tv_name);
        TextView tv_phoneNumberValue = contactCardLayout.findViewById(R.id.tv_phoneNumberValue);
        TextView tv_groupValue = contactCardLayout.findViewById(R.id.tv_groupValue);


        Contact c = this.getItem(position);
        tv_name.setText(c.getName());
        tv_groupValue.setText(c.getGroup());
        tv_phoneNumberValue.setText((c.getPhoneNumber()));

        //Create array of photos to choose from
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

        iv_picture.setImageResource(icon_resource_numbers[c.getPictureNumber()]);

        return contactCardLayout;
    }
}
