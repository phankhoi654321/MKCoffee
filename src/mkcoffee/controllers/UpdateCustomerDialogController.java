/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import mkcoffee.model.Customer;

/**
 * FXML Controller class
 *
 * @author maikhanh
 */
public class UpdateCustomerDialogController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private TextField nameUpdate;
    @FXML
    private TextField addUpdate;
    @FXML
    private TextField phoneUpdate;
    @FXML
    private TextField emailUpdate;

    public void getCustomer(String name, String add, String phone, String email) {
        nameUpdate.setText(name);
        addUpdate.setText(add);
        phoneUpdate.setText(phone);
        emailUpdate.setText(email);

    }

    public Customer getNewCustomer() {
        if (!(nameUpdate.getText().isEmpty() && addUpdate.getText().isEmpty() && phoneUpdate.getText().isEmpty() && emailUpdate.getText().isEmpty())) {
            String customerName = nameUpdate.getText();
            String customerAdd = addUpdate.getText();
            String customerPhone = phoneUpdate.getText();
            String customerEmail = emailUpdate.getText();

            Customer updateCustomer = new Customer();
            updateCustomer.setCustomerName(customerName);
            updateCustomer.setCustomerAdd(customerAdd);
            updateCustomer.setCustomerPhone(customerPhone);
            updateCustomer.setCustomerEmail(customerEmail);

            return updateCustomer;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Customer Information");
            alert.setHeaderText("Customer Register form");
            alert.setContentText("Please fill in all the field!");
            Optional<ButtonType> choose = alert.showAndWait();
            
            return null;
        }

    }

}
