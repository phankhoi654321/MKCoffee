/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.model;

import java.sql.Date;
import java.util.Calendar;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author maikhanh
 */
public class User {

    private SimpleStringProperty userId;
    private SimpleStringProperty fullName;
    private SimpleStringProperty email;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty birthday;
    private SimpleStringProperty gender;
    private SimpleStringProperty userName;
    private SimpleStringProperty userPass; 
    
    
//    private String userName;
//    private String userPass;
//    private String firstName;
//    private String LastName;
//    private String gender;
//    private int phoneNumber;
    
    public User() {
    this.userId = new SimpleStringProperty();
    this.fullName = new SimpleStringProperty();
    this.email = new SimpleStringProperty();
    this.phoneNumber = new SimpleStringProperty();
    this.birthday = new SimpleStringProperty();
    this.gender = new SimpleStringProperty();
    this.userName = new SimpleStringProperty();
    this.userPass = new SimpleStringProperty();
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getUserPass() {
        return userPass.get();
    }

    public void setUserPass(String userPass) {
        this.userPass.set(userPass);
    }
    
    public String getUserId() {
        return userId.get();
    }

    public void setUserId(String userId) {
        this.userId.set(userId);
    }
    
    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    } 
    
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    } 
    
    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    } 
    
    public String getBirthday() {
        return birthday.get();
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    } 
    
    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    
    
}
