package com.cellulant.validation.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class ValidateModel implements Serializable {

    private static final long serialVersionUID = -305726463442998985L;

    @Id
    private String id;

    private String phoneNumber;

    private String country;

    private String fullName;

    public ValidateModel(String id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public ValidateModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "ValidateModel{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
