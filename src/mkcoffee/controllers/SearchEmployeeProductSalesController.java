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
import mkcoffee.model.EmployeeSale;

/**
 * FXML Controller class
 *
 * @author maikhanh
 */
public class SearchEmployeeProductSalesController implements Initializable {

    @FXML
    private Label employeeId;
    @FXML
    private Label employeeName;
    @FXML
    private Label employeePhone;
    @FXML
    private Label employeeEmail;
    @FXML
    private Label employeeTotalSale;
    
    @FXML
    private TableView<EmployeeSale> employeeSaleTable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void myEmployeeId(String value, ObservableList<EmployeeSale> employeeSales) {
        employeeId.setText(value);
        employeeSaleTable.setItems(employeeSales);
        employeeName.setText(employeeSales.get(0).getEmployeeName());
        employeePhone.setText(employeeSales.get(0).getEmployeePhone());
        employeeEmail.setText(employeeSales.get(0).getEmployeeEmail());

        Integer total = 0;
        for (int i = 0; i < employeeSales.size(); i++) {
            total += employeeSales.get(i).getTotalPrice();
        }
        employeeTotalSale.setText(total.toString());
    }
}
