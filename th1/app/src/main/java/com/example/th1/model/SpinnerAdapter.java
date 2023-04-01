package com.example.th1.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.th1.R;

public class SpinnerAdapter extends BaseAdapter {
    private int[] imgs = {R.drawable.banh1,R.drawable.banh2,R.drawable.banh3,R.drawable.banh4,
            R.drawable.banh5,R.drawable.banh6};
    private Context context;

    public SpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_image,viewGroup,false);
        ImageView img = item.findViewById(R.id.img);
        img.setImageResource(imgs[i]);
        return item;
    }
}
