package com.shikhar.helpme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdapter extends ArrayAdapter
{
    Context context;
    ArrayList<PersonDetail> data;
    LayoutInflater inflater;
    public PersonAdapter(Context context, int resource,ArrayList<PersonDetail> data,LayoutInflater inflater)
    {
        super(context, resource, data);

        this.data=data;
        this.context=context;
        this.inflater=inflater;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View    v = inflater.inflate(R.layout.home_adapter, null);
        char ch;
        TextView name_TextView = (TextView) v.findViewById(R.id.name_TextView);
        TextView charge_TextView = (TextView) v.findViewById(R.id.charge_TextView);
        TextView profession_TextView = (TextView) v.findViewById(R.id.profession_TextView);
        ImageView imageView=(ImageView)v.findViewById(R.id.image_ListView);
        PersonDetail personDetail = data.get(position);
        name_TextView.setText(personDetail.name.toUpperCase());
        charge_TextView.setText(String.valueOf(personDetail.charge)+"Rs/Hour");
        profession_TextView.setText(personDetail.profession);
        if(personDetail.profession.equals("cook"))
        {
            ch='c';
        }
        else if(personDetail.profession.equals("driver"))
        {
            ch='d';
        }
        else
        {
            ch='b';
        }
        switch (ch)
        {
            case 'c' : imageView.setImageResource(R.drawable.cook);
                break;
            case 'd' : imageView.setImageResource(R.drawable.driver);
                break;
            case 'b' :imageView.setImageResource(R.drawable.babysitter);
                break;
        }
        return v;
    }
}
