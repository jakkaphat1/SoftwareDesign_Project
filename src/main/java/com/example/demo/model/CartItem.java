package com.example.demo.model;

public class CartItem {
    private Menu menu;
    private int quantity;

    // Constructor
    public CartItem() {
    }

    public CartItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
