package com.example.bthea.htttsvtdt.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.bthea.htttsvtdt.Data.lichthidata;
import com.example.bthea.htttsvtdt.Data.quatrinhdata;
import com.example.bthea.htttsvtdt.Objects.MonThi;
import com.example.bthea.htttsvtdt.Objects.hdqt.DiemQuaTrinh;
import com.example.bthea.htttsvtdt.R;
import com.example.bthea.htttsvtdt.Session;
import com.example.bthea.htttsvtdt.User;
import com.example.bthea.htttsvtdt.adapter.LichthiAdapter;
import com.example.bthea.htttsvtdt.adapter.QuaTrinhAdapter;

import java.util.ArrayList;


public class QuaTrinhFragment extends Fragment{
    QuaTrinhAdapter adapter;
    //User Session Class
    private ArrayList<DiemQuaTrinh> lstdiemquatrinh;
    private ListView listView;
    private Session session;


    public QuaTrinhFragment() {
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
        View view = inflater.inflate(R.layout.fragment_quatrinh, container, false);


        session = new Session(getActivity().getApplicationContext());


        listView = (ListView) view.findViewById(R.id.listquatrinh);




        User user = session.getUserDetails();
        int year = session.getHK().get(session.KEY_YEAR);
        int hk = session.getHK().get(session.KEY_HK);

        lstdiemquatrinh = quatrinhdata.getlist(user, year, hk);


        adapter = new QuaTrinhAdapter(getActivity(),0,lstdiemquatrinh);
        listView.setAdapter(adapter);


        return  view;

    }

}
