/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mkcoffee.controllers.Datasource;
import mkcoffee.controllers.LoadingController;
import mkcoffee.controllers.LoginController;
import mkcoffee.controllers.MainViewController;
import mkcoffee.controllers.SignUpController;
import mkcoffee.controllers.StatisticViewController;

/**
 *
 * @author maikhanh
 */
public class MKCoffee extends Application {

    static Stage stageSignIn, stageSignUp, stageMainView, statictisView, loading;

    @Override
    public void start(Stage stage) throws Exception {
        this.loading = stage;
        loadingWindow();
    }

    public void signInWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(MKCoffee.class.getResource("/mkcoffee/views/Login.fxml"));
            AnchorPane pane = loader.load();
            LoginController controller = loader.getController();
            stageSignIn = new Stage();
            controller.Main(this, stageSignIn);
            Scene scene = new Scene(pane);
            stageSignIn.setTitle("Sign in");
            stageSignIn.setScene(scene);
            stageSignIn.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void loadingWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(MKCoffee.class.getResource("/mkcoffee/views/Loading.fxml"));
            AnchorPane pane = loader.load();
            LoadingController controller = loader.getController();
            loading = new Stage();
            
            controller.Main(this, loading);
            Scene scene = new Scene(pane);
            loading.initStyle(StageStyle.UNDECORATED);
            loading.setTitle("Loading ...");
            loading.setScene(scene);
            loading.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void signInClose() {
        stageSignIn.close();
    }

    public void signUpClose() {
        stageSignUp.close();
    }

    public void loadingWindowClose() {
        loading.close();
    }

    public void signupWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(MKCoffee.class.getResource("/mkcoffee/views/SignUp.fxml"));
            AnchorPane pane = loader.load();
            SignUpController controller = loader.getController();
            
            stageSignUp = new Stage();
            controller.main(this);
            Scene scene = new Scene(pane);

            stageSignUp.setTitle("Register");
            stageSignUp.setResizable(false);
            stageSignUp.setScene(scene);
            stageSignUp.initStyle(StageStyle.UNDECORATED);
            stageSignUp.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainViewWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(MKCoffee.class.getResource("/mkcoffee/views/MainView.fxml"));
            BorderPane pane = loader.load();
            MainViewController controller = loader.getController();
            stageMainView = new Stage();
            controller.main(this);
            Scene scene = new Scene(pane);

            stageMainView.setTitle("MKCoffee");
            stageMainView.setResizable(false);
            stageMainView.setScene(scene);
            stageMainView.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void mainViewWindowClose() {
        stageMainView.close();
    }
    
    public void statisticViewWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(MKCoffee.class.getResource("/mkcoffee/views/StatisticView.fxml"));
            AnchorPane borderPane = loader.load();
            StatisticViewController controller = loader.getController();
            statictisView = new Stage();
            controller.main(this);
            Scene scene = new Scene(borderPane);

            statictisView.setTitle("MKCoffee");
            statictisView.setResizable(false);
            statictisView.setScene(scene);
            statictisView.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws Exception {
        super.init();
        if (!Datasource.getInstance().open()) {
            System.out.println("FATAL ERROR: Couldn't connect to database");
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Datasource.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
