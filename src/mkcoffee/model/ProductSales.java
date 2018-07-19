/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.model;

import java.text.DecimalFormat;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author maikhanh
 */
public class ProductSales {

    private SimpleStringProperty transactionId;
    private SimpleStringProperty orderId;
    private SimpleStringProperty employeeId;
    private SimpleStringProperty customerId;
    private SimpleStringProperty productId;
    private SimpleStringProperty dateOfTransaction;
    private SimpleStringProperty timeOfTransaction;
    private SimpleIntegerProperty monthOfTransaction;
    private SimpleStringProperty productName;
    private SimpleStringProperty kind;
    private SimpleStringProperty unit;
    private SimpleIntegerProperty price;
    private SimpleIntegerProperty quantity;
    private SimpleIntegerProperty totalPrice;

    public ProductSales() {
        this.transactionId = new SimpleStringProperty();
        this.orderId = new SimpleStringProperty();
        this.employeeId = new SimpleStringProperty();
        this.customerId = new SimpleStringProperty();
        this.productId = new SimpleStringProperty();
        this.dateOfTransaction = new SimpleStringProperty();
        this.timeOfTransaction = new SimpleStringProperty();
        this.monthOfTransaction = new SimpleIntegerProperty();
        this.productName = new SimpleStringProperty();
        this.kind = new SimpleStringProperty();
        this.unit = new SimpleStringProperty();
        this.price = new SimpleIntegerProperty();
        this.quantity = new SimpleIntegerProperty();
        this.totalPrice = new SimpleIntegerProperty();
    }

//    public ProductSales(String productName, String kind, String unit, Integer price, Integer quantity, Integer totalPrice) {
//        this.productName.set(productName);
//        this.kind.set(kind);
//        this.unit.set(unit);
//        this.price.set(price);
//        this.quantity.set(quantity);
//        this.totalPrice.set(totalPrice);
//    }
    
    public String getTimeOfTransaction() {
        return timeOfTransaction.get();
    }

    public void setTimeOfTransaction(String value) {
        this.timeOfTransaction.set(value);
    }
    
    public Integer getMonthOfTransaction() {
        return monthOfTransaction.get();
    }

    public void setMonthOfTransaction(Integer value) {
        this.monthOfTransaction.set(value);
    }
    
    public String getOrderId() {
        return orderId.get();
    }

    public void setOrderId(String orderId) {
        this.orderId.set(orderId);
    }
    
    public String getDateOfTransaction() {
        return dateOfTransaction.get();
    }

    public void setDateOfTransaction(String value) {
        this.dateOfTransaction.set(value);
    }
    
    public String getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(String value) {
        this.customerId.set(value);
    }
    
    public String getProductId() {
        return productId.get();
    }

    public void setProductId(String value) {
        this.productId.set(value);
    }
    
    public String getEmployeeId() {
        return employeeId.get();
    }

    public void setEmployeeId(String value) {
        this.employeeId.set(value);
    }
    
    public String getTransactionId() {
        return transactionId.get();
    }

    public void setTransactionId(String value) {
        this.transactionId.set(value);
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

    public Integer getPrice() {
        return price.get();
    }

    public void setPrice(Integer price) {
        this.price.set(price);
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Integer quantity) {
        this.quantity.set(quantity);
    }

    public Integer getTotalPrice() {
        return totalPrice.get();
    }

    public void setTotalPrice(Integer price, Integer quantity) {
        this.totalPrice.set(price * quantity);
    }
    public void setTotalPrice(Integer price) {
        this.totalPrice.set(price);
    }
    
    private static String priceWithDecimal(Integer price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price);
    }
}
