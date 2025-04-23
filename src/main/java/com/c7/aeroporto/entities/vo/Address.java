package com.c7.aeroporto.entities.vo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private Integer number;
    private Integer zipCode;
    private String city;
    private String state;

    public Address(){}

    public Address(String street, Integer number, Integer zipCode, String city, String state) {
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
