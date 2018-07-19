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
public class ReceiptPrint {
    private String receiptProductID;
    private String productId;
    private String receiptEmployeeId;
    private String receiptProductName;
    private String receiptProductKind;
    private String receiptProductUnit;
    private String receiptProductPrice;
    private String receiptProductStock;
    private String receiptProductdateOfTransaction;

    public ReceiptPrint() {
    }

    public ReceiptPrint(String receiptProductID, String productId, String receiptEmployeeId, String receiptProductName, String receiptProductKind, String receiptProductUnit, String receiptProductPrice, String receiptProductStock, String receiptProductdateOfTransaction) {
        this.receiptProductID = receiptProductID;
        this.productId = productId;
        this.receiptEmployeeId = receiptEmployeeId;
        this.receiptProductName = receiptProductName;
        this.receiptProductKind = receiptProductKind;
        this.receiptProductUnit = receiptProductUnit;
        this.receiptProductPrice = receiptProductPrice;
        this.receiptProductStock = receiptProductStock;
        this.receiptProductdateOfTransaction = receiptProductdateOfTransaction;
    }

    public String getReceiptProductID() {
        return receiptProductID;
    }

    public void setReceiptProductID(String receiptProductID) {
        this.receiptProductID = receiptProductID;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getReceiptEmployeeId() {
        return receiptEmployeeId;
    }

    public void setReceiptEmployeeId(String receiptEmployeeId) {
        this.receiptEmployeeId = receiptEmployeeId;
    }

    public String getReceiptProductName() {
        return receiptProductName;
    }

    public void setReceiptProductName(String receiptProductName) {
        this.receiptProductName = receiptProductName;
    }

    public String getReceiptProductKind() {
        return receiptProductKind;
    }

    public void setReceiptProductKind(String receiptProductKind) {
        this.receiptProductKind = receiptProductKind;
    }

    public String getReceiptProductUnit() {
        return receiptProductUnit;
    }

    public void setReceiptProductUnit(String receiptProductUnit) {
        this.receiptProductUnit = receiptProductUnit;
    }

    public String getReceiptProductPrice() {
        return receiptProductPrice;
    }

    public void setReceiptProductPrice(String receiptProductPrice) {
        this.receiptProductPrice = receiptProductPrice;
    }

    public String getReceiptProductStock() {
        return receiptProductStock;
    }

    public void setReceiptProductStock(String receiptProductStock) {
        this.receiptProductStock = receiptProductStock;
    }

    public String getReceiptProductdateOfTransaction() {
        return receiptProductdateOfTransaction;
    }

    public void setReceiptProductdateOfTransaction(String receiptProductdateOfTransaction) {
        this.receiptProductdateOfTransaction = receiptProductdateOfTransaction;
    }
    
    
}
