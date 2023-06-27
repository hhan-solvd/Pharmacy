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

    private DrugCategory(Builder builder) {
        this.categoryID = builder.categoryID;
        this.name = builder.name;
    }

    public static class Builder {
        private int categoryID;
        private String name;

        public Builder withCategoryID(int categoryID) {
            this.categoryID = categoryID;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public DrugCategory build() {
            return new DrugCategory(this);
        }
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
