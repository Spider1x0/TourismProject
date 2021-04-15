/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourismproject;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
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
public class RestaurantsCityTwoController implements Initializable {

    DatabaseConnection dc = new DatabaseConnection();
    private int userID;
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
    @FXML
    private MaterialDesignIconView backArrow;
    @FXML
    private ImageView placeOneImg;
    @FXML
    private Text placeOneTitle;
    @FXML
    private JFXButton placeOneInfo;
    @FXML
    private ImageView placeTwoImg;
    @FXML
    private Text placeTwoTitle;
    @FXML
    private JFXButton placeTwoInfo;
    @FXML
    private ImageView placeThreeImg;
    @FXML
    private Text placeThreeTitle;
    @FXML
    private JFXButton placeThreeInfo;
    @FXML
    private ImageView placeFourImg;
    @FXML
    private Text placeFourTitle;
    @FXML
    private JFXButton placeFourInfo;
    @FXML
    private ImageView placeFiveImg;
    @FXML
    private Text placeFiveTitle;
    @FXML
    private JFXButton placeFiveInfo;
    @FXML
    private ImageView placeSixImg;
    @FXML
    private Text placeSixTitle;
    @FXML
    private JFXButton placeSixInfo;
    @FXML
    private ImageView placeImg;
    @FXML
    private Text placeTitle;
    @FXML
    private Text placeDesc;
    @FXML
    private Text favText;
    
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

        /* Fill the page with pleaces informtion from the database */
        
        try {
            setPlaces();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void toAdmin1(ActionEvent event) throws IOException, SQLException {
        
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

    @FXML
    private void backPage(MouseEvent event) throws IOException {

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

    /* Display information for each place depending on the button clicked */
    
    @FXML
    private void showPlaceInfo(ActionEvent event) throws SQLException {

        String info[];
        Image image;
        
        favText.setText("ADD TO FAVOURITE?");
        
        if (event.getTarget().equals(placeOneInfo)) {

            info = dc.getPlaceInfo(37);
            image = dc.getImage(37, "Places");
            placeTitle.setText(info[0]);
            placeDesc.setText(info[1]);
            placeImg.setImage(image);

        } else if (event.getTarget().equals(placeTwoInfo)) {

            info = dc.getPlaceInfo(38);
            image = dc.getImage(38, "Places");
            placeTitle.setText(info[0]);
            placeDesc.setText(info[1]);
            placeImg.setImage(image);

        } else if (event.getTarget().equals(placeThreeInfo)) {

            info = dc.getPlaceInfo(39);
            image = dc.getImage(39, "Places");
            placeTitle.setText(info[0]);
            placeDesc.setText(info[1]);
            placeImg.setImage(image);

        } else if (event.getTarget().equals(placeFourInfo)) {

            info = dc.getPlaceInfo(40);
            image = dc.getImage(40, "Places");
            placeTitle.setText(info[0]);
            placeDesc.setText(info[1]);
            placeImg.setImage(image);

        } else if (event.getTarget().equals(placeFiveInfo)) {

            info = dc.getPlaceInfo(41);
            image = dc.getImage(41, "Places");
            placeTitle.setText(info[0]);
            placeDesc.setText(info[1]);
            placeImg.setImage(image);

        } else if (event.getTarget().equals(placeSixInfo)) {

            info = dc.getPlaceInfo(42);
            image = dc.getImage(42, "Places");
            placeTitle.setText(info[0]);
            placeDesc.setText(info[1]);
            placeImg.setImage(image);
        }
    }

    /*  
     * Validate if the location can be favorited 
     * (A location shoud be selected first & 
     * the maximum number of favourites should be less than 5 & 
     * the location should not be already favorited) 
     * if the conditions are met then add to favourites 
    */
    
    @FXML
    private void addFavourite(MouseEvent event) throws SQLException {
        
        boolean favPlace = true;
        
        if (placeTitle.getText().equals("")) {
            
            favText.setText("SELECT A PLACE FIRST!");
            return;
        }
        
        if (dc.checkNumberOfFavs(userID)) {
            
            favPlace = false;
            favText.setText("MAXIMUM NUMBER REACHED!");
        }
        
        if (dc.checkDuplicateFavs(userID, placeTitle.getText())) {
            
            favPlace = false;
            favText.setText("ALREADY FAVORITED!");
        }
        
        if (favPlace) {
            
            dc.addFav(userID, placeTitle.getText());
            favText.setText("ADDED TO FAVOURITES!");
        }
    }

    public void setCurrentUser(int userID) {

        this.userID = userID;
    }

    /* Get relevant places names and images from the database and display them */
    
    private void setPlaces() throws SQLException {

        String place;
        Image image;

        place = dc.getPlaces(37);
        image = dc.getImage(37, "Places");
        placeOneTitle.setText(place);
        placeOneImg.setImage(image);

        place = dc.getPlaces(38);
        image = dc.getImage(38, "Places");
        placeTwoTitle.setText(place);
        placeTwoImg.setImage(image);

        place = dc.getPlaces(39);
        image = dc.getImage(39, "Places");
        placeThreeTitle.setText(place);
        placeThreeImg.setImage(image);

        place = dc.getPlaces(40);
        image = dc.getImage(40, "Places");
        placeFourTitle.setText(place);
        placeFourImg.setImage(image);

        place = dc.getPlaces(41);
        image = dc.getImage(41, "Places");
        placeFiveTitle.setText(place);
        placeFiveImg.setImage(image);

        place = dc.getPlaces(42);
        image = dc.getImage(42, "Places");
        placeSixTitle.setText(place);
        placeSixImg.setImage(image);
    }
}
