package com.example.task61;

public class CartItem {
    private String item_name;
    private int quantity;
    private String price;

    public CartItem(String item_name, int quantity, String price) {
        this.item_name = item_name;
        this.quantity = quantity;
        this.price = price;
    }

    public CartItem() {
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
