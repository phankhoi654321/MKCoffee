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
public class OrderList {

    public SimpleStringProperty orderId;
    public SimpleStringProperty employeeId;
    public SimpleStringProperty customerId;
    public SimpleStringProperty employeeName;
    public SimpleStringProperty customerName;
    public SimpleStringProperty timeOrder;
    public SimpleStringProperty dateOrder;
    public SimpleIntegerProperty totalInvoice;
    public SimpleIntegerProperty monthOfTransaction;

    public OrderList() {
        this.orderId = new SimpleStringProperty();
        this.employeeId = new SimpleStringProperty();
        this.employeeName = new SimpleStringProperty();
        this.customerId = new SimpleStringProperty();
        this.customerName = new SimpleStringProperty();
        this.timeOrder = new SimpleStringProperty();
        this.dateOrder = new SimpleStringProperty();
        this.totalInvoice = new SimpleIntegerProperty();
        this.monthOfTransaction = new SimpleIntegerProperty();
    }
    
    public String getOrderId() {
        return orderId.get();
    }

    public void setOrderId(String orderId) {
        this.orderId.set(orderId);
    }

    public String getEmployeeName() {
        return employeeName.get();
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName.set(employeeName);
    }
    
    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public String getEmployeeId() {
        return employeeId.get();
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId.set(employeeId);
    }

    public String getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(String employeeId) {
        this.customerId.set(employeeId);
    }
    
    public String getTimeOrder() {
        return timeOrder.get();
    }

    public void setTimeOrder(String timeOrder) {
        this.timeOrder.set(timeOrder);
    }
    public String getDateOrder() {
        return dateOrder.get();
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder.set(dateOrder);
    }
    
    public Integer getTotalInvoice() {
        return totalInvoice.get();
    }

    public void setTotalInvoice(Integer totalInvoice) {
        this.totalInvoice.set(totalInvoice);
    }
    
    public Integer getMonthOfTransaction() {
        return monthOfTransaction.get();
    }

    public void setMonthOfTransaction(Integer value) {
        this.monthOfTransaction.set(value);
    }

}
