/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oldFiles;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author fefoss454
 */
public class HomeController implements Initializable {

    @FXML
    private BorderPane currentPage;
    @FXML
    private Label testLbl;
    @FXML
    private JFXButton homeBtn;
    @FXML
    private JFXButton citiesBtn;
    @FXML
    private JFXButton sightseeingBtn;
    @FXML
    private JFXButton entertainment;
    @FXML
    private JFXButton adminBtn1;
    @FXML
    private JFXButton adminBtn2;
    @FXML
    private JFXButton logOffBtn;
    @FXML
    private Label currentTime;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toProfile(MouseEvent event) {
    }

    @FXML
    private void toHome(ActionEvent event) {
    }

    @FXML
    private void toCities(ActionEvent event) {
    }

    @FXML
    private void toSightseeing(ActionEvent event) {
    }

    @FXML
    private void toEntertainment(ActionEvent event) {
    }

    @FXML
    private void toAdmin1(ActionEvent event) {
    }

    @FXML
    private void toAdmin2(ActionEvent event) {
    }

    @FXML
    private void toLogin(ActionEvent event) {
    }
    
}
