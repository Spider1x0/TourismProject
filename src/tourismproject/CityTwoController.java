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
public class CityTwoController implements Initializable {

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
    private Label cityNameLbl;
    @FXML
    private Label currentTime;
    @FXML
    private MaterialDesignIconView backArrow;
    @FXML
    private ImageView mustSeeImg;
    @FXML
    private Text mustSeeTitle;
    @FXML
    private Text mustSeeDesc;
    @FXML
    private Text cityDesc;
    @FXML
    private Label cityName;
    @FXML
    private ImageView cityImg;
    
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
        
        /* Fill the page with the city informtion from the database */
        
        try {
            setCityInfo();
            setMustSee();
            setCityName();
        } catch (SQLException ex) {
            Logger.getLogger(CityOneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void setCurrentUser(int userID) {
        
        this.userID = userID;
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
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EntertainmentCityOne.fxml"));
        Parent root = (Parent) loader.load();
        
        EntertainmentCityOneController entCityOne = loader.getController();
        entCityOne.setCurrentUser(userID);
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Entertainment");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backPage(MouseEvent event) throws IOException {
        
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
    private void toEntertainmentCityTwo(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EntertainmentCityTwo.fxml"));
        Parent root = (Parent) loader.load();
        
        EntertainmentCityTwoController entCityTwo = loader.getController();
        entCityTwo.setCurrentUser(userID);
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Entertainment");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toHotelsCityTwo(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HotelsCityTwo.fxml"));
        Parent root = (Parent) loader.load();
        
        HotelsCityTwoController hotCityTwo = loader.getController();
        hotCityTwo.setCurrentUser(userID);
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Hotels");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toSightseeingCityTwo(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SightseeingCityTwo.fxml"));
        Parent root = (Parent) loader.load();
        
        SightseeingCityTwoController sightCityTwo = loader.getController();
        sightCityTwo.setCurrentUser(userID);
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Sightseeing");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toRestaurantsCityTwo(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantsCityTwo.fxml"));
        Parent root = (Parent) loader.load();
        
        RestaurantsCityTwoController resCityTwo = loader.getController();
        resCityTwo.setCurrentUser(userID);
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Restaurants");
        stage.setScene(scene);
        stage.show();
    }
    
    /* Get city information and display it */
    
    public void setCityInfo() throws SQLException {
        
        String cityInfo[];
        Image image;

        cityInfo = dc.getCities(2);
        cityName.setText(cityInfo[0]);
        cityDesc.setText(cityInfo[1]);
        image = dc.getImage(2, "City");
        cityImg.setImage(image);
    }
    
    /* Set must see and display it */
    
    public void setMustSee() throws SQLException {
        
        String mustSee[];
        Image image;

        mustSee = dc.getMustSee(2);
        mustSeeTitle.setText(mustSee[0]);
        mustSeeDesc.setText(mustSee[1]);
        image = dc.getImage(2, "Must_See");
        mustSeeImg.setImage(image);
    }
    
    /* Set city name */
    
    private void setCityName() throws SQLException {
        
        String name[] = dc.getCities(2);
        cityNameLbl.setText(name[0]);
    }
}
