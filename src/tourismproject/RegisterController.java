/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourismproject;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fefoss454
 */
public class RegisterController implements Initializable {

    @FXML
    private JFXTextField usernameText;
    @FXML
    private JFXPasswordField passwordText;
    @FXML
    private JFXTextField emailText;
    @FXML
    private Label regErr;

    DatabaseConnection dc = new DatabaseConnection();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    /* Go to the login page if the registration is complete */
    
    private void goToLogin(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Login");
        stage.setScene(scene);
        stage.show();
    }
    
    /* Check user registration information and load login page if the information is valid */
    
    @FXML
    public void checkRegister(ActionEvent event) throws SQLException, IOException {
        
        boolean createAccount = true;
        
        if (usernameText.getText().equals("") || passwordText.getText().equals("") || emailText.getText().equals("")) {
            regErr.setStyle("-fx-opacity: 1");
            createAccount = false;
        } else {
            regErr.setStyle("-fx-opacity: 0");
        }
        
        /* Check if the email or the username already exist */
        
        if (dc.checkDuplicateAccounts(emailText.getText(), "Email")) {
            emailText.setPromptText("EMAIL ALREADY EXISTS");
            createAccount = false;
        } else {
            emailText.setPromptText("EMAIL");
        }
        
        if (dc.checkDuplicateAccounts(usernameText.getText(), "Username")) {
            usernameText.setPromptText("USERNAME ALREADY EXISTS");
            createAccount = false;
        } else {
            usernameText.setPromptText("USERNAME");
        }
        
        if (createAccount) {
            dc.createAccount(usernameText.getText(), emailText.getText(), passwordText.getText());
            goToLogin(event);
        }
    }
}
