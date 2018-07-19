/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import mkcoffee.main.MKCoffee;
import mkcoffee.model.User;

/**
 *
 * @author maikhanh
 */
public class LoginController implements Initializable {

    private static Connection conn = null;
    private static PreparedStatement state = null;
    private static PreparedStatement stateUser = null;
    ResultSet result;
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DB_URL = "jdbc:sqlserver://localhost;";
    static final String DATABASENAME = "databaseName=mkcoffee;";
    static final String USER = "user=SA;";
    static final String PASS = "password=Sqldemo2017";
    String userSignInSQL = "select userName, userPass from register where userName=? and userPass= ?";
    String userSQL = "SELECT userId, fullName, email, phoneNumber,gender, birthday,  userName, userPass from users where userName=? and userPass= ?";

    MKCoffee signInView;
    Stage stage;

    public void Main(MKCoffee signInView, Stage stage) {
        this.stage = stage;
        this.signInView = signInView;
    }

    @FXML
    public JFXTextField userNameLogin;

    @FXML
    private JFXPasswordField userPassLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private static void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error ");
        alert.setHeaderText("There is an error happened !");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private static void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Success ");
        alert.setHeaderText("Information");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    void signupAction(ActionEvent event) {
        try {
            signInView.signInClose();
            signInView.signupWindow();
        } catch (Exception ex) {
        }
    }

    @FXML
    void signinAction() throws IOException {
        try {
            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            state = conn.prepareStatement(userSignInSQL);
            state.setString(1, userNameLogin.getText());
            state.setString(2, userPassLogin.getText());

            result = state.executeQuery();

            if (userNameLogin.getText().isEmpty() || userPassLogin.getText().isEmpty()) {
                showError("Please Fill in and try again or sign up");
            } else {
                if (result.next()) {
                    if ((result.getString("userName")).equals(userNameLogin.getText())
                            && (result.getString("userPass")).equals(userPassLogin.getText())) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mkcoffee/views/MainView.fxml"));
                        Parent root = (Parent) loader.load();
                        MainViewController secController = loader.getController();
                        if (findUser() != null) {
                            String name = findUser().getFullName();
                            String userId = findUser().getUserId();
                            secController.myEmployeeId(name, userId);
                        } else {
                            return;
                        }
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                        signInView.signInClose();
                    } else {
                        showError("username or password is invalid");
                    }
                } else if (!result.next()) {
                    showError("username or password is invalid");
                }
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            showError(sqlEx.getMessage());
        } catch (NullPointerException nullEx) {
            nullEx.printStackTrace();
            showError(nullEx.getMessage());
        } finally {
            try {
                state.close();
                conn.close();
            } catch (SQLException rr) {
                showError(rr.getMessage());
            }
        }

    }

    public User findUser() {
        User user = new User();
        try {
            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            stateUser = conn.prepareStatement(userSQL);
            stateUser.setString(1, userNameLogin.getText());
            stateUser.setString(2, userPassLogin.getText());

            result = stateUser.executeQuery();
            if (result.next()) {
                user.setUserId(result.getString(1));
                user.setFullName(result.getString(2));
                user.setEmail(result.getString(3));
                user.setPhoneNumber(result.getString(4));
                user.setGender(result.getString(5));
                user.setBirthday(result.getDate(6).toString());
                user.setUserName(result.getString(7));
                user.setUserPass(result.getString(8));

                return user;
            } else if (!result.next()) {
                showError("username or password is invalid");
                return user = null;
            }
        } catch (SQLException r) {
            r.printStackTrace();
            showError(r.getMessage());
        } catch (NullPointerException l) {
            l.printStackTrace();
            showError(l.getMessage());
        } finally {
            try {
                result.close();
                stateUser.close();
                conn.close();
            } catch (SQLException rr) {
                showError(rr.getMessage());
            }
        }
        return user;

    }

    @FXML
    private void forgetPassword() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Forgot Password");
        alert.setHeaderText("Forgot Password");
        alert.setContentText("Please contact Admin - 0905778899 or MrKhanh - 0905112233 to reset your password");
        alert.showAndWait();
    }

}
