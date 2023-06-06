package com.solvd.app.models;

public class DrugCategory {
    private int categoryID;
    private String name;

    public DrugCategory() {
    }

    public DrugCategory(String name) {
        this.name = name;
    }

    public DrugCategory(int categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DrugCategory{" +
                "categoryID=" + categoryID +
                ", name='" + name + '\'' +
                '}';
    }
}
