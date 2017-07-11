package com.example.bthea.htttsvtdt.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.bthea.htttsvtdt.Data.hoatdongdata;
import com.example.bthea.htttsvtdt.Data.tkbdata;
import com.example.bthea.htttsvtdt.Objects.MonHoc;
import com.example.bthea.htttsvtdt.Objects.hdqt.DiemHoatDong;
import com.example.bthea.htttsvtdt.R;
import com.example.bthea.htttsvtdt.Session;
import com.example.bthea.htttsvtdt.User;
import com.example.bthea.htttsvtdt.adapter.HoatDongAdapter;
import com.example.bthea.htttsvtdt.adapter.TkbAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguye on 4/12/2017.
 */

public class HoatdongActivity extends AppCompatActivity {


    HoatDongAdapter adapter;
    private Toolbar mToolbar;
    //User Session Class
    private List<DiemHoatDong> lstdhd;
    private ListView listView;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoatdong);
        listView = (ListView) findViewById(R.id.listhoatdongpt);

        session = new Session(getApplicationContext());
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        User user = session.getUserDetails();


        // Lấy intent của Activity này
        Intent intent = getIntent();
        // lấy thùng chứa Bundle với tên giao dịch là "GoiTin"
        Bundle bundle = intent.getBundleExtra("data");
        String muc = bundle.getString("muc");
        int year = session.getHK().get(session.KEY_YEAR);
        int hk = session.getHK().get(session.KEY_HK);
        this.setTitle("Mục "+ muc);

        List<DiemHoatDong> data = hoatdongdata.getlist(user,year,hk);
        lstdhd = new ArrayList<>();

        for(DiemHoatDong t : data)
        {
            if(t.getMucID().equals(muc))
            {
                lstdhd.add(t);
            }
        }

        adapter = new HoatDongAdapter(this, 0, lstdhd);
        listView.setAdapter(adapter);


    }




}
