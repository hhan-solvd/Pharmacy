package com.solvd.app.models;

public class Position {
    private int positionID;
    private String name;
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
