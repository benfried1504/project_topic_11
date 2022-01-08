package com.example.project_topic11;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button addProductButton, showAllButton;
    EditText productNameInput, priceInput, amountInput;
    ImageView imageView;
    ImageButton imageButton;

    Bitmap photo;

    ArrayList<Product> productsList = new ArrayList<>();
    final int CAMERA_REQUEST = 1;

    // set the layout as the activity_main layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // created the screen (whats written on it)
        productNameInput = (EditText) findViewById(R.id.ProductNameInput);
        priceInput = (EditText) findViewById(R.id.PriceInput);
        amountInput = (EditText) findViewById(R.id.AmountInput);

        // made the add product button
        addProductButton = (Button) findViewById(R.id.AddProductBtn);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = productNameInput.getText().toString();
                String priceText = priceInput.getText().toString();
                String amountText = amountInput.getText().toString();

                if (productName.equals("") || priceText.equals("") || amountText.equals("")) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Missing Data",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                double price = Double.parseDouble(priceText);
                int amount = Integer.parseInt(amountText);
                Product newProduct = new Product(
                        productName,
                        photo,
                        price,
                        amount
                );
                // cleared the screen
                productsList.add(newProduct);
                Toast.makeText(
                        getApplicationContext(),
                        "Added Product: " + productName +
                                ", amount: " + amount +
                                ", price: " + price,
                        Toast.LENGTH_SHORT
                ).show();
                productNameInput.setText("");
                priceInput.setText("");
                amountInput.setText("");
                imageView.setImageBitmap(null);
                photo = null;
            }
        });

        // created the button that moves to the show all layout
        showAllButton = (Button) findViewById(R.id.ShowAllButton);
        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowAll.class);

                intent.putExtra("productsList", productsList);

                startActivity(intent);
            }
        });

        // created the upload image button
        imageView = findViewById(R.id.imageView);

        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, CAMERA_REQUEST);
            }


        });
    }

    //uploaded the image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }
}
