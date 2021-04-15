/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourismproject;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
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
public class ProfileController implements Initializable {

    @FXML
    private Label currentTime;
    @FXML
    private Text emailLbl;
    @FXML
    private Text usernameLbl;
    @FXML
    private Text fullnameLbl;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXTextField passwordField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField firstNameField;
    @FXML
    private JFXTextField lastNameField;
    @FXML
    private Text favOneTitle;
    @FXML
    private Text favTwoTitle;
    @FXML
    private Text favThreeTitle;
    @FXML
    private Text favFourTitle;
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
    private JFXButton saveAccountChangesBtn;
    @FXML
    private Label changeErr;
    @FXML
    private ImageView profileImg;
    @FXML
    private ImageView favOneImg;
    @FXML
    private ImageView favTwoImg;
    @FXML
    private ImageView favThreeImg;
    @FXML
    private ImageView favFourImg;
    @FXML
    private MaterialDesignIconView removeFavOne;
    @FXML
    private MaterialDesignIconView removeFavTwo;
    @FXML
    private MaterialDesignIconView removeFavThree;
    @FXML
    private MaterialDesignIconView removeFavFour;

    DatabaseConnection dc = new DatabaseConnection();
    int userID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            currentTime.setText(sdf.format(date));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
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

    public void setCurrentUser(int userID) throws SQLException {

        this.userID = userID;
        fillInformation();
        loadFavs();
    }

    public void fillInformation() throws SQLException {

        String[] fill = dc.getUserInfo(userID);

        usernameLbl.setText(fill[1]);
        fullnameLbl.setText((fill[3] == null ? "" : fill[3] + " ") + (fill[4] == null ? "" : fill[4]));
        emailLbl.setText(fill[5]);
    }

    private void loadFavs() throws SQLException {

        int favPlaces[];
        String fav;
        Image image;

        favPlaces = dc.getFavPlaces(userID);

        if (favPlaces.length >= 1) {

            fav = dc.getPlaces(favPlaces[0]);
            image = dc.getImage(favPlaces[0], "Places");
            favOneTitle.setText(fav);
            favOneImg.setImage(image);
        }

        if (favPlaces.length >= 2) {

            fav = dc.getPlaces(favPlaces[1]);
            image = dc.getImage(favPlaces[1], "Places");
            favTwoTitle.setText(fav);
            favTwoImg.setImage(image);
        }

        if (favPlaces.length >= 3) {

            fav = dc.getPlaces(favPlaces[2]);
            image = dc.getImage(favPlaces[2], "Places");
            favThreeTitle.setText(fav);
            favThreeImg.setImage(image);
        }

        if (favPlaces.length == 4) {

            fav = dc.getPlaces(favPlaces[3]);
            image = dc.getImage(favPlaces[3], "Places");
            favFourTitle.setText(fav);
            favFourImg.setImage(image);
        }
    }

    @FXML
    private void saveAccountChanges(ActionEvent event) throws SQLException, IOException {

        boolean changeInfo = true;
        boolean toChange[] = {false, false, false, false, false};

        if ((!usernameField.getText().equals("")) && (!dc.checkDuplicateAccounts(usernameField.getText(), "Username"))) {
            toChange[0] = true;
            usernameField.setPromptText("USERNAME");
        } else if ((!usernameField.getText().equals("")) && (dc.checkDuplicateAccounts(usernameField.getText(), "Username"))) {
            usernameField.setPromptText("USERNAME ALREADY EXISTS");
            changeInfo = false;
            changeErr.setStyle("-fx-opacity: 1");
        } else {
            usernameField.setPromptText("USERNAME");
        }

        if (!passwordField.getText().equals("")) {
            toChange[1] = true;
        }

        if ((!emailField.getText().equals("")) && (!dc.checkDuplicateAccounts(emailField.getText(), "Email"))) {
            toChange[2] = true;
            emailField.setPromptText("EMAIL");
        } else if ((!emailField.getText().equals("")) && (dc.checkDuplicateAccounts(emailField.getText(), "Email"))) {
            emailField.setPromptText("EMAIL ALREASY EXISTS");
            changeInfo = false;
            changeErr.setStyle("-fx-opacity: 1");
        } else {
            emailField.setPromptText("EMAIL");
        }

        if (!firstNameField.getText().equals("")) {
            toChange[3] = true;
        }

        if (!lastNameField.getText().equals("")) {
            toChange[4] = true;
        }

        if (changeInfo) {

            changeErr.setStyle("-fx-opacity: 0");

            if (toChange[0]) {

                dc.editAccountInfo(usernameField.getText(), "Username", userID);
            }

            if (toChange[1]) {

                dc.editAccountInfo(passwordField.getText(), "Password", userID);
            }

            if (toChange[2]) {

                dc.editAccountInfo(emailField.getText(), "Email", userID);
            }

            if (toChange[3]) {

                dc.editAccountInfo(firstNameField.getText(), "First_Name", userID);
            }

            if (toChange[4]) {

                dc.editAccountInfo(lastNameField.getText(), "Last_Name", userID);
            }

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
    }

    @FXML
    private void removeFav(MouseEvent event) throws SQLException, IOException {

        if (event.getTarget().equals(removeFavOne) && !favOneTitle.getText().equals("")) {

            dc.removeFav(userID, 1);

        } else if (event.getTarget().equals(removeFavTwo) && !favTwoTitle.getText().equals("")) {

            dc.removeFav(userID, 2);

        } else if (event.getTarget().equals(removeFavThree) && !favThreeTitle.getText().equals("")) {

            dc.removeFav(userID, 3);

        } else if (event.getTarget().equals(removeFavFour) && !favFourTitle.getText().equals("")) {

            dc.removeFav(userID, 4);
        }

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
}
