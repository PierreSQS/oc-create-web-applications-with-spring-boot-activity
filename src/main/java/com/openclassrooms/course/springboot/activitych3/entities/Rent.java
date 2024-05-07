package com.openclassrooms.course.springboot.activitych3.entities;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Rent {

    @CsvBindByName
    private int id;

    @CsvBindByName
    private String city;

    @CsvBindByName
    private String country;

    @CsvBindByName(column = "rent")
    private Double rentPrice;

    public Rent() {
    }

    public Rent(int id, String city, String country, Double rent) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.rentPrice = rent;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", rentPrice=" + rentPrice +
                '}';
    }
}
