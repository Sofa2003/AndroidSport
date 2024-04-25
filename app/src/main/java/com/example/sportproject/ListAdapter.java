package com.example.sportproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<Treni> {

    public ListAdapter(Context context, ArrayList<Treni> treniArrayList){
        super(context,R.layout.spisok,treniArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Treni treni = getItem(position);
        if(convertView ==null ){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.spisok,parent,false);
        }
        ImageView imageView = convertView.findViewById(R.id.civtreni);
        TextView textspisok = convertView.findViewById(R.id.textspisok);
        TextView texttime = convertView.findViewById(R.id.texttime);


        imageView.setImageResource(treni.imageid);
        textspisok.setText(treni.name);
        texttime.setText(treni.timetreni);




        return convertView;
    }
}
