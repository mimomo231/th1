package com.example.th1.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.th1.R;

import java.util.List;
//list view adapter cho SmtActivity
public class LvAdapter extends ArrayAdapter<Cat> {
    private Context context;
    private Cat[] mList;

    public LvAdapter(@NonNull Context context, Cat[] mList) {
        super(context, R.layout.item1,mList);
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //convert xml files that define a layout, convert to view object
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item1,null,false);
        ImageView img = view.findViewById(R.id.img);
        TextView tname = view.findViewById(R.id.tvname);
        TextView tsub = view.findViewById(R.id.tvsub);
        TextView tdes = view.findViewById(R.id.tvdes);
        Cat cat = mList[position];
        img.setImageResource(cat.getImg());
        tname.setText(cat.getName());
        tsub.setText(cat.getPrice()+"");
        tdes.setText(cat.getDes());
        return view;
    }
    public Cat getItem (int position){
        return mList[position];
    }
}
