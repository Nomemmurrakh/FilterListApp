package com.example.filterlistapp.repository;

import com.example.filterlistapp.factory.ChocolateFactory;
import com.example.filterlistapp.models.Chocolate;

import java.util.ArrayList;
import java.util.List;

public class ChocolateRepository {

    List<Chocolate> chocolates;

    public ChocolateRepository(){
        this.chocolates = new ArrayList<>();

        this.chocolates.add(ChocolateFactory.getChocolate("dark"));
        this.chocolates.add(ChocolateFactory.getChocolate("normal"));
        this.chocolates.add(ChocolateFactory.getChocolate("white"));
        this.chocolates.add(ChocolateFactory.getChocolate("milk"));
    }

    public List<Chocolate> getChocolates(){
        return this.chocolates;
    }
}
