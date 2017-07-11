package com.example.bthea.htttsvtdt.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bthea.htttsvtdt.FragmentDrawer;
import com.example.bthea.htttsvtdt.R;
import com.example.bthea.htttsvtdt.Session;
import com.example.bthea.htttsvtdt.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by nguye on 4/12/2017.
 */

public class ThongBaoActivity extends AppCompatActivity {
    Session session;
    String URL = "http://mobiservice.tdt.edu.vn/Service1.svc/XemNoiDungVaCapNhatDaXemService";
    String key = "3e160cf127ecf0208a8f22f451f42077";
    String idthongbao;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    //User Session Class
    private ListView listView;
    private TextView tvTitle;
    private WebView tvData;
    private TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongbao);
        // Lấy intent của Activity này
        Intent intent = getIntent();
        // lấy thùng chứa Bundle với tên giao dịch là "GoiTin"
        Bundle bundle = intent.getBundleExtra("Data");
        idthongbao = bundle.getString("idthongbao");
        session = new Session(getApplicationContext());
        mToolbar = (Toolbar) findViewById(R.id.mtoolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvTitle = (TextView) findViewById(R.id.tvtieude);
        tvData = (WebView) findViewById(R.id.wnoidung);
        User user = session.getUserDetails();
        String datajson = "";

        try {
            datajson = new ThongBaoAsync().execute(user.getUserName(), user.getUserPass()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        JSONObject jsonRoot = null;
        String tieude = "";
        Date ngaygiotb = null;
        String noidung = "";
        try {
            jsonRoot = new JSONObject(datajson);

            tieude = jsonRoot.getString("TieuDe");
            noidung = jsonRoot.getString("NoiDung");
            //ngaygiotb = new Date(jsonRoot.getString("ngaydang"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        tvTitle.setText(tieude);
        String str="<html><body>"+noidung+"</body></html>";

        tvData.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);



    }


    private class ThongBaoAsync extends AsyncTask<String, Integer, String> {

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
                data.accumulate("TinTucID", idthongbao);
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
