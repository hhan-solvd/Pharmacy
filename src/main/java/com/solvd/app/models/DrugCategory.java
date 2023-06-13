package com.solvd.app.models;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "drug_category")
@XmlAccessorType(XmlAccessType.FIELD)
public class DrugCategory {

    @XmlAttribute(name = "category_id")
    private int categoryID;

    @XmlElement(name = "name")
    private String name;

    public DrugCategory() {
    }

    public DrugCategory(String name) {
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
