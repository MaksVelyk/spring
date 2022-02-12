package com.cookwarestore.model;

public class OrderPosition {
    private Cookware cookware;
    private int quantity;

    public OrderPosition(Cookware cookware, int quantity) {
        this.cookware = cookware;
        this.quantity = quantity;
    }

    public OrderPosition() {
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public Cookware getCookware() {
        return cookware;
    }

    public void setCookware(Cookware cookware) {
        this.cookware = cookware;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
