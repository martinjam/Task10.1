package com.example.task61;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task61.data.food_DatabaseHelper;
import com.example.task61.model.Food;

import java.util.List;

public class ViewFood extends AppCompatActivity {

    Button addCartButton;
    ImageView foodImage;
    TextView descriptionTextView2, pickupTextView2, dateTextView2, quantityTextView2, locationTextView2, priceFoodDisplayTextView;
    food_DatabaseHelper db;
    public static final String APPNAME = "com.example.task61";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food);
        addCartButton = findViewById(R.id.saveFoodButton);

        foodImage = findViewById(R.id.imageView);
        descriptionTextView2 = findViewById(R.id.descriptionTextView2);
        dateTextView2 = findViewById(R.id.dateTextView2);
        pickupTextView2 = findViewById(R.id.pickupTextView2);
        quantityTextView2 = findViewById(R.id.quantityTextView2);
        locationTextView2 = findViewById(R.id.locationTextView4);
        priceFoodDisplayTextView = findViewById(R.id.priceFoodDisplayTextView);
        db = new food_DatabaseHelper(this);

        Intent intent = getIntent();
        int food_id = intent.getIntExtra("food_id", -1);
        List<Food> foodItems = db.fetchAllFood();

        if (food_id != -1) {
            try {
                Food currentFood = foodItems.get(food_id);
                BitmapFactory.Options options = new BitmapFactory.Options();
                byte[] image = currentFood.getImage();
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, options);
                foodImage.setImageBitmap(bitmap);

                descriptionTextView2.setText(currentFood.getDescription());
                dateTextView2.setText(currentFood.getDate());
                pickupTextView2.setText(currentFood.getPick_up_time());
                quantityTextView2.setText(currentFood.getQuantity());
                locationTextView2.setText(currentFood.getLocation());
                priceFoodDisplayTextView.setText(currentFood.getPrice());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        addCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences(APPNAME, Context.MODE_PRIVATE);
                if (food_id > 0) {
                    SharedPreferences.Editor editor = sp.edit();
                    Food currentFood = foodItems.get(food_id);
                    int quantity = sp.getInt(String.valueOf(food_id), 0) + 1;
                    String id = String.valueOf(food_id);
                    editor.putInt(id, quantity);
                    editor.commit();
                }
            }
        });
    }
}
