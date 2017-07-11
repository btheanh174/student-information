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

import com.example.bthea.htttsvtdt.Activity.tkbdayActivity;
import com.example.bthea.htttsvtdt.R;
import com.example.bthea.htttsvtdt.Session;
import com.example.bthea.htttsvtdt.adapter.DayAdapter;

import java.util.ArrayList;
import java.util.List;


public class TkbFragment extends Fragment {

    DayAdapter adapter;
    private List<String> lstDay;
    private ListView listView;
    Session session;
    private  int year;
    private  int hk;

    public TkbFragment() {
        // Required empty public constructor


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tkb, container, false);
        session = new Session(getActivity().getApplicationContext());

        //year = session.getHK().get(session.KEY_HK);
       // hk = session.getHK().get(session.KEY_YEAR);


        lstDay = new ArrayList() ;
        lstDay.add("Thứ hai" );
        lstDay.add("Thứ ba" );
        lstDay.add("Thứ tư" );
        lstDay.add("Thứ năm" );
        lstDay.add("Thứ sáu" );
        lstDay.add("Thứ bảy" );
        lstDay.add("Chủ nhật" );

        listView = (ListView) view.findViewById(R.id.listday);


        adapter = new DayAdapter(getActivity(),0, lstDay);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                int day = position+2;

                Bundle args = new Bundle();
                args.putInt("day", day);


                Intent intent = new Intent(getActivity().getApplicationContext(), tkbdayActivity.class);
                // đưa các giữ liệu vào "thùng chứa" Bundle

                intent.putExtra("data", args);
                startActivity(intent);
            }
        });

        return view;
    }

}
