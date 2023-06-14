package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;

@JsonRootName("drug_category")
@XmlRootElement(name = "drug_category")
@XmlAccessorType(XmlAccessType.FIELD)
public class DrugCategory {

    @JsonProperty("category_id")
    @XmlAttribute(name = "category_id")
    private int categoryID;

    @JsonProperty("name")
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
