package com.example.project_topic11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowAll extends AppCompatActivity {
    ListView allProductsListView;
    ArrayList<Product> productsList;

// changed layout to show_all layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        Intent intent = getIntent();

        productsList = (ArrayList<Product>) intent.getSerializableExtra("productsList");

        allProductsListView = findViewById(R.id.AllProductsListView);
        ArrayAdapter<Product> productAdapter = new ProductAdapter(this, R.layout.product_layout, productsList);
        allProductsListView.setAdapter(productAdapter);

        // created the button that moves to the bill layout and code
        Button billButton = findViewById(R.id.getBillButton);
        billButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowAll.this, Bill.class);

                intent.putExtra("productsList", productsList);

                startActivity(intent);
            }


        });
    }
}