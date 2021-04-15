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
 *
 * @author fefoss454
 */
public class LoginController implements Initializable {

    @FXML
    private Label loginErr;
    @FXML
    private JFXTextField usernameText;
    @FXML
    private JFXPasswordField passwordText;

    int userID;
    DatabaseConnection dc = new DatabaseConnection();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /* Open register page */
    
    @FXML
    private void goToRegister(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Register");
        stage.setScene(scene);
        stage.show();
    }

    /* Go to home page if user info is correct */
    
    private void goToHome(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = (Parent) loader.load();

        HomeController home = loader.getController();
        home.setCurrentUser(userID);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Home");
        stage.setScene(scene);
        stage.show();
    }

    /* Validates user input (Username & Password) and login if the information is correct*/
    
    @FXML
    private void checkUser(ActionEvent event) throws IOException, SQLException {

        if (usernameText.getText().equals("") || passwordText.getText().equals("")) {

            loginErr.setText("PLEASE TYPE YOUR USERNAME AND PASSWORD");
            loginErr.setStyle("-fx-opacity: 1");
        
        } else {

            boolean checkUser = dc.checkLogin(usernameText.getText(), passwordText.getText());

            if (checkUser) {

                loginErr.setStyle("-fx-opacity: 0");
                userID = dc.getUserID(usernameText.getText());
                goToHome(event);
            } else {

                loginErr.setText("INVALID USERNAME OR PASSWORD");
                loginErr.setStyle("-fx-opacity: 1");
            }
        }
    }
}
