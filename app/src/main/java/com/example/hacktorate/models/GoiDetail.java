package com.example.hacktorate.models;

public class GoiDetail {

    private String GroceryName, GroceryPlace, GroceryPrice;
    private String  GroceryTime;

    public GoiDetail() {
    }

    public GoiDetail(String groceryName, String groceryPlace, String groceryPrice, String groceryTime) {
        GroceryName = groceryName;
        GroceryPlace = groceryPlace;
        GroceryPrice = groceryPrice;
        GroceryTime = groceryTime;
    }

    public String getGroceryName() {
        return GroceryName;
    }

    public void setGroceryName(String groceryName) {
        GroceryName = groceryName;
    }

    public String getGroceryPlace() {
        return GroceryPlace;
    }

    public void setGroceryPlace(String groceryPlace) {
        GroceryPlace = groceryPlace;
    }

    public String getGroceryPrice() {
        return GroceryPrice;
    }

    public void setGroceryPrice(String groceryPrice) {
        GroceryPrice = groceryPrice;
    }

    public String getGroceryTime() {
        return GroceryTime;
    }

    public void setGroceryTime(String groceryTime) {
        GroceryTime = groceryTime;
    }

//    public String getGroceryName(String commodity) {
//        return commodity;
//    }
//
//    public String getGroceryPlace(String s) {
//        return s;
//    }
//
//    public String getGroceryPrice(String modal_price) {
//        return modal_price;
//    }
//
//    public Long getGroceryTime(Long timestamp) {
//        return timestamp;
//    }
}

