package com.example.bthea.htttsvtdt.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bthea.htttsvtdt.Activity.HoatdongActivity;
import com.example.bthea.htttsvtdt.Activity.tkbdayActivity;
import com.example.bthea.htttsvtdt.Data.hoatdongdata;
import com.example.bthea.htttsvtdt.Data.khungdiemdata;
import com.example.bthea.htttsvtdt.Data.quatrinhdata;
import com.example.bthea.htttsvtdt.Objects.hdqt.DiemHoatDong;
import com.example.bthea.htttsvtdt.Objects.hdqt.DiemQuaTrinh;
import com.example.bthea.htttsvtdt.Objects.hdqt.KhungDiem;
import com.example.bthea.htttsvtdt.R;
import com.example.bthea.htttsvtdt.Session;
import com.example.bthea.htttsvtdt.User;
import com.example.bthea.htttsvtdt.adapter.KhungDiemAdapter;
import com.example.bthea.htttsvtdt.adapter.QuaTrinhAdapter;

import java.util.ArrayList;


public class KetQuaFragment extends Fragment{
    KhungDiemAdapter adapter;
    //User Session Class
    private ArrayList<KhungDiem> lstKhungDiem;
    private ArrayList<DiemHoatDong> lsthoatdong;
    private ListView listView;
    private TextView tvTong;

    private Session session;


    public KetQuaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ketqua, container, false);


        session = new Session(getActivity().getApplicationContext());


        listView = (ListView) view.findViewById(R.id.listketqua);
        tvTong = (TextView) view.findViewById(R.id.tvtongdiemqt);



        User user = session.getUserDetails();
        int year = session.getHK().get(session.KEY_YEAR);
        int hk = session.getHK().get(session.KEY_HK);

        lstKhungDiem = khungdiemdata.getlist(user, year, hk);
        lsthoatdong = hoatdongdata.getlist(user, year, hk);
        if(lsthoatdong.size()>0) {
            tvTong.setText("        Tổng Điểm :                                " + lsthoatdong.get(0).getDiemTongKet());
        }

        adapter = new KhungDiemAdapter(getActivity(),0,lstKhungDiem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                KhungDiem dhd = lstKhungDiem.get(position);

                Bundle args = new Bundle();
                args.putString("muc", dhd.getMuc());


                Intent intent = new Intent(getActivity().getApplicationContext(), HoatdongActivity.class);
                // đưa các giữ liệu vào "thùng chứa" Bundle

                intent.putExtra("data", args);
                startActivity(intent);
            }
        });

        return  view;

    }

}
