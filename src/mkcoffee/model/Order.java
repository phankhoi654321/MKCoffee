/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author maikhanh
 */
public class Order {
    private SimpleStringProperty productName;
    private SimpleStringProperty unit;
    private SimpleIntegerProperty quantity;
    private SimpleIntegerProperty price;
    private SimpleIntegerProperty totalPrice;

    public Order() {
        this.productName = new SimpleStringProperty();
        this.unit = new SimpleStringProperty();
        this.quantity = new SimpleIntegerProperty();
        this.price = new SimpleIntegerProperty();
        this.totalPrice = new SimpleIntegerProperty(price.get() * quantity.get());
    }


    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String value) {
        productName.set(value);
    }

    public String getUnit() {
        return unit.get();
    }

    public void setUnit(String value) {
        unit.set(value);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int value) {
        quantity.set(value);
    }
    
    public int getPrice() {
        return price.get();
    }

    public void setPrice(int value) {
        price.set(value);
    }
    
    


    
    

    
    
    
    
    
    

    
    
    
}
