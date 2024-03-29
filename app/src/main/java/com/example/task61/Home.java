package com.example.task61;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.task61.data.food_DatabaseHelper;
import com.example.task61.model.Food;

import java.util.List;

public class Home extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, FoodAdapter.OnRowClickListener {
    ImageView addFoodImageView, popupImageView;
    food_DatabaseHelper db_food;
    public static final String APPNAME ="com.example.task61";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        RecyclerView foodRecylerView = findViewById(R.id.foodRecyclerView);
        db_food = new food_DatabaseHelper(this);
        List<Food> foodList = db_food.fetchAllFood();

        FoodAdapter adapter = new FoodAdapter(foodList, Home.this, this);
        foodRecylerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        foodRecylerView.setLayoutManager(layoutManager);

        SharedPreferences sp = getSharedPreferences(APPNAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        for (Food food : foodList) {
            String id = String.valueOf(food.getId());
            if (sp.getInt(id, -1) != -1) {
                editor.putInt(id, 0).commit();
            }
        }

        popupImageView = findViewById(R.id.popupImageView);
        popupImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Home.this, v);
                popup.setOnMenuItemClickListener(Home.this);
                popup.inflate(R.menu.menu);
                popup.show();
            }
        });

        addFoodImageView = findViewById(R.id.addFoodImageView);

        addFoodImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createIntent = new Intent(com.example.task61.Home.this, com.example.task61.NewFoodItem.class);
                startActivity(createIntent);
            }
        });

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent createIntent = new Intent(com.example.task61.Home.this, com.example.task61.Home.class);
                startActivity(createIntent);
                return true;
            case R.id.account:
                Intent createIntent2 = new Intent(com.example.task61.Home.this, com.example.task61.MainActivity.class);
                startActivity(createIntent2);
                return true;
            case R.id.myList:
                Intent createIntent3 = new Intent(com.example.task61.Home.this, com.example.task61.MyList.class);
                startActivity(createIntent3);
                return true;
            case R.id.cart:
                Intent createIntent4 = new Intent(com.example.task61.Home.this, com.example.task61.Cart.class);
                startActivity(createIntent4);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void addToListImageView(int position) {
        Intent createIntent = new Intent(com.example.task61.Home.this, com.example.task61.ViewFood.class);
        createIntent.putExtra("food_id", position);
        startActivity(createIntent);
    }
}