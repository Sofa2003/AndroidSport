package com.example.sportproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapterIstoria extends ArrayAdapter<String> {
    private Context context;
    private List<String> data;
    public ListAdapterIstoria(Context context,List<String> data){
        super(context,R.layout.spisoktreni,data);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.spisoktreni,parent,false);
        TextView treniname = view.findViewById(R.id.textspisoktreni);
        TextView datanow =view.findViewById(R.id.textdata);

        String[]parts = data.get(position).split("\n");
        treniname.setText(parts[0]);
        datanow.setText(parts[1]);
        return view;

    }
}
