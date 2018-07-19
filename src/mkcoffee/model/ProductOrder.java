/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.model;

/**
 *
 * @author maikhanh
 */

//for print
public class ProductOrder {
    private String productName;
    private String kind;
    private String unit;
    private String price;
    private String amount;
    private String quantity;

    public ProductOrder() {
    }
    
    

    public ProductOrder(String productName, String kind, String quantity, String unit, String price, String amount) {
        this.productName = productName;
        this.kind = kind;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.amount = amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    
}
