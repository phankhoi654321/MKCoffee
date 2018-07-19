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
import mkcoffee.model.ProductSales;

/**
 * FXML Controller class
 *
 * @author maikhanh
 */
public class OrderInvoiceDialogController implements Initializable {

    @FXML
    private Label orderId;
    @FXML
    private Label employeeId;
    @FXML
    private Label customerId;
    @FXML
    private Label dateTime;
    @FXML
    private Label totalInvoice;
    @FXML
    private Label employeeName;

    @FXML
    private TableView<ProductSales> orderInvoiceTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void myOrderInvoice(ObservableList<ProductSales> orderInvoice) {
        orderId.setText(orderInvoice.get(0).getOrderId());
        employeeId.setText(orderInvoice.get(0).getEmployeeId());
        customerId.setText(Datasource.getInstance().searchCustomerNameById(orderInvoice.get(0).getCustomerId()));
        dateTime.setText(orderInvoice.get(0).getDateOfTransaction());
        employeeName.setText(Datasource.getInstance().searchEmployeeById(orderInvoice.get(0).getEmployeeId()).getFullName());
        Integer total = 0;
        
        for (int i = 0; i < orderInvoice.size(); i++) {
            total += orderInvoice.get(i).getTotalPrice();
        }
        totalInvoice.setText(total.toString());
        orderInvoiceTable.setItems(orderInvoice);

    }


}
