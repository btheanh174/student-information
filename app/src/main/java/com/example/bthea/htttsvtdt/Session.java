package com.example.bthea.htttsvtdt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.bthea.htttsvtdt.Activity.LoginActivity;
import com.example.bthea.htttsvtdt.Objects.hocky;

import java.util.HashMap;


/**
 * Created by nguye on 4/10/2017.
 */

public class Session {
    public static final String KEY_HK = "hk";
    public static final String KEY_YEAR = "year";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "MSSV";
    // Email address (make variable public to access from outside)
    public static final String KEY_PASSWORD = "Pass";
    // Sharedpref file name
    private static final String PREFER_NAME = "HTTDT";
    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    // Shared Preferences reference
    SharedPreferences pref;
    // Editor reference for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Constructor
    public Session(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //Create login session
    public void createUserLoginSession(String name, String passwaord) {
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_PASSWORD, passwaord);

        // commit changes
        editor.commit();
    }
    //Create login session
    public void createHKSession(int year, int hk) {


        editor.putInt(KEY_HK, hk);

        editor.putInt(KEY_YEAR, year);

        // commit changes
        editor.commit();
    }

    public void createNamhocSession(int year) {




        editor.putInt(KEY_YEAR, year);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     */
    public boolean checkLogin() {
        // Check login status
        if (!this.isUserLoggedIn()) {

            Intent i = new Intent(_context, LoginActivity.class);
            _context.startActivity(i);

            return true;
        }
        return false;
    }


    /**
     * Get stored session data
     */
    public User getUserDetails() {

        //Use hashmap to store user credentials
        User user;

        // user name
        String mssv = pref.getString(KEY_NAME, null);
        // user password id

        String pass = pref.getString(KEY_PASSWORD, null);

        user = new User(mssv, pass);
        // return user
        return user;
    }
    public HashMap<String, Integer> getHK() {


        HashMap<String, Integer> hocky = new HashMap<String, Integer>();
        // user name
        hocky.put(KEY_HK, pref.getInt(KEY_HK, 0));

        // user email id
        hocky.put(KEY_YEAR, pref.getInt(KEY_YEAR, 0));

        // return user
        return hocky;
    }

    public int getNamhoc() {


        int year;
        // user email id
        year = pref.getInt(KEY_YEAR, 0);

        // return user
        return year;
    }
    /**
     * Clear session details
     */
    public void logoutUser() {

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();
        // After logout redirect user to Login Activity


    }


    // Check for login
    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}
