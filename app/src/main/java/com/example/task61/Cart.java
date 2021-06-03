package com.example.task61;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.task61.Cart;
import com.example.task61.data.food_DatabaseHelper;
import com.example.task61.model.Food;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity implements CartAdapter.OnRowClickListener {
    food_DatabaseHelper db_food;
    public static final String APPNAME ="com.example.task61";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        db_food = new food_DatabaseHelper(this);

        RecyclerView cartRecyclerView = findViewById(R.id.cartRecyclerView);
        List<Food> foodList = db_food.fetchAllFood();
        List<CartItem> Cart = new ArrayList<>();

        SharedPreferences sp = getSharedPreferences(APPNAME, Context.MODE_PRIVATE);

        for (Food food : foodList) {
            String id = String.valueOf(food.getId()-1);
            if (sp.getInt(id, -1) > 0) {
                CartItem newItem = new CartItem(food.getTitle(), Integer.parseInt(food.getQuantity()), food.getPrice());
                Cart.add(newItem);
            }
        }

        CartAdapter adapter = new CartAdapter(Cart, Cart.this, this);
        cartRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cartRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onItemClick(int position) {

    }
}
