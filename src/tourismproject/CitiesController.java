/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourismproject;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author fefoss454
 */
public class CitiesController implements Initializable {

    @FXML
    private Label currentTime;
    @FXML
    private Label cityNameTwo;
    @FXML
    private ImageView cityImgTwo;
    @FXML
    private Text cityDescTwo;
    @FXML
    private JFXButton visitCityTwo;
    @FXML
    private Label cityNameOne;
    @FXML
    private ImageView cityImgOne;
    @FXML
    private Text cityDescOne;
    @FXML
    private JFXButton visitCityOne;
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

    DatabaseConnection dc = new DatabaseConnection();
    private int userID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /* Set the current time and display it */
        
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            currentTime.setText(sdf.format(date));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        /* Set Cities' information */
        
        try {
            setCitiesInfo();
        } catch (SQLException ex) {
            Logger.getLogger(CitiesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void toCityTwo(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CityTwo.fxml"));
        Parent root = (Parent) loader.load();

        CityTwoController cTwo = loader.getController();
        cTwo.setCurrentUser(userID);
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Welcome");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toCityOne(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CityOne.fxml"));
        Parent root = (Parent) loader.load();

        CityOneController cOne = loader.getController();
        cOne.setCurrentUser(userID);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Welcome");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toProfile(MouseEvent event) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
        Parent root = (Parent) loader.load();

        ProfileController profile = loader.getController();
        profile.setCurrentUser(userID);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Profile");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toHome(ActionEvent event) throws IOException {

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

    @FXML
    private void toCities(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cities.fxml"));
        Parent root = (Parent) loader.load();

        CitiesController cities = loader.getController();
        cities.setCurrentUser(userID);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Cities");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toSightseeing(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sightseeing.fxml"));
        Parent root = (Parent) loader.load();

        SightseeingController sightseeing = loader.getController();
        sightseeing.setCurrentUser(userID);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Sightseeing");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toEntertainment(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Entertainment.fxml"));
        Parent root = (Parent) loader.load();

        EntertainmentController entertainmentCon = loader.getController();
        entertainmentCon.setCurrentUser(userID);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Entertainment");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toAdmin1(ActionEvent event) throws SQLException, IOException {
        
        String checkUser[] = dc.getUserInfo(userID);

        if (checkUser[0].equals("Normal")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ErrorPopup.fxml"));
            Parent root = (Parent) loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(adminBtn1.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.getIcons().add(new Image("images/error-icon.png"));
            stage.setTitle("DENIED ACCESS");
            stage.show();

        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditHome.fxml"));
            Parent root = (Parent) loader.load();

            EditHomeController eHome = loader.getController();
            eHome.setCurrentUser(userID);

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Rahal - Manage Home");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void toAdmin2(ActionEvent event) throws IOException, SQLException {
        
        String checkUser[] = dc.getUserInfo(userID);

        if (checkUser[0].equals("Normal")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ErrorPopup.fxml"));
            Parent root = (Parent) loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(adminBtn1.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.getIcons().add(new Image("images/error-icon.png"));
            stage.setTitle("DENIED ACCESS");
            stage.show();

        } else {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCities.fxml"));
            Parent root = (Parent) loader.load();

            EditCitiesController eCities = loader.getController();
            eCities.setCurrentUser(userID);

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Rahal - Manage Cities");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void toLogin(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Login");
        stage.setScene(scene);
        stage.show();
    }

    public void setCurrentUser(int userID) {

        this.userID = userID;
    }

    /* Set city information */
    
    public void setCitiesInfo() throws SQLException {

        String cityInfo[];
        Image image;

        cityInfo = dc.getCities(1);
        cityNameOne.setText(cityInfo[0]);
        cityDescOne.setText(cityInfo[1]);
        image = dc.getImage(1, "City");
        cityImgOne.setImage(image);

        cityInfo = dc.getCities(2);
        cityNameTwo.setText(cityInfo[0]);
        cityDescTwo.setText(cityInfo[1]);
        image = dc.getImage(2, "City");
        cityImgTwo.setImage(image);
    }
}
