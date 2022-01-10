package com.example.filterlistapp.factory;

import com.example.filterlistapp.models.Chocolate;

public class ChocolateFactory {

    public static Chocolate getChocolate(String type){
        Chocolate chocolate;
        switch (type){
            case "dark":
                chocolate = new Chocolate(1, "dark", "Dark Chocolate", 20.0);
            break;
            case "white":
                chocolate = new Chocolate(1, "white", "White Chocolate", 50.0);
            break;
            case "compound":
                chocolate = new Chocolate(1, "compound", "Dark Chocolate", 100.0);
            break;
            case "milk":
                chocolate = new Chocolate(1, "milk", "Milk Chocolate", 150.0);
            break;
            default:
                chocolate = new Chocolate(1, "normal", "Normal Chocolate", 20.0);
        }
        return chocolate;
    }
}
