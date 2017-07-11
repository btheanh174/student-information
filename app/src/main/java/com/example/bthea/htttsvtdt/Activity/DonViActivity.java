package com.example.bthea.htttsvtdt.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bthea.htttsvtdt.FragmentDrawer;
import com.example.bthea.htttsvtdt.Objects.news.DonVi;
import com.example.bthea.htttsvtdt.R;
import com.example.bthea.htttsvtdt.Session;
import com.example.bthea.htttsvtdt.User;
import com.example.bthea.htttsvtdt.adapter.DonviAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by nguye on 4/12/2017.
 */

public class DonViActivity extends AppCompatActivity {
    Session session;
    String URL = "http://mobiservice.tdt.edu.vn/Service1.svc/DanhSachDonViDangTinService";
    String key = "2d4419765e1ad17e1c1dffb249ed6057";
    DonviAdapter adapter;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    //User Session Class
    private ListView listView;
    private List<DonVi> lstDonVi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donvi);
        listView = (ListView) findViewById(R.id.listdonvi);

        session = new Session(getApplicationContext());
        mToolbar = (Toolbar) findViewById(R.id.mtoolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        User user = session.getUserDetails();
        String datajson = "";

        try {
            datajson = new DonviAsync().execute(user.getUserName(), user.getUserPass()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        JSONArray jsonRoot = null;
        try {
            jsonRoot = new JSONArray(datajson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lstDonVi = new ArrayList<>();
        for (int i = 0; i < jsonRoot.length(); i++) {
            JSONObject jsondonvi = null;
            try {
                jsondonvi = jsonRoot.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String tendonvi = null;
            try {
                tendonvi = jsondonvi.getString("donvi");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            int soluongtin = 0;
            try {
                soluongtin = Integer.parseInt(jsondonvi.getString("sl_tin"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            DonVi dv = new DonVi(tendonvi, soluongtin);
            lstDonVi.add(dv);

        }
        adapter = new DonviAdapter(this, 0, lstDonVi);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                // đưa các giữ liệu vào "thùng chứa" Bundle
                Bundle bundle = new Bundle();
                DonVi d = lstDonVi.get(position);
                bundle.putString("donvi", d.getTenDonVi());
                intent.putExtra("GoiTin", bundle);
                startActivity(intent);
            }
        });


    }


    private class DonviAsync extends AsyncTask<String, Integer, String> {

        /**
         * Get data from service
         *
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(String... params) {
            // user libraly https://jsoup.org/
            // see in build.gradle app

            // create json data header
            JSONObject data = new JSONObject();
            try {
                data.accumulate("Key", key);
                data.accumulate("MSSV", params[0]);
                data.accumulate("MatKhau", params[1]);
                Log.d("Data header log: ", data.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Create jsoup object to request to service
            // Type post
            try {
                Document doc = Jsoup
                        .connect(URL)
                        .ignoreContentType(true)
                        .requestBody(data.toString())
                        .header("Content-Type", "application/json; charset=utf-8")
                        .post();
                return doc.text();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * Be call when doInBackgound finish
         *
         * @param s result,
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }
}
