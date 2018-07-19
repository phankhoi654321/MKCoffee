/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author maikhanh
 */
public class EmployeeSale {

    private SimpleStringProperty employeeId;
    private SimpleStringProperty employeeName;
    private SimpleStringProperty employeePhone;
    private SimpleStringProperty employeeEmail;
    private SimpleStringProperty dateOfTransaction;
    private SimpleStringProperty timeOfTransaction;
    private SimpleStringProperty productName;
    private SimpleStringProperty kind;
    private SimpleStringProperty unit;
    private SimpleIntegerProperty price;
    private SimpleIntegerProperty quantity;
    private SimpleIntegerProperty totalPrice;

    public EmployeeSale() {
        this.employeeId = new SimpleStringProperty();
        this.employeeName = new SimpleStringProperty();
        this.employeePhone = new SimpleStringProperty();
        this.employeeEmail = new SimpleStringProperty();
        this.dateOfTransaction = new SimpleStringProperty();
        this.timeOfTransaction = new SimpleStringProperty();
        this.productName = new SimpleStringProperty();
        this.kind = new SimpleStringProperty();
        this.unit = new SimpleStringProperty();
        this.price = new SimpleIntegerProperty();
        this.quantity = new SimpleIntegerProperty();
        this.totalPrice = new SimpleIntegerProperty();
    }
    
       
    public String getEmployeeId() {
        return employeeId.get();
    }

    public void setEmployeeId(String value) {
        employeeId.set(value);
    }
    
    public String getEmployeeName() {
        return employeeName.get();
    }

    public void setEmployeeName(String value) {
        employeeName.set(value);
    }
    
    public String getEmployeePhone() {
        return employeePhone.get();
    }

    public void setEmployeePhone(String value) {
        employeePhone.set(value);
    }
    
    public String getEmployeeEmail() {
        return employeeEmail.get();
    }

    public void setEmployeeEmail(String value) {
        employeeEmail.set(value);
    }
    
    public String getKind() {
        return kind.get();
    }

    public void setKind(String kind) {
        this.kind.set(kind);
    }

    public String getUnit() {
        return unit.get();
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(Integer price) {
        this.price.set(price);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Integer quantity) {
        this.quantity.set(quantity);
    }

    public int getTotalPrice() {
        return totalPrice.get();
    }

    public void setTotalPrice(Integer price) {
        this.totalPrice.set(price);
    }
    
    public String getDateOfTransaction() {
        return dateOfTransaction.get();
    }

    public void setDateOfTransaction(String value) {
        this.dateOfTransaction.set(value);
    }
    
    public String getTimeOfTransaction() {
        return timeOfTransaction.get();
    }

    public void setTimeOfTransaction(String value) {
        this.timeOfTransaction.set(value);
    }

}
