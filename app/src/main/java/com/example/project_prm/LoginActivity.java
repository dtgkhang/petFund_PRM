package com.example.project_prm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

//import androidx.appcompat.app.AppCompatActivity;
//
//public class LoginActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle saveInstanceState){
//        super.onCreate(saveInstanceState);
//        setContentView(R.layout.activity_login);
//        EditText usernameField = findViewById(R.id.username);
//        EditText passwordField = findViewById(R.id.password);
//        Button loginBtn = findViewById(R.id.loginButton);
//
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String username = usernameField.getText().toString();
//                String password = passwordField.getText().toString();
//
//                Toast.makeText(LoginActivity.this,"Username: "+username +", pass: "+password ,Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//}
public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login;
    String URL = "https://petstore3.swagger.io/api/v3/user/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                loginUser(user, pass);
            }
        });
    }

    private void loginUser(String username, String password) {
        String urlWithParams = URL + "?username=" + username + "&password=" + password;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlWithParams,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        // Move to MainActivity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
//                        finish(); // Optional: close the LoginActivity so the user can't go back to it
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

