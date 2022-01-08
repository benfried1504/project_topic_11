package com.example.project_topic11;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Locale;

// placed information from product to the product layout
public class ProductAdapter extends ArrayAdapter<Product> {
    Context context;
    List<Product> objects;

    public ProductAdapter(@NonNull Context context, int resource , List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.product_layout, parent, false);

        TextView productNameTextView = view.findViewById(R.id.ProductNameTextView);
        TextView priceTextView = view.findViewById(R.id.PriceTextView);
        TextView amountTextView = view.findViewById(R.id.AmountTextView);
        ImageView productImageView = view.findViewById(R.id.ProductImageView);

        Product temp = objects.get(position);

        productNameTextView.setText(temp.getName());
        priceTextView.setText(String.format(Locale.US, "%.2f$", temp.getPrice()));
        amountTextView.setText(String.format(Locale.US, "Amount: %d", temp.getAmount()));
        productImageView.setImageBitmap(temp.getPhoto());

        return view;
    }
}
