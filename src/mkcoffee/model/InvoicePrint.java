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
public class InvoicePrint {
    private String invoiceId;
    private String employeeId;
    private String employeeName;
    private String customerId;
    private String customerName;
    private String date;
    private String time;
    private String totalInvoice;

    public InvoicePrint() {
    }

    public InvoicePrint(String invoiceId, String employeeId, String employeeName, String customerId, String customerName, String date, String time, String totalInvoice) {
        this.invoiceId = invoiceId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.customerId = customerId;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        this.totalInvoice = totalInvoice;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalInvoice() {
        return totalInvoice;
    }

    public void setTotalInvoice(String totalInvoice) {
        this.totalInvoice = totalInvoice;
    }
    
    
    
}
