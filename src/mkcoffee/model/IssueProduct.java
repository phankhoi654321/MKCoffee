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
public class IssueProduct {
    private SimpleStringProperty transactionId;
    private SimpleStringProperty employeeId;
    private SimpleStringProperty productName;
    private SimpleStringProperty kind;
    private SimpleStringProperty unit;
    private SimpleIntegerProperty price;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty dateOfTransaction;
    
    public IssueProduct() {
        this.transactionId = new SimpleStringProperty();
        this.employeeId = new SimpleStringProperty();
        this.productName = new SimpleStringProperty();
        this.kind = new SimpleStringProperty();
        this.unit = new SimpleStringProperty();
        this.price = new SimpleIntegerProperty();
        this.quantity = new SimpleIntegerProperty();
        this.dateOfTransaction = new SimpleStringProperty();
    }
    
    public String getTransactionId() {
        return transactionId.get();
    }

    public void setTransactionId(String value) {
        transactionId.set(value);
    }
    
    public String getEmployeeId() {
        return employeeId.get();
    }

    public void setEmployeeId(String value) {
        employeeId.set(value);
    }
    
    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String value) {
        productName.set(value);
    }
    
    public String getKind() {
        return kind.get();
    }

    public void setKind(String value) {
        kind.set(value);
    }
    
    public String getUnit() {
        return unit.get();
    }

    public void setUnit(String value) {
        unit.set(value);
    }
    
    public Integer getPrice() {
        return price.get();
    }

    public void setPrice(Integer value) {
        price.set(value);
    }
    
    public Integer getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Integer value) {
        quantity.set(value);
    }
    
    public String getDateOfTransaction() {
        return dateOfTransaction.get();
    }

    public void setDateOfTransaction(String value) {
        dateOfTransaction.set(value);
    }
}
