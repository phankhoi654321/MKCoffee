/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author maikhanh
 */
public class Customer {
    private SimpleStringProperty customerId = new SimpleStringProperty("");
    private SimpleStringProperty customerName = new SimpleStringProperty("");
    private SimpleStringProperty customerAdd = new SimpleStringProperty("");
    private SimpleStringProperty customerPhone = new SimpleStringProperty();
    private SimpleStringProperty customerEmail = new SimpleStringProperty("");
    
    public Customer(){
        
    }

    public Customer(String customerId, String customerName, String customerAdd, String customerPhone, String customerEmail) {
        this.customerId.set(customerId);
        this.customerName.set(customerName);
        this.customerAdd.set(customerAdd);
        this.customerPhone.set(customerPhone);
        this.customerEmail.set(customerEmail);
        
    }

    public String getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(String value) {
        customerId.set(value);
    }
    
    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String value) {
        customerName.set(value);
    }
    
    public String getCustomerAdd() {
        return customerAdd.get();
    }

    public void setCustomerAdd(String value) {
        customerAdd.set(value);
    }
    
    public String getCustomerPhone() {
        return customerPhone.get();
    }

    public void setCustomerPhone(String value) {
        customerPhone.set(value);
    }
    
    public String getCustomerEmail() {
        return customerEmail.get();
    }

    public void setCustomerEmail(String value) {
        customerEmail.set(value);
    }

    
    
    
}
