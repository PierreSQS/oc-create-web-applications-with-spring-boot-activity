package com.openclassrooms.course.springboot.activitych3.entities;

import com.opencsv.bean.CsvBindByName;

public class Rent {

    @CsvBindByName
    private int id;

    @CsvBindByName
    private String city;

    @CsvBindByName
    private String country;

    @CsvBindByName
    private Double rentPrice;

    public Rent() {
    }

    public Rent(int id, String city, String country, Double rent) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.rentPrice = rent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }
}
