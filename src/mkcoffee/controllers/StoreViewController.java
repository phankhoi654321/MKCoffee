/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import mkcoffee.main.MKCoffee;

/**
 * FXML Controller class
 *
 * @author maikhanh
 */
public class StoreViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    static MKCoffee su;
    
    public void main(MKCoffee su) {
        this.su = su;
    }
    
}
