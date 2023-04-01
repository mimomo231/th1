package com.example.th1.model;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th1.R;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder>{

    private Context context;

    private List<Cat> mlist;

    private List<Cat> listBackup;

    private CatItemListener mCatItem;

    public CatAdapter(Context context) {
        this.context = context;
        mlist = new ArrayList<>();
        listBackup = new ArrayList<>();
    }

    public List<Cat> getBackup(){

        return listBackup;
    }
    //lấy danh sách được lọc ra
    public void filterList(List<Cat> filterlist){
        mlist = filterlist;
        notifyDataSetChanged();
    }
    public void setClickListener(CatItemListener mCatItem){
        this.mCatItem = mCatItem;

    }

    //tạo 1 view dựng hình
    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new CatViewHolder(view);
    }

//  hien thi du lieu tai 1 vi tri cu the trong viewholder
    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = mlist.get(position);
        if(cat == null)
            return;
        holder.img.setImageResource(cat.getImg());
        holder.tvName.setText(cat.getName());
        holder.tvPrice.setText(cat.getPrice()+"");
        holder.tvDes.setText(cat.getDes());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xac nhan xoa");
                builder.setMessage("Ban co chac muon "+ cat.getName()+" xoa khong?");
                builder.setIcon(R.drawable.remove);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    //tạo ra 1 dialog hỏi có chắc chắn không?
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listBackup.remove(cat);
                        mlist.remove(cat);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mlist != null)
            return mlist.size();
        return 0;
    }

    public void add(Cat cat) {
        listBackup.add(cat);
        mlist.add(cat);
        notifyDataSetChanged();
    }
    public void update(int position, Cat cat){
        listBackup.set(position,cat);
        mlist.set(position,cat);
        notifyDataSetChanged();
    }
    // lấy ra item trong tại position
    public Cat getItem(int position){
        return mlist.get(position);

    }
    // dựng hình viewholder cho cat
    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView tvName, tvPrice, tvDes;
        private Button btnRemove;
        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvName = itemView.findViewById(R.id.tvname);
            tvPrice = itemView.findViewById(R.id.tvprice);
            tvDes = itemView.findViewById(R.id.tvdes);
            btnRemove = itemView.findViewById(R.id.btnRemove);
            //item được bắt sự kiện từng item
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mCatItem != null){
                mCatItem.onItemClick(v,getAdapterPosition());
            }
        }
    }
    // bắt sự kiện click chuột item trong RecyclerView
    public interface CatItemListener {
        void onItemClick(View view, int position);
    }
}
