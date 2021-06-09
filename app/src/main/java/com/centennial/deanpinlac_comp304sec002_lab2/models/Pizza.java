package com.centennial.deanpinlac_comp304sec002_lab2.models;

import com.centennial.deanpinlac_comp304sec002_lab2.enums.Crust;
import com.centennial.deanpinlac_comp304sec002_lab2.enums.PizzaName;
import com.centennial.deanpinlac_comp304sec002_lab2.enums.Price;
import com.centennial.deanpinlac_comp304sec002_lab2.enums.Size;

public class Pizza {
    private PizzaName name;
    private Size size;
    private Crust crust;
    private Price price;
    private double subTotal;
    private String toppings;

    public Pizza(PizzaName pizzaName, Price price){
        this.name = pizzaName;
        this.size = Size.Small;
        setPrice(price);
    }

    public PizzaName getName() {
        return name;
    }

    public void setName(PizzaName name) {
        this.name = name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;

        //update subTotal
        double subTotal;

        switch (this.size){
            case Large:
                subTotal = price.value + 8.0;
                break;
            case Medium:
                subTotal = price.value + 5.0;
                break;
            case Small: default:
                subTotal = price.value;
                break;
        }

        this.subTotal = subTotal;
    }

    public Crust getCrust() {
        return crust;
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        double subTotal;
        switch (this.size){
            case Large:
                subTotal = price.value + 8.0;
                break;
            case Medium:
                subTotal = price.value + 5.0;
                break;
            case Small: default:
                subTotal = price.value;
                break;
        }

        this.price = price;
        this.subTotal = subTotal;
    }

    public double getSubTotal(){
        return subTotal;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }
}
