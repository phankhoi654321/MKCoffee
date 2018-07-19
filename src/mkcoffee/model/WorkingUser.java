/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author maikhanh
 */
public class WorkingUser {
    
    
    private SimpleStringProperty userId;
    private SimpleStringProperty fullName;
    private SimpleStringProperty email;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty startOfDate;
    private SimpleStringProperty startOfTime;
    private SimpleStringProperty endOfDate;
    private SimpleStringProperty endOfTime;
    
    public WorkingUser() {
    this.userId = new SimpleStringProperty();
    this.startOfDate = new SimpleStringProperty();
    this.startOfTime = new SimpleStringProperty();
    this.endOfDate = new SimpleStringProperty();
    this.endOfTime = new SimpleStringProperty();
    }
    
    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
    
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }
    
    public String getUserId() {
        return userId.get();
    }

    public void setUserId(String userId) {
        this.userId.set(userId);
    }
    
    public String getStartOfDate() {
        return startOfDate.get();
    }

    public void setStartOfDate(String startOfDate) {
        this.startOfDate.set(startOfDate);
    }
    
    public String getStartOfTime() {
        return startOfTime.get();
    }

    public void setStartOfTime(String startOfTime) {
        this.startOfTime.set(startOfTime);
    }
    
    public String getEndOfDate() {
        return endOfDate.get();
    }

    public void setEndOfDate(String endOfDate) {
        this.endOfDate.set(endOfDate);
    }
    
    public String getEndOfTime() {
        return endOfTime.get();
    }

    public void setEndOfTime(String endOfTime) {
        this.endOfTime.set(endOfTime);
    }
    
    
    
}
