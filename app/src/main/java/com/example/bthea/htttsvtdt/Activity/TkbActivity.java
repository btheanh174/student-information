package com.example.bthea.htttsvtdt.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bthea.htttsvtdt.Objects.hocky;
import com.example.bthea.htttsvtdt.R;
import com.example.bthea.htttsvtdt.Session;
import com.example.bthea.htttsvtdt.fragments.TkbFragment;
import com.example.bthea.htttsvtdt.fragments.ThiFragment;

import java.util.ArrayList;
import java.util.List;


public class TkbActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Toolbar mToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<hocky> lsthocky = hocky.getdata() ;
    private int nam = lsthocky.get(0).getYear();
    private int hcky = lsthocky.get(0).getHocky();
    Session session;
    Spinner spinner;
    private ThiFragment thiFragment;
    int pos =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tkb);
        session = new Session(getApplicationContext());
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        //session.createHKSession(nam, hcky);

        nam = session.getHK().get(session.KEY_YEAR);
        hcky = session.getHK().get(session.KEY_HK);
        for(int i = 0;i < lsthocky.size();i++)
        {
            if(lsthocky.get(i).getHocky()==hcky&&lsthocky.get(i).getYear()==nam)
            {
                pos = i;
                break;


            }
        }


    }
    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hocki_spinner, menu);

        lsthocky = new ArrayList<>(hocky.getdata());

        MenuItem item = menu.findItem(R.id.spinnerhk);
        spinner = (Spinner) MenuItemCompat.getActionView(item);

        ArrayAdapter<hocky> adapter = new ArrayAdapter<hocky>(this, R.layout.spinner_item_hk, lsthocky );

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

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.ic_today_white_36dp,
                R.drawable.ic_date_range_white_36dp
        };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        thiFragment = new ThiFragment();
        adapter.addFrag(new TkbFragment(), "Thời Khóa Biểu");
        adapter.addFrag(thiFragment , "Lịch Thi");
        viewPager.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        hocky hky = lsthocky.get(position);

        nam = hky.getYear();
        hcky = hky.getHocky();

        session.createHKSession(nam, hcky);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
      //  thiFragment.refresh();

     


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            // return null to display only the icon
            return mFragmentTitleList.get(position);
        }

    }
}
