package com.example.bthea.htttsvtdt.Activity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.bthea.htttsvtdt.Data.kqhtdata;
import com.example.bthea.htttsvtdt.Objects.kqht.DiemMonHoc;
import com.example.bthea.htttsvtdt.Objects.namhoc;
import com.example.bthea.htttsvtdt.R;
import com.example.bthea.htttsvtdt.Session;
import com.example.bthea.htttsvtdt.User;
import com.example.bthea.htttsvtdt.adapter.KqhtAdapter;

import java.util.ArrayList;
import java.util.List;


public class KqhtActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Toolbar mToolbar;
    private List<namhoc> lstnamhoc = namhoc.getdata() ;
    private int nam = lstnamhoc.get(0).getYear();
    private Session session;
    private Spinner spinner;
    private KqhtAdapter adapter;

    private ListView listView;

    int pos =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kqht);
        session = new Session(getApplicationContext());
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listkqht);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //session.createHKSession(nam, hcky);


        for(int i = 0;i < lstnamhoc.size();i++)
        {
            if(lstnamhoc.get(i).getYear()==nam)
            {
                pos = i;
                break;


            }
        }

        int year = session.getHK().get(session.KEY_YEAR);
        User user = session.getUserDetails();

        List<DiemMonHoc> data = kqhtdata.getlist(user,year);

        adapter = new KqhtAdapter(this, 0, data);
        listView.setAdapter(adapter);







    }
    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hocki_spinner, menu);

        lstnamhoc = new ArrayList<>(namhoc.getdata());

        MenuItem item = menu.findItem(R.id.spinnerhk);
        spinner = (Spinner) MenuItemCompat.getActionView(item);

        ArrayAdapter<namhoc> adapter = new ArrayAdapter<namhoc>(this, R.layout.spinner_item_hk, lstnamhoc );

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //Toast.makeText(getBaseContext(), "Position: "+pos, Toast.LENGTH_LONG).show();


        spinner.setSelection(pos);




        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        namhoc year = lstnamhoc.get(position);

        nam = year.getYear();
        session.createNamhocSession(nam);



        User user = session.getUserDetails();

        List<DiemMonHoc> data = kqhtdata.getlist(user,nam);

        adapter = new KqhtAdapter(this, 0, data);
        listView.setAdapter(adapter);



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
