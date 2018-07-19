/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import mkcoffee.main.MKCoffee;

/**
 * FXML Controller class
 *
 * @author maikhanh
 */
public class LoadingController implements Initializable {

    @FXML
    private AnchorPane loadingAnchorPane;

    MKCoffee lodingView;
    Stage stage;

    public void Main(MKCoffee lodingView, Stage stage) {
        this.stage = stage;
        this.lodingView = lodingView;

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new loading().start();
    }

    class loading extends Thread {

        @Override
        public void run() {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {
                        FadeTransition fadeout = new FadeTransition(Duration.seconds(2), loadingAnchorPane);
                        fadeout.setFromValue(1);
                        fadeout.setToValue(0);
                        fadeout.setCycleCount(1);
                        fadeout.play();

                        fadeout.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                                lodingView.loadingWindowClose();
                                lodingView.signInWindow();
                            }
                        });
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

    }

}
