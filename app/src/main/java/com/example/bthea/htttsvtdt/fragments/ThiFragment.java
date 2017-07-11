package com.example.bthea.htttsvtdt.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bthea.htttsvtdt.Data.lichthidata;
import com.example.bthea.htttsvtdt.Objects.MonThi;
import com.example.bthea.htttsvtdt.R;
import com.example.bthea.htttsvtdt.Session;
import com.example.bthea.htttsvtdt.User;
import com.example.bthea.htttsvtdt.adapter.LichthiAdapter;

import java.util.ArrayList;
import java.util.List;


public class ThiFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    LichthiAdapter adapter;
    //User Session Class
    private ArrayList<MonThi> lstlichthi;
    private ListView listView;
    private Spinner spinnerhk;
    private Session session;


    public ThiFragment() {
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
        View view = inflater.inflate(R.layout.fragment_thi, container, false);


        spinnerhk = (Spinner) view.findViewById(R.id.spinnerLoaiKyTHi);
        session = new Session(getActivity().getApplicationContext());


        listView = (ListView) view.findViewById(R.id.listthi);


        String[] items = new String[] {"Giữa kỳ","Cuối kỳ"};

        ArrayAdapter<String> adapterspinner = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item_hocki, items);
        adapterspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerhk.setAdapter(adapterspinner);
        spinnerhk.setOnItemSelectedListener(this);
        return  view;

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        ArrayList<MonThi> lst = new ArrayList<>();

        User user = session.getUserDetails();
        int year = session.getHK().get(session.KEY_YEAR);
        int hk = session.getHK().get(session.KEY_HK);

        lstlichthi = lichthidata.getlist(user,year,hk);

        if(position == 0)
        {
            for(MonThi mt : lstlichthi)
            {
                if(mt.getLoaikythi().equals("Semi"))
                {

                    lst.add(mt);


                }
            }
        }
        if(position == 1)
        {

            for(MonThi mt : lstlichthi)
            {
                if(mt.getLoaikythi().equals("Final"))
                {
                    lst.add(mt);
                }
            }
        }


        adapter = new LichthiAdapter(getActivity(), 0, lst);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
