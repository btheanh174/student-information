package com.example.bthea.htttsvtdt.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bthea.htttsvtdt.R;
import com.example.bthea.htttsvtdt.Session;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    //User Session Class
    Session session;
    String URL = "http://mobiservice.tdt.edu.vn/Service1.svc/DangNhapService";
    String key = "fd24c1a218b6afaa636ea12e6113ab67";

    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // User Session Manager
        session = new Session(getApplicationContext());
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);


        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        String username = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        final boolean loginstatus;

        String checklogin = "";
        try {
            checklogin = new LoginAsync().execute(username, password).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (checklogin.equals("")) {
            loginstatus = false;
        } else {
            loginstatus = true;
        }
        progressDialog.setMessage("Authenticating...");

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        if (loginstatus) {
                            onLoginSuccess();
                        } else {
                            onLoginFailed();
                        }
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

    }


    @Override
    public void onBackPressed() {
        // Disable going back to the LoginActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {

        Toast.makeText(getBaseContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
        session.createUserLoginSession(_emailText.getText().toString(), _passwordText.getText().toString());
        _loginButton.setEnabled(true);
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Đăng nhập không thành công", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || email.length() < 8) {
            _emailText.setError("Bạn đã nhập sai mã số sinh viên");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            _passwordText.setError("Mật khẩu phải trên 4 kí tự");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    /**
     * Call login function in new thread
     *
     * @param username
     * @param password
     */
    private void loginAsync(final String username, final String password) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new LoginAsync().execute(username, password);
            }
        });
    }

    private class LoginAsync extends AsyncTask<String, Integer, String> {

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
}
