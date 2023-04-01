package com.example.th1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.th1.model.Cat;
import com.example.th1.model.LvAdapter;

import java.util.Calendar;

public class SmtActivity extends AppCompatActivity {
    private ListView listView;
    private LvAdapter adapter;
    private Cat[] list;
    private TextView et, ed;
    private Button btTime, btDate;
    private TextView tv;
    private CheckBox cb1, cb2;
    private RadioButton g1, g2;
    private RatingBar ratingBar;
    private TextView kq;
    private Button btshow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smt);

        listView = findViewById(R.id.listview);

        tv = findViewById(R.id.doevt);
        registerForContextMenu(tv);
        initData();
        adapter = new LvAdapter(this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < listView.getAdapter().getCount(); i++) {
                    listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                listView.getChildAt(position).setBackgroundColor(Color.BLUE);
                Cat cat = adapter.getItem(position);
                Toast.makeText(SmtActivity.this, cat.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        btTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hh = c.get(Calendar.HOUR_OF_DAY);
                int mm = c.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(SmtActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        et.setText(hourOfDay + ":" + minute);
                    }
                },hh,mm,false);
                dialog.show();
            }
        });
        btDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int yy = c.get(Calendar.YEAR);
                int mm = c.get(Calendar.MONTH);
                int dd = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(SmtActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ed.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                },yy,mm,dd);
                dialog.show();
            }
        });
        btshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = "check box: ";
                if(cb1.isChecked()){
                    ss += cb1.getText() +", ";
                }
                if(cb2.isChecked()){
                    ss += cb2.getText() +", ";
                }
                ss += "\nGioi tinh: ";
                if (g1.isChecked()){
                    ss += g1.getText();
                }else{
                    ss += g2.getText();
                }
                ss += "\nRating: " + ratingBar.getRating();
//                ss += listView.getSelectedItem().
                kq.setText(ss);
            }
        });
    }

    private void initData() {
        Integer[] imgs = {R.drawable.phoenix, R.drawable.meo1,R.drawable.phoenix, R.drawable.meo1};
        String[] names = getResources().getStringArray(R.array.abc);
        Integer[] sub = {123,324,345,567};
        String[] des = {"des 1","des 2","des 3","des 4"};
        list = new Cat[imgs.length];
        for (int i = 0; i < list.length; i++) {
            list[i] = new Cat(imgs[i],names[i],des[i],sub[i]);
        }
        et = findViewById(R.id.etTime);
        ed = findViewById(R.id.etDate);
        btDate = findViewById(R.id.btDate);
        btTime = findViewById(R.id.btTime);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        g1 = findViewById(R.id.gMale);
        g2 = findViewById(R.id.gFemale);
        ratingBar = findViewById(R.id.rb);
        btshow = findViewById(R.id.btnshow);
        kq = findViewById(R.id.kq);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mFile:
                Toast.makeText(this, "File", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mExit:
                System.exit(0);
                break;
            case R.id.mEmail:
                Toast.makeText(this, "Email", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mPhone:
                Toast.makeText(this, "Phone", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.text_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mRed:
                tv.setTextColor(Color.RED);
                break;
            case R.id.mGreen:
                tv.setTextColor(Color.GREEN);
                break;
            case R.id.mBlue:
                tv.setTextColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }
}