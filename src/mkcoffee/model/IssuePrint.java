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
public class IssuePrint {
    private String issueId;
    private String employeeId;
    private String productName;
    private String kind;
    private String unit;
    private String price;
    private String outputStock;
    private String date;

    public IssuePrint() {
    }

    public IssuePrint(String issueId, String employeeId, String productName, String kind, String unit, String price, String outputStock, String date) {
        this.issueId = issueId;
        this.employeeId = employeeId;
        this.productName = productName;
        this.kind = kind;
        this.unit = unit;
        this.price = price;
        this.outputStock = outputStock;
        this.date = date;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    public String getOutputStock() {
        return outputStock;
    }

    public void setOutputStock(String outputStock) {
        this.outputStock = outputStock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
