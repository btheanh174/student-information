package com.example.bthea.htttsvtdt.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.bthea.htttsvtdt.Objects.MonHoc;
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

public class tkbdata {
    static String URL = "http://mobiservice.tdt.edu.vn/Service1.svc/LayTKBTheoHocKyService";
    static String key = "e491aabdbb4725f8b92540a2d5f7310b";

    private static class tkbAsync extends AsyncTask<String, Integer, String> {

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
                data.accumulate("HocKy", params[3]);

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

    public static List<MonHoc> getlist(User u, int namhoc, int hk)
    {
        List<MonHoc> lsttkb = new ArrayList<>();
        String datajson = "";

        try {
            datajson = new tkbAsync().execute(u.getUserName(), u.getUserPass(),namhoc+"", hk+"").get();
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
            String MaMH = null;
            try {
                MaMH = jsontkb.getString("MaMH");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            int nhom = 0;
            try {
                nhom = Integer.parseInt(jsontkb.getString("Nhom"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String Phong = null;
            try {
                Phong = jsontkb.getString("Phong");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            int sotiet = 0;
            try {
                sotiet = Integer.parseInt(jsontkb.getString("SoTiet"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String tenMH = null;
            try {
                tenMH = jsontkb.getString("TenMonHoc");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            int thu = 0;
            try {
                thu = Integer.parseInt(jsontkb.getString("Thu"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            int tietbatdau = 0;
            try {
                tietbatdau = Integer.parseInt(jsontkb.getString("TietBatDau"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String tuan = null;
            try {
                tuan = jsontkb.getString("Tuan");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MonHoc tkb = new MonHoc(MaMH, nhom, Phong, sotiet,tietbatdau, thu,tenMH, tuan );
            lsttkb.add(tkb);


        }
        return lsttkb;

    }
}

