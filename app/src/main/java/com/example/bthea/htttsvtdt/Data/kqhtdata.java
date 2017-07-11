package com.example.bthea.htttsvtdt.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.bthea.htttsvtdt.Objects.kqht.DiemMonHoc;
import com.example.bthea.htttsvtdt.User;

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
 * Created by Tobi on 5/3/2017.
 */

public class kqhtdata {
    static String URL = "http://mobiservice.tdt.edu.vn/Service1.svc/LayKetQuaHocTapCQService";
    static String key = "a7527c602d65ca19315fde9842374f27";

    private static class kqhtAsync extends AsyncTask<String, Integer, String> {

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
                data.accumulate("NamHoc", params[2]);

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

    public static List<DiemMonHoc> getlist(User u, int namhoc)
    {
        List<DiemMonHoc> lstkqht = new ArrayList<>();
        String datajson = "";

        try {
            datajson = new kqhtAsync().execute(u.getUserName(), u.getUserPass(),namhoc+"").get();
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

        for (int i = 0; i < jsonRoot.length(); i++) {
            JSONObject jsontkb = null;
            try {
                jsontkb = jsonRoot.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String MaMH=null;
            String TenMH=null;
            int sotinchi=0;
            String diem1=null;
            String diem2=null;
            String giuaky=null;
            String cuoiky=null;
            String tongket=null;

            try {
                MaMH = jsontkb.getString("MaMH");
                TenMH = jsontkb.getString("TenMH");
                sotinchi = Integer.parseInt(jsontkb.getString("SoChi"));
                diem1 = jsontkb.getString("Diem1");
                diem2 = jsontkb.getString("Diem2");
                giuaky = jsontkb.getString("GiuaKy");
                cuoiky = jsontkb.getString("CuoiKy");
                tongket =jsontkb.getString("DiemTongKet");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            DiemMonHoc dmh = new DiemMonHoc(MaMH, TenMH, sotinchi, diem1, diem2, giuaky,cuoiky, tongket);
            lstkqht.add(dmh);

        }
        return lstkqht;

    }
}

