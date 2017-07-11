package com.example.bthea.htttsvtdt.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.bthea.htttsvtdt.Objects.MonThi;
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

public class lichthidata {
    static String URL = "http://mobiservice.tdt.edu.vn/Service1.svc/LayLichThiService";
    static String key = "56642e67c3a00dc60900a6810fcd8412";

    private static class LichthiAsync extends AsyncTask<String, Integer, String> {

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

    public static ArrayList<MonThi> getlist(User u, int namhoc, int hk)
    {
        ArrayList<MonThi> lstLichThi = new ArrayList<>();
        String datajson = "";

        try {
            datajson = new LichthiAsync().execute(u.getUserName(), u.getUserPass(),namhoc+"", hk+"").get();
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
            String Ngaythi=null;
            String GioThi=null;
            String Nhomthi=null;
            int Thư=0;
            String PhongThi=null;
            String Loaikythi=null;
            int ThoiLuong=0;
            String GhiChu=null;
            String ToThi= null;
            try {
                MaMH = jsontkb.getString("MaMH");
                TenMH = jsontkb.getString("TenMH");
                Ngaythi = jsontkb.getString("NgayThi");
                GioThi = jsontkb.getString("GioThi");
                Nhomthi = jsontkb.getString("NhomThi");
                Thư = Integer.parseInt(jsontkb.getString("Thu"));
                PhongThi = jsontkb.getString("Phong");
                Loaikythi = jsontkb.getString("LoaiKyThi");
                ThoiLuong = Integer.parseInt(jsontkb.getString("ThoiLuong"));
                GhiChu = jsontkb.getString("GhiChu");
                ToThi = jsontkb.getString("ToThi");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            MonThi tkb = new MonThi(MaMH, TenMH, Ngaythi, GioThi, Nhomthi, Thư, PhongThi, Loaikythi, ThoiLuong, GhiChu, ToThi );
            lstLichThi.add(tkb);


        }
        return lstLichThi;

    }
}

