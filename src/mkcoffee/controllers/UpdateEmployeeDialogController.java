/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.controllers;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import mkcoffee.model.Customer;
import mkcoffee.model.User;

/**
 * FXML Controller class
 *
 * @author maikhanh
 */
public class UpdateEmployeeDialogController implements Initializable {
    @FXML
    private TextField fullNameUpdate;
    @FXML
    private TextField emailUpdate;
    @FXML
    private TextField phoneUpdate;
    @FXML
    private DatePicker birthdayUpdate;
    @FXML
    private TextField usernameUpdate;
    @FXML
    private TextField passwordUpdate;
    //gender
    @FXML
         
//    private ComboBox<Label> comboGender;
    private ComboBox<String> comboGender;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        comboGender.getItems().add(new Label("Male"));
//        comboGender.getItems().add(new Label("Female"));
//        comboGender.setEditable(false);
//        comboGender.setPromptText("Gender");
//        comboGender.setConverter(new StringConverter<Label>() {
//            @Override
//            public String toString(Label object) {
//                return object == null ? "" : object.getText();
//            }
//
//            @Override
//            public Label fromString(String string) {
//                return new Label(string);
//            }
//        });
    }  
    
    public void getEmployee(String fullName,String emal, String phone, Date birthday, String gender, String username, String password){
        fullNameUpdate.setText(fullName);
        emailUpdate.setText(emal);
        phoneUpdate.setText(phone);
        birthdayUpdate.setValue(birthday.toLocalDate());
//        comboGender.setValue(new Label(gender));
        comboGender.setValue(gender);
        usernameUpdate.setText(username);
        passwordUpdate.setText(password);
    }
    
    public User getUpdateEmployee() {
        String fullName = fullNameUpdate.getText();
        String email = emailUpdate.getText();
        String phone = phoneUpdate.getText();
        String birthday = birthdayUpdate.getValue().toString();
        String gender = comboGender.getValue();
        String username = usernameUpdate.getText();
        String password = passwordUpdate.getText();

        User updateUser = new User();
        updateUser.setFullName(fullName);
        updateUser.setEmail(email);
        updateUser.setPhoneNumber(phone);
        updateUser.setBirthday(birthday);
        updateUser.setGender(gender);
        updateUser.setUserName(username);
        updateUser.setUserPass(password);

        return updateUser;
    }
    
}
