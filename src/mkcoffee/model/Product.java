/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author maikhanh
 */
public class Product extends RecursiveTreeObject<Product> {

    public SimpleIntegerProperty iD;
    public SimpleStringProperty productID;
    public SimpleStringProperty productName;
    public SimpleStringProperty productKind;
    public SimpleStringProperty productUnit;
    public SimpleIntegerProperty productPrice;
    public SimpleStringProperty productCategory;
    public SimpleIntegerProperty productStock;
    public SimpleIntegerProperty productStockReceipt;

    public Product() {
        this.iD = new SimpleIntegerProperty();
        this.productID = new SimpleStringProperty();
        this.productName = new SimpleStringProperty();
        this.productKind = new SimpleStringProperty();
        this.productUnit = new SimpleStringProperty();
        this.productPrice = new SimpleIntegerProperty();
        this.productCategory = new SimpleStringProperty();
        this.productStock = new SimpleIntegerProperty();
        this.productStockReceipt = new SimpleIntegerProperty();
    }

    public Product(String productID, String productName, String productKind, String productUnit,Integer productPrice, String productCategory, Integer productStock) {
        this.productID = new SimpleStringProperty(productID);
        this.productName = new SimpleStringProperty(productName);
        this.productKind = new SimpleStringProperty(productKind);
        this.productUnit = new SimpleStringProperty(productUnit);
        this.productPrice = new SimpleIntegerProperty(productPrice);
        this.productCategory = new SimpleStringProperty(productCategory);
        this.productStock = new SimpleIntegerProperty(productStock);
    }

    public String getProductID() {
        return productID.get();
    }

    public void setProductID(String value) {
        productID.set(value);
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String value) {
        productName.set(value);
    }
    
    public String getProductKind() {
        return productKind.get();
    }

    public void setProductKind(String value) {
        productKind.set(value);
    }
    
    public String getProductUnit() {
        return productUnit.get();
    }

    public void setProductUnit(String value) {
        productUnit.set(value);
    }

    public Integer getProductPrice() {
        return productPrice.get();
    }

    public void setProductPrice(int value) {
        productPrice.set(value);
    }

    public String getProductCategory() {
        return productCategory.get();
    }

    public void setProductCategory(String value) {
        productCategory.set(value);
    }

    public Integer getProductStock() {
        return productStock.get();
    }

    public void setProductStock(int value) {
        productStock.set(value);
    }
    
    public Integer getProductStockReceipt() {
        return productStockReceipt.get();
    }

    public void setProductStockReceipt(int value) {
        productStockReceipt.set(value);
    }
    
    public Integer getID() {
        return iD.get();
    }

    public void setID(int value) {
        iD.set(value);
    }

}
