package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;
import java.util.List;

@JsonRootName("pharmacy")
@XmlRootElement(name = "pharmacy")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pharmacy {

    @JsonProperty("pharmacy_id")
    @XmlAttribute(name = "pharmacy_id")
    private int pharmacyID;

    @JsonProperty("name")
    @XmlElement(name = "name")
    private String name;

    @JsonProperty("address")
    @XmlElement(name = "address")
    private String address;

    @JsonProperty("phone_number")
    @XmlElement(name = "phone_number")
    private int phoneNumber;

    @JsonProperty("staff")
    @XmlElementWrapper(name = "staff")
    @XmlElement(name = "staff", type = Staff.class)
    private List<Staff> staff;

    public Pharmacy() {
    }

    public Pharmacy(String name, String address, int phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(int pharmacyID) {
        this.pharmacyID = pharmacyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "pharmacyID=" + pharmacyID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", staff=" + staff +
                '}';
    }
}
