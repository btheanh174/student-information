package com.example.bthea.htttsvtdt.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.bthea.htttsvtdt.Objects.hdqt.DiemQuaTrinh;
import com.example.bthea.htttsvtdt.Objects.hdqt.KhungDiem;
import com.example.bthea.htttsvtdt.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tobi on 5/3/2017.
 */

public class khungdiemdata {
    static String URL = "http://mobiservice.tdt.edu.vn/Service1.svc/LayKhungDiemDanhGiaRenLuyenService";
    static String key = "c28397c0ac81cbe67aa9155cd0d899b3";

    private static class khungdiemAsync extends AsyncTask<String, Integer, String> {

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

    public static ArrayList<KhungDiem> getlist(User u, int namhoc, int hk)
    {
        ArrayList<KhungDiem> lstkd = new ArrayList<>();
        String datajson = "";

        try {
            datajson = new khungdiemAsync().execute(u.getUserName(), u.getUserPass(),namhoc+"",hk+"").get();
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
            String muc = null;
            String name= null;
            String maxdiem= null;
            String hocky= null;
            String year= null;


            try {
                muc = jsontkb.getString("Muc");
                name = jsontkb.getString("Ten");
                maxdiem = jsontkb.getString("MaxDiem");
                hocky = jsontkb.getString("Semester");
                year = jsontkb.getString("Year");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            KhungDiem kd = new KhungDiem(muc,name,maxdiem, hocky, year);
            lstkd.add(kd);

        }
        return lstkd;

    }
}

