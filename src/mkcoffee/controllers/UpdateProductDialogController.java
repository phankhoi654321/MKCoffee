/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import mkcoffee.model.Product;

/**
 * FXML Controller class
 *
 * @author maikhanh
 */
public class UpdateProductDialogController implements Initializable {

    @FXML
    private TextField nameUpdate;
    @FXML
    private TextField kindUpdate;
    @FXML
    private TextField priceUpdate;
    @FXML
    private TextField categoryUpdate;
    @FXML
    private TextField stockUpdate;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void getProduct(String name,String kind, Integer price, String category, Integer stock){
        nameUpdate.setText(name);
        kindUpdate.setText(kind);
        priceUpdate.setText(price.toString());
        categoryUpdate.setText(category);
        stockUpdate.setText(stock.toString());
                
    }
    
    public Product getUpdateProduct() {
        
        String productName = nameUpdate.getText();
        String productKind = kindUpdate.getText();
        String productUnit = "pcs";
        Integer productPrice = Integer.parseInt(priceUpdate.getText());
        String productCategory = categoryUpdate.getText();
        List<Product> products = Datasource.getInstance().searchProductByNameKind(productName, productKind);
        Integer productStock = products.get(0).getProductStock() + Integer.parseInt(stockUpdate.getText());
        Integer productStockReceipt = Integer.parseInt(stockUpdate.getText());
        
        
        Product updateProduct = new Product();
        updateProduct.setProductName(productName);
        updateProduct.setProductKind(productKind);
        updateProduct.setProductUnit(productUnit);
        updateProduct.setProductPrice(productPrice);
        updateProduct.setProductCategory(productCategory);
        updateProduct.setProductStock(productStock);
        updateProduct.setProductStockReceipt(productStockReceipt);

        return updateProduct;
    }
    
}
