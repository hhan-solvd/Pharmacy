package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;

@JsonRootName("position")
@XmlRootElement(name = "position")
@XmlAccessorType(XmlAccessType.FIELD)
public class Position {

    @JsonProperty("position_id")
    @XmlAttribute(name = "position_id")
    private int positionID;

    @JsonProperty("name")
    @XmlElement(name = "name")
    private String name;

    @JsonProperty("salary")
    @XmlElement(name = "salary")
    private double salary;

    public Position() {
    }

    public Position(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public int getPositionID() {
        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionID=" + positionID +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
