package com.example.bthea.htttsvtdt.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bthea.htttsvtdt.FragmentDrawer;
import com.example.bthea.htttsvtdt.Objects.news.ThongBao;
import com.example.bthea.htttsvtdt.R;
import com.example.bthea.htttsvtdt.Session;
import com.example.bthea.htttsvtdt.User;
import com.example.bthea.htttsvtdt.adapter.NewsAdapter;

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

public class NewsActivity extends AppCompatActivity {
    Session session;
    String URL = "http://mobiservice.tdt.edu.vn/Service1.svc/DanhSachTinTucTheoDonViService";
    String key = "2f82e7b8668401cd07084804d71b9701";
    NewsAdapter adapter;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    //User Session Class
    private ListView listView;
    private String donvi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        listView = (ListView) findViewById(R.id.listnews);
        // Lấy intent của Activity này
        Intent intent = getIntent();
        // lấy thùng chứa Bundle với tên giao dịch là "GoiTin"
        Bundle bundle = intent.getBundleExtra("GoiTin");
        donvi = bundle.getString("donvi");

        session = new Session(getApplicationContext());
        mToolbar = (Toolbar) findViewById(R.id.mtoolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        User user = session.getUserDetails();
        String datajson = "";

        try {
            datajson = new NewsAsync().execute(user.getUserName(), user.getUserPass()).get();
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
        final List<ThongBao> lstThongBao = new ArrayList<>();
        for (int i = 0; i < jsonRoot.length(); i++) {
            JSONObject jsonthongbao = null;
            int idthongbao = 0;
            boolean isread = true;
            String tieude = "";

            try {
                jsonthongbao = jsonRoot.getJSONObject(i);
                idthongbao = Integer.parseInt(jsonthongbao.getString("_id"));
                if (jsonthongbao.getString("isread").equals("null")) {
                    isread = false;
                } else isread = true;

                tieude = jsonthongbao.getString("tieude");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            ThongBao dv = new ThongBao(idthongbao, isread, tieude);
            lstThongBao.add(dv);

        }
        adapter = new NewsAdapter(this, 0, lstThongBao);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ThongBaoActivity.class);
                // đưa các giữ liệu vào "thùng chứa" Bundle
                Bundle bundle = new Bundle();
                ThongBao d = lstThongBao.get(position);
                bundle.putString("idthongbao", d.getId() + "");
                intent.putExtra("Data", bundle);
                startActivity(intent);
            }
        });
    }


    private class NewsAsync extends AsyncTask<String, Integer, String> {

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
                data.accumulate("TenDonVi", donvi);
                data.accumulate("beginIndex", 1);
                data.accumulate("endIndex", 20);
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
         * @param s result
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }
}
