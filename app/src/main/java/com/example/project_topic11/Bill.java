package com.example.project_topic11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Bill extends AppCompatActivity {
    final double TAX = 17;

    ListView allProductsListView;
    ArrayList<Product> productsList;

 //setting the layout to bill layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        Intent intent = getIntent();

        //got the arraylist
        productsList = (ArrayList<Product>) intent.getSerializableExtra("productsList");

        allProductsListView = findViewById(R.id.BillListView);
        ArrayAdapter<Product> productAdapter = new ProductAdapter(this, R.layout.product_layout, productsList);
        allProductsListView.setAdapter(productAdapter);

        // calculated and wrote the prices
        TextView priceWithoutTaxTextView = findViewById(R.id.PriceWithoutTaxTextView);
        priceWithoutTaxTextView.setText(getString(R.string.price_without_tax, getTotalPrice()));

        TextView priceWithTaxTextView = findViewById(R.id.PriceWithTaxTextView);

        priceWithTaxTextView.setText(getString(R.string.price_with_tax, getTotalPriceAfterTax()));

        // freated the new bill feture
        Button newBillButton = findViewById(R.id.NewBillButton);
        newBillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bill.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    // calculated the total price
    private double getTotalPrice() {
        double sum = 0;
        for (Product product : productsList) {
            sum += product.getTotalPrice();
        }
        return sum;
    }

    private double getTotalPriceAfterTax() {
        return getTotalPrice() * (1 + this.TAX / 100);
    }
}