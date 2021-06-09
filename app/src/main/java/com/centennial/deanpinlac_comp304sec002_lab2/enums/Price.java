package com.centennial.deanpinlac_comp304sec002_lab2.enums;

public enum Price {
    Canadian(4.99),
    Chicken(5.99),
    Hawaiian(4.99),
    Smokey(6.49),
    Veggie(4.99);

    public final double value;

    private Price(double value){
        this.value = value;
    }
}
