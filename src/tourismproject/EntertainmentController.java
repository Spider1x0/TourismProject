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
import java.util.ArrayList;
import java.util.Collections;
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
public class EntertainmentController implements Initializable {

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
    private JFXButton shuffleBtn;
    @FXML
    private ImageView placeImg;
    @FXML
    private Text placeTitle;
    @FXML
    private Text placeDesc;
    @FXML
    private Text favText;

    DatabaseConnection dc = new DatabaseConnection();
    ArrayList<Integer> idList = new ArrayList<>();
    private int userID;
    private int counter;
    
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

        try {
            randomizer();
            getElemnt();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setCurrentUser(int userID) {
        
        this.userID = userID;
    }

    @FXML
    private void toProfile(MouseEvent event) throws SQLException, IOException {
        
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
    private void showPlaceInfo(ActionEvent event) throws SQLException {
        
        String info[];
        Image image;

        favText.setText("ADD TO FAVOURITE?");

        if (event.getTarget().equals(placeOneInfo)) {

            info = dc.getPlaceInfo(idList.get(0));
            image = dc.getImage(idList.get(0), "Places");
            placeTitle.setText(info[0]);
            placeDesc.setText(info[1]);
            placeImg.setImage(image);

        } else if (event.getTarget().equals(placeTwoInfo)) {

            info = dc.getPlaceInfo(idList.get(1));
            image = dc.getImage(idList.get(1), "Places");
            placeTitle.setText(info[0]);
            placeDesc.setText(info[1]);
            placeImg.setImage(image);

        } else if (event.getTarget().equals(placeThreeInfo)) {

            info = dc.getPlaceInfo(idList.get(2));
            image = dc.getImage(idList.get(2), "Places");
            placeTitle.setText(info[0]);
            placeDesc.setText(info[1]);
            placeImg.setImage(image);

        } else if (event.getTarget().equals(placeFourInfo)) {

            info = dc.getPlaceInfo(idList.get(3));
            image = dc.getImage(idList.get(3), "Places");
            placeTitle.setText(info[0]);
            placeDesc.setText(info[1]);
            placeImg.setImage(image);

        } else if (event.getTarget().equals(placeFiveInfo)) {

            info = dc.getPlaceInfo(idList.get(4));
            image = dc.getImage(idList.get(4), "Places");
            placeTitle.setText(info[0]);
            placeDesc.setText(info[1]);
            placeImg.setImage(image);

        } else if (event.getTarget().equals(placeSixInfo)) {

            info = dc.getPlaceInfo(idList.get(5));
            image = dc.getImage(idList.get(5), "Places");
            placeTitle.setText(info[0]);
            placeDesc.setText(info[1]);
            placeImg.setImage(image);
        }
    }
    
    public void setLabels(String lblSetter) throws SQLException {

        counter++;
        switch (counter) {
            case 1:

                placeOneTitle.setText(lblSetter);
                break;
            case 2:

                placeTwoTitle.setText(lblSetter);
                break;
            case 3:

                placeThreeTitle.setText(lblSetter);
                break;
            case 4:

                placeFourTitle.setText(lblSetter);
                break;
            case 5:

                placeFiveTitle.setText(lblSetter);
                break;
            case 6:

                placeSixTitle.setText(lblSetter);
                break;
            default:
                System.out.println("Woah there, there isn't a seventh label");
        }
    }

    @FXML
    private void shuffle(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Entertainment.fxml"));
        Parent root = (Parent) loader.load();

        EntertainmentController entertainment = loader.getController();
        entertainment.setCurrentUser(userID);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Rahal - Entertainment");
        stage.setScene(scene);
        stage.show();
    }

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
    
    public void randomizer() throws SQLException {

        addNumbers();
        Collections.shuffle(idList);
    }

    public void addNumbers() throws SQLException {

        dc.getIdList(idList, "Entertainment");
    }

    public void getElemnt() throws SQLException {

        for (int i = 0; i != 6; i++) {
            int imgSetter;
            imgSetter = (idList.get(i));
            String label = dc.getPlaces(idList.get(i));

            setLabels(label);
            setPhotos(imgSetter);
        }
    }

    public void setPhotos(int id) throws SQLException {
        
        switch (counter) {
            case 1:
                
                placeOneImg.setImage(dc.getImage(id, "Places"));
                break;
            case 2:
                
                placeTwoImg.setImage(dc.getImage(id, "Places"));
                break;
            case 3:
                
                placeThreeImg.setImage(dc.getImage(id, "Places"));
                break;
            case 4:
                
                placeFourImg.setImage(dc.getImage(id, "Places"));
                break;
            case 5:
                
                placeFiveImg.setImage(dc.getImage(id, "Places"));
                break;
            case 6:
                
                placeSixImg.setImage(dc.getImage(id, "Places"));
                counter = 0;
                break;
            default:
                System.out.println("Woah there there isn't a seventh label");
        }
    }
}
