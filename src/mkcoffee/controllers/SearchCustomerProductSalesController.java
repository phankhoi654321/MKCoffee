/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import mkcoffee.model.CustomerBuy;

/**
 * FXML Controller class
 *
 * @author maikhanh
 */
public class SearchCustomerProductSalesController implements Initializable {
    
    @FXML
    private Label customerId;
    @FXML
    private Label customerName;
    @FXML
    private Label customerAdd;
    @FXML
    private Label customerPhone;
    @FXML
    private Label customerEmail;
    @FXML
    private Label customerTotalPayment;
    
    @FXML
    private TableView customerBuyTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        showCustomerBuyTableView();
//        System.out.println(customerId.getText());
    }    
    
    public void myCustomerId(String value, ObservableList<CustomerBuy> customerBuys) {
        customerId.setText(value);
        customerBuyTable.setItems(customerBuys);
        customerName.setText(customerBuys.get(0).getCustomerName());
        customerAdd.setText(customerBuys.get(0).getCustomerAdd());
        customerPhone.setText(customerBuys.get(0).getCustomerPhone());
        customerEmail.setText(customerBuys.get(0).getCustomerEmail());
        Integer total = 0;
        for (int i = 0; i < customerBuys.size(); i++) {
            total += customerBuys.get(i).getTotalPrice();
        }
        customerTotalPayment.setText(total.toString());
        
    }
    
    public void showCustomerBuyTableView() {
        ObservableList<CustomerBuy> customerBuys = FXCollections.observableArrayList(Datasource.getInstance().queryCustomerBuy(customerId.getText()));
    }
    
}
