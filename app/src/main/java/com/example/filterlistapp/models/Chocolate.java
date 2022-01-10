package com.example.filterlistapp.models;

import java.util.Objects;

public class Chocolate {

    private int id;
    private String type;
    private String name;
    private Double price;

    public Chocolate(int id, String type, String name, Double price) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chocolate chocolate = (Chocolate) o;
        return Objects.equals(getType(), chocolate.getType()) && Objects.equals(getName(), chocolate.getName()) && Objects.equals(getPrice(), chocolate.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getName(), getPrice());
    }
}
