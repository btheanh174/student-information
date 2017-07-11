package com.example.bthea.htttsvtdt.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bthea.htttsvtdt.FragmentDrawer;
import com.example.bthea.htttsvtdt.R;
import com.example.bthea.htttsvtdt.Session;
import com.example.bthea.htttsvtdt.fragments.FriendsFragment;
import com.example.bthea.htttsvtdt.fragments.HomeFragment;
import com.example.bthea.htttsvtdt.fragments.MessagesFragment;

/**
 * Created by nguye on 4/9/2017.
 */

public class MenuActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, View.OnClickListener {
    //User Session Class
    Session session;
    TextView btnLogout;
    ImageButton btnThongBao;
    ImageButton btnTkb;
    ImageButton btnKqht;
    ImageButton btnHdpt;
    ImageButton btnEmail;

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        session = new Session(getApplicationContext());
        if (session.checkLogin()) {
            finish();
        }

        mToolbar = (Toolbar) findViewById(R.id.mtoolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);




        btnThongBao = (ImageButton) findViewById(R.id.btnThongBao);
        btnThongBao.setOnClickListener(this);

        btnTkb = (ImageButton) findViewById(R.id.btnTkb);
        btnTkb.setOnClickListener(this);

        btnKqht= (ImageButton) findViewById(R.id.btnKqht);
        btnKqht.setOnClickListener(this);

        btnHdpt= (ImageButton) findViewById(R.id.btnHdpt);
        btnHdpt.setOnClickListener(this);

        btnEmail= (ImageButton) findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            session.logoutUser();

            Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new FriendsFragment();
                title = getString(R.string.title_friends);
                break;
            case 2:
                fragment = new MessagesFragment();
                title = getString(R.string.title_messages);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThongBao:
                Intent intent = new Intent(getApplicationContext(), DonViActivity.class);
                startActivity(intent);
                break;
            case R.id.btnTkb:
                Intent intent1 = new Intent(getApplicationContext(), TkbActivity.class);
                startActivity(intent1);
                break;

            case R.id.btnKqht:
                Intent intent2 = new Intent(getApplicationContext(), KqhtActivity.class);
                startActivity(intent2);
                break;

            case R.id.btnHdpt:
                Intent intent3 = new Intent(getApplicationContext(), HdqtActivity.class);
                startActivity(intent3);
                break;
            case R.id.btnEmail:
                Intent intent4 = new Intent(getApplicationContext(), EmailActivity.class);
                startActivity(intent4);
                break;
        }

    }
}
