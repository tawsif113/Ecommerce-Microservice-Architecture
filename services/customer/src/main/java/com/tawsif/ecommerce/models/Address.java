package com.tawsif.ecommerce.models;

import org.springframework.validation.annotation.Validated;

@Validated
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;

    public Address(String street, String houseNumber, String zipCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode=zipCode;
    }
    @Override
    public String toString(){
        return "Address [street=" + street + ", houseNumber=" + houseNumber + ", zipCode=" + zipCode + "]";
    }
}
