package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;

@JsonRootName("person")
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @JsonProperty("person_id")
    @XmlAttribute(name = "person_id")
    private int personID;

    @JsonProperty("name")
    @XmlElement(name = "name")
    private String name;

    @JsonProperty("address")
    @XmlElement(name = "address")
    private String address;

    @JsonProperty("phone_number")
    @XmlElement(name = "phone_number")
    private int phoneNumber;

    @JsonProperty("email")
    @XmlElement(name = "email")
    private String email;

    @JsonProperty("gender")
    @XmlElement(name = "gender")
    private String gender;

    public Person() {
    }

    public Person(String name, String address, int phoneNumber, String email, String gender) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personID=" + personID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
