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
public class ReceiptProduct {
    private SimpleStringProperty receiptProductID;
    private SimpleStringProperty productId;
    private SimpleStringProperty receiptEmployeeId;
    private SimpleStringProperty receiptProductName;
    private SimpleStringProperty receiptProductKind;
    private SimpleStringProperty receiptProductUnit;
    private SimpleIntegerProperty receiptProductPrice;
    private SimpleIntegerProperty receiptProductStock;
    private SimpleStringProperty receiptProductdateOfTransaction;
    
    public ReceiptProduct() {
        this.receiptProductID = new SimpleStringProperty();
        this.productId = new SimpleStringProperty();
        this.receiptEmployeeId = new SimpleStringProperty();
        this.receiptProductName = new SimpleStringProperty();
        this.receiptProductKind = new SimpleStringProperty();
        this.receiptProductUnit = new SimpleStringProperty();
        this.receiptProductPrice = new SimpleIntegerProperty();
        this.receiptProductStock = new SimpleIntegerProperty();
        this.receiptProductdateOfTransaction = new SimpleStringProperty();
    }
    
    public String getReceiptProductID() {
        return receiptProductID.get();
    }

    public void setReceiptProductID(String value) {
        receiptProductID.set(value);
    }
    
    public String getProductId() {
        return productId.get();
    }

    public void setProductId(String value) {
        productId.set(value);
    }
    
    public String getReceiptEmployeeId() {
        return receiptEmployeeId.get();
    }

    public void setReceiptEmployeeId(String value) {
        receiptEmployeeId.set(value);
    }
    
    public String getReceiptProductName() {
        return receiptProductName.get();
    }

    public void setReceiptProductName(String value) {
        receiptProductName.set(value);
    }
    
    public String getReceiptProductKind() {
        return receiptProductKind.get();
    }

    public void setReceiptProductKind(String value) {
        receiptProductKind.set(value);
    }
    
    public String getReceiptProductUnit() {
        return receiptProductUnit.get();
    }

    public void setReceiptProductUnit(String value) {
        receiptProductUnit.set(value);
    }
    
    public Integer getReceiptProductPrice() {
        return receiptProductPrice.get();
    }

    public void setReceiptProductPrice(Integer value) {
        receiptProductPrice.set(value);
    }
    
    public Integer getReceiptProductStock() {
        return receiptProductStock.get();
    }

    public void setReceiptProductStock(Integer value) {
        receiptProductStock.set(value);
    }
    
    public String getReceiptProductdateOfTransaction() {
        return receiptProductdateOfTransaction.get();
    }

    public void setReceiptProductdateOfTransaction(String value) {
        receiptProductdateOfTransaction.set(value);
    }
}
