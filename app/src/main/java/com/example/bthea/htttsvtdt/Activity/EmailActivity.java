package com.example.bthea.htttsvtdt.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.JsonReader;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bthea.htttsvtdt.FragmentDrawer;
import com.example.bthea.htttsvtdt.R;
import com.example.bthea.htttsvtdt.Session;
import com.example.bthea.htttsvtdt.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by nguye on 4/12/2017.
 */

public class EmailActivity extends AppCompatActivity {
    Session session;
    String URL = "http://mobiservice.tdt.edu.vn/Service1.svc/MailPreAuthService";
    String key = "582b6318985c2889ff4f799dcf790a17";
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    //User Session Class
    private TextView tvTitle;
    private TextView tvData;
    private TextView tvDate;
    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);


        mToolbar = (Toolbar) findViewById(R.id.mtoolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Lấy intent của Activity này
        Intent intent = getIntent();
        // lấy thùng chứa Bundle với tên giao dịch là "GoiTin"
        Bundle bundle = intent.getBundleExtra("Data");
        session = new Session(getApplicationContext());


        String urlemail = "";


        User user = session.getUserDetails();
        String datajson = "";

        try {
            datajson = new EmailAsync().execute(user.getUserName(), user.getUserPass()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        urlemail = datajson.replaceAll("\"","");

        myWebView = (WebView) findViewById(R.id.emailwebview);

        myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.loadUrl(urlemail);
        myWebView.setWebViewClient(new WebViewClient() {});


    }


    private class EmailAsync extends AsyncTask<String, Integer, String> {

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
         * @param s result
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }
}
