package com.centennial.deanpinlac_comp304sec002_lab2.enums;

public enum PizzaName {
    Canadian("Canadian Pizza"),
    Chicken("Chicken Caesar"),
    Hawaiian("Hawaiian Pizza"),
    Smokey("Smokey Maple Bacon"),
    Veggie("Veggie Lover's");

    public final String value;

    private PizzaName(String name){
        this.value = name;
    }

    public boolean equals(String name){
        return this.value.equals(name);
    }
}
