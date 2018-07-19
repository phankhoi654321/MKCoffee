/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import mkcoffee.model.User;
import mkcoffee.model.WorkingUser;

/**
 * FXML Controller class
 *
 * @author maikhanh
 */
public class EmployeeWorkingController implements Initializable {

    @FXML
    private Label userId;
    @FXML
    private Label fullName;
    @FXML
    private Label phoneNumber;
    @FXML
    private Label email;

    @FXML
    private TableView<WorkingUser> workingUserTableSearch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void myEmployeeSearch(String value, ObservableList<WorkingUser> workingUsers, ObservableList<User> users) {
        userId.setText(value);
        workingUserTableSearch.setItems(workingUsers);
        fullName.setText(users.get(0).getFullName());
        phoneNumber.setText(users.get(0).getPhoneNumber());
        email.setText(users.get(0).getEmail());
    }

}
