/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mkcoffee.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import mkcoffee.main.MKCoffee;

/**
 * FXML Controller class
 *
 * @author maikhanh
 */
public class SignUpController implements Initializable {

    static MKCoffee su;
//    Stage stage;

    @FXML
    private JFXTextField fullName;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField phoneNumber;

    @FXML
    private JFXDatePicker birthday;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField userName;

    //gender
    @FXML
    JFXComboBox<Label> combo;

    String gender;

    private static Connection conn = null;
    private static PreparedStatement perpareStatement = null;
    private static PreparedStatement perpareStatementRegister = null;
    private static PreparedStatement perpareStatementFindId = null;

    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DB_URL = "jdbc:sqlserver://localhost;";
    static final String DATABASENAME = "databaseName=mkcoffee;";
    static final String USER = "user=SA;";
    static final String PASS = "password=Sqldemo2017";
    private static String REGISTER_INSERT = "INSERT INTO register(userId, userName, userPass) VALUES (?,?,?)";
    private static String USER_INSERT = "INSERT INTO users(fullName, email, phoneNumber, birthday, gender, userName, userPass ) VALUES (?,?,?,?,?,?,?)";
    private static String FIND_USERS_NAME = "SELECT userId FROM users WHERE userName = ? AND birthday = ?";

    public void main(MKCoffee su) {
        this.su = su;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        combo.getItems().add(new Label("Male"));
        combo.getItems().add(new Label("Female"));
        combo.setEditable(false);
        combo.setPromptText("Gender");
        combo.setConverter(new StringConverter<Label>() {
            @Override
            public String toString(Label object) {
                return object == null ? "" : object.getText();
            }

            @Override
            public Label fromString(String string) {
                return new Label(string);
            }
        });

    }

    @FXML
    void cancelAction(ActionEvent event) {
        su.signUpClose();
        su.signInWindow();
    }

    private static void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign up ");
        alert.setHeaderText("Information");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private static void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error ");
        alert.setHeaderText("there is an error happened !");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void insert(String fullName, String email, String phoneNumber, String birthday, String gender, String userName, String userPass) {

        try {
            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            perpareStatement = conn.prepareStatement(USER_INSERT);

            perpareStatement.setString(1, fullName);
            perpareStatement.setString(2, email);
            perpareStatement.setString(3, phoneNumber);
            perpareStatement.setDate(4, java.sql.Date.valueOf(birthday));
            perpareStatement.setString(5, gender);
            perpareStatement.setString(6, userName);
            perpareStatement.setString(7, userPass);

            perpareStatement.executeUpdate();
            
            String userId = searchByUserName(userName, birthday);
//            System.out.println(userId);
            registerInsert(userId, userName, userPass);
                   
            showInfo(userName + " has registered user successfully");
            su.signUpClose();
            su.signInWindow();

        } catch (SQLException ex) {
            showError(ex.getMessage());
        } catch (NumberFormatException c) {
            showError(c.getMessage());
        } catch (NullPointerException cc) {
            showError(cc.getMessage());
        } catch (Error e) {
            showError(e.getMessage());
        } catch (Exception f) {
            showError(f.getMessage());
        } finally {
            try {
                perpareStatement.close();
                conn.close();
                
            } catch (SQLException ex) {
                showError(ex.getMessage());
            }
        }
    }
    
        public static void insertInside(String fullName, String email, String phoneNumber, String birthday, String gender, String userName, String userPass) {

        try {
            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            perpareStatement = conn.prepareStatement(USER_INSERT);

            perpareStatement.setString(1, fullName);
            perpareStatement.setString(2, email);
            perpareStatement.setString(3, phoneNumber);
            perpareStatement.setDate(4, java.sql.Date.valueOf(birthday));
            perpareStatement.setString(5, gender);
            perpareStatement.setString(6, userName);
            perpareStatement.setString(7, userPass);

            perpareStatement.executeUpdate();
            
            String userId = searchByUserName(userName, birthday);
//            System.out.println(userId);
            registerInsert(userId, userName, userPass);
        } catch (SQLException ex) {
            showError(ex.getMessage());
        } catch (NumberFormatException c) {
            showError(c.getMessage());
        } catch (NullPointerException cc) {
            showError(cc.getMessage());
        } catch (Error e) {
            showError(e.getMessage());
        } catch (Exception f) {
            showError(f.getMessage());
        } finally {
            try {
                perpareStatement.close();
                conn.close();
                
            } catch (SQLException ex) {
                showError(ex.getMessage());
            }
        }
    }

    @FXML
    public void register(ActionEvent event) {
        insert(fullName.getText(), email.getText(), phoneNumber.getText(), this.birthday.getValue().toString(), combo.getValue().getText(), userName.getText(), password.getText());
    }

    public static void registerInsert(String userId, String userName, String userPass) {

        try {
            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            perpareStatementRegister = conn.prepareStatement(REGISTER_INSERT);

            perpareStatementRegister.setString(1, userId);
            perpareStatementRegister.setString(2, userName);
            perpareStatementRegister.setString(3, userPass);

            perpareStatementRegister.executeUpdate();
//            showInfo(userName + " has registered register successfully");

        } catch (SQLException ex) {
            showError(ex.getMessage());
        } catch (NumberFormatException c) {
            showError(c.getMessage());
        } catch (NullPointerException cc) {
            showError(cc.getMessage());
        } catch (Error e) {
            showError(e.getMessage());
        } catch (Exception f) {
            showError(f.getMessage());
        } finally {
            try {
                perpareStatementRegister.close();
                conn.close();
                
            } catch (SQLException ex) {
                showError(ex.getMessage());
            }
        }
    }
    
    

    public static String searchByUserName(String name, String birthday) {
        String result = null;
        try {
            conn = DriverManager.getConnection(DB_URL + DATABASENAME + USER + PASS);
            perpareStatementFindId = conn.prepareStatement(FIND_USERS_NAME);
            perpareStatementFindId.setString(1, name);
            perpareStatementFindId.setDate(2, java.sql.Date.valueOf(birthday));
            ResultSet rs = perpareStatementFindId.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);

                return result;
            } else {
                System.out.println("Can't find user");

            }

            rs.close();
            return result;
        } catch (SQLException e) {
            System.out.println("Can't find user - Check Connection: " + e.getMessage());
            return result;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
