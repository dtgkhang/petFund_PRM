package com.example.project_prm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project_prm.model.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductDetailActivity extends AppCompatActivity {
    TextView name, price, description;
    Button buy;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        name = findViewById(R.id.productName);
        price = findViewById(R.id.productPrice);
        description = findViewById(R.id.productDescription);
        buy = findViewById(R.id.buy);

        product = (Product) getIntent().getSerializableExtra("product");

        name.setText(product.getName());
        price.setText(String.valueOf(product.getPrice()));
        description.setText(product.getDescription());

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyProduct(product.getId());
            }
        });
    }

    private void buyProduct(String productId) {
        String url = "http://your-api-url/buy";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ProductDetailActivity.this, "Purchase successful", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProductDetailActivity.this, "Purchase failed", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("productId", productId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
