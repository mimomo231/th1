package com.example.th1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.th1.model.Cat;
import com.example.th1.model.CatAdapter;
import com.example.th1.model.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CatAdapter.CatItemListener,
            SearchView.OnQueryTextListener{
    private Spinner sp;
    private RecyclerView recyclerView;
    private CatAdapter adapter;
    private EditText etName, etPrice, etDes;
    private Button btnAdd, btnUpdate;
    private int pcurrent;
    private SearchView searchView;
    private int[] imgs = {R.drawable.banh1,R.drawable.banh2,R.drawable.banh3,R.drawable.banh4,
            R.drawable.banh5,R.drawable.banh6};

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapter = new CatAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cat cat = new Cat();
                String i = sp.getSelectedItem().toString();
                String name = etName.getText().toString();
                String des = etDes.getText().toString();
                String price = etPrice.getText().toString();
                int img = R.drawable.banh1;
                double pri = 0;
                try{
                    img = imgs[Integer.parseInt(i)];
                    pri = Double.parseDouble(price);
                    cat.setImg(img);
                    cat.setDes(des);
                    cat.setName(name);
                    cat.setPrice(pri);
                    adapter.add(cat);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cat cat = new Cat();
                String i = sp.getSelectedItem().toString();
                String name = etName.getText().toString();
                String des = etDes.getText().toString();
                String price = etPrice.getText().toString();
                int img = R.drawable.banh1;
                double pri = 0;
                try{
                    img = imgs[Integer.parseInt(i)];
                    pri = Double.parseDouble(price);
                    cat.setImg(img);
                    cat.setDes(des);
                    cat.setName(name);
                    cat.setPrice(pri);
                    adapter.update(pcurrent,cat);
                    btnAdd.setEnabled(true);
                    btnUpdate.setEnabled(false);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //khoi tao view
    private void initView() {
        sp = findViewById(R.id.img);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        sp.setAdapter(adapter);
        recyclerView = findViewById(R.id.rv);
        etName = findViewById(R.id.etName);
        etDes = findViewById(R.id.etDes);
        etPrice = findViewById(R.id.etPrice);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setEnabled(false);
        searchView = findViewById(R.id.svSearch);
    }

    // mỗi lần item  click thì sẽ được action
    @Override
    public void onItemClick(View view, int position) {
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);
        //lấy vị trí ra để sau
        pcurrent = position;
        Cat cat = adapter.getItem(position);
        int img = cat.getImg();
        int p = 0;
        for(int i = 0; i < imgs.length; i++){
            if(img == imgs[i]){
                p = i;
                break;
            }
        }
        sp.setSelection(p);
        etPrice.setText(cat.getPrice()+"");
        etName.setText(cat.getName());
        etDes.setText(cat.getDes());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filter(newText);
        return false;
    }
    //hàm này lọc ra danh sách có chứa từ khóa
    private void filter(String newText) {
        List<Cat> filterlist = new ArrayList<>();
        for (Cat c: adapter.getBackup()) {
            if(c.getName().toLowerCase().contains(newText.toLowerCase())){
                filterlist.add(c);
            }
        }
        if (filterlist.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            adapter.filterList(filterlist);
        }
    }
}