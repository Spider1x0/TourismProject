/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourismproject;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author fefoss454
 */
public class EditCitiesController implements Initializable {

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
    private ComboBox<String> citiesList;
    @FXML
    private JFXButton cityChoosePic;
    @FXML
    private JFXTextField cityName;
    private JFXTextArea newsDisc;
    @FXML
    private JFXButton saveCityBtn;
    @FXML
    private ImageView cityImg;
    @FXML
    private JFXTextArea cityDesc;
    @FXML
    private Label editCitiesTitle;
    @FXML
    private ComboBox<String> placesTypesList;
    @FXML
    private ComboBox<String> placesList;
    @FXML
    private JFXButton PlaceChoosePic;
    @FXML
    private JFXTextField placeName;
    @FXML
    private JFXButton savePlaceBtn;
    @FXML
    private JFXTextArea placeDesc;
    @FXML
    private ImageView placeImg;
    @FXML
    private Label editMustSeeTitle;
    @FXML
    private Label editPlacesTitle;
    @FXML
    private ComboBox<String> mustSeeCitiesList;
    @FXML
    private ImageView mustSeeImg;
    @FXML
    private JFXButton mustSeeChoosePic;
    @FXML
    private JFXTextField mustSeeName;
    @FXML
    private JFXTextArea mustSeeDesc;
    @FXML
    private JFXButton saveMustSeeBtn;

    ObservableList<String> citiesOBList = FXCollections.observableArrayList();
    ObservableList<String> typesOBList = FXCollections.observableArrayList();
    ObservableList<String> placesOBList = FXCollections.observableArrayList();

    DatabaseConnection dc = new DatabaseConnection();
    private int userID;
    private int cityID;

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
            fillCities();
            fillTypes();
        } catch (SQLException ex) {
            Logger.getLogger(EditHomeController.class.getName()).log(Level.SEVERE, null, ex);
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

    /* Show the image of the city from the value of the choice box */
    
    @FXML
    private void showCityImg(ActionEvent event) throws SQLException {

        if (citiesList.getSelectionModel().getSelectedIndex() == 0) {

            cityID = 1;
            Image img = dc.getImage(cityID, "City");
            cityImg.setImage(img);
        } else if (citiesList.getSelectionModel().getSelectedIndex() == 1) {

            cityID = 2;
            Image img = dc.getImage(cityID, "City");
            cityImg.setImage(img);
        }
    }

    /* Show the image of the place from the value of the choice box */
    
    @FXML
    private void showPlaceImg(ActionEvent event) throws SQLException {

        Image img = dc.getImage(placesList.getSelectionModel().getSelectedItem(), "Places");
        placeImg.setImage(img);
    }

    /* Show the image of must see from the value of the choice box */
    
    @FXML
    private void showMustSeeImg(ActionEvent event) throws SQLException {

        Image img = dc.getImage(mustSeeCitiesList.getSelectionModel().getSelectedIndex() + 1, "Must_See");
        mustSeeImg.setImage(img);
    }
    
    /* Open file chooser and display the requested image */

    @FXML
    private void loadPicture(ActionEvent event) throws FileNotFoundException {

        if (event.getTarget().equals(cityChoosePic)) {

            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(cityChoosePic.getScene().getWindow());
            FileInputStream fis1 = new FileInputStream(f);
            InputStream is = fis1;
            cityImg.setImage(new Image(is));

        } else if (event.getTarget().equals(PlaceChoosePic)) {

            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(PlaceChoosePic.getScene().getWindow());
            FileInputStream fis2 = new FileInputStream(f);
            InputStream is = fis2;
            placeImg.setImage(new Image(is));

        } else if (event.getTarget().equals(mustSeeChoosePic)) {

            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(mustSeeChoosePic.getScene().getWindow());
            FileInputStream fis3 = new FileInputStream(f);
            InputStream is = fis3;
            mustSeeImg.setImage(new Image(is));
        }
    }

    /* Save the information of the city if the conditions are met */
    
    @FXML
    private void saveCity(ActionEvent event) throws SQLException, IOException {

        boolean save = false;

        if (citiesList.getValue() != null && !cityName.getText().isEmpty() && !cityDesc.getText().isEmpty() && cityImg.getImage() != null) {

            save = true;
        } else if (citiesList.getValue() != null && (cityName.getText().isEmpty() || cityDesc.getText().isEmpty() || cityImg.getImage() == null) || !cityName.getText().isEmpty() && (citiesList.getValue() == null || cityDesc.getText().isEmpty() || cityImg.getImage() == null) || !cityDesc.getText().isEmpty() && (citiesList.getValue() == null || cityName.getText().isEmpty() || cityImg.getImage() == null) || cityImg.getImage() != null && (citiesList.getValue() == null || cityName.getText().isEmpty() || cityDesc.getText().isEmpty())) {

            editCitiesTitle.setText("FILL THE INFORMATION");
        }

        if (save) {

            dc.saveCity(cityID, cityImg.getImage(), cityName.getText(), cityDesc.getText());
            editCitiesTitle.setText("EDIT CITIES");

            fillCities();
        }
    }
    
    /* Save the information of the place if the conditions are met */

    @FXML
    private void savePlace(ActionEvent event) throws SQLException, IOException {

        boolean save = false;

        if (placesList.getValue() != null && !placeName.getText().isEmpty() && !placeDesc.getText().isEmpty() && placeImg.getImage() != null) {

            save = true;
        } else if (placesList.getValue() != null && (placeName.getText().isEmpty() || placeDesc.getText().isEmpty() || placeImg.getImage() == null) || !placeName.getText().isEmpty() && (placesList.getValue() == null || placeDesc.getText().isEmpty() || placeImg.getImage() == null) || !placeDesc.getText().isEmpty() && (placesList.getValue() == null || placeName.getText().isEmpty() || placeImg.getImage() == null) || placeImg.getImage() != null && (placesList.getValue() == null || placeName.getText().isEmpty() || placeDesc.getText().isEmpty())) {

            editPlacesTitle.setText("FILL THE INFORMATION");
        }

        if (save) {

            dc.savePlace(placesList.getSelectionModel().getSelectedItem(), placeImg.getImage(), placeName.getText(), placeDesc.getText());
            editPlacesTitle.setText("EDIT PLACES");

            showPlaces(event);
        }
    }

    /* Save the information of the must see if the conditions are met */
    
    @FXML
    private void saveMustSee(ActionEvent event) throws SQLException, IOException {

        boolean save = false;

        if (mustSeeCitiesList.getValue() != null && !mustSeeName.getText().isEmpty() && !mustSeeDesc.getText().isEmpty() && mustSeeImg.getImage() != null) {

            save = true;
        } else if (mustSeeCitiesList.getValue() != null && (mustSeeName.getText().isEmpty() || mustSeeDesc.getText().isEmpty() || mustSeeImg.getImage() == null) || !mustSeeName.getText().isEmpty() && (mustSeeCitiesList.getValue() == null || mustSeeDesc.getText().isEmpty() || mustSeeImg.getImage() == null) || !mustSeeDesc.getText().isEmpty() && (mustSeeCitiesList.getValue() == null || mustSeeName.getText().isEmpty() || mustSeeImg.getImage() == null) || mustSeeImg.getImage() != null && (mustSeeCitiesList.getValue() == null || mustSeeName.getText().isEmpty() || mustSeeDesc.getText().isEmpty())) {

            editMustSeeTitle.setText("FILL THE INFORMATION");
        }

        if (save) {

            dc.saveMustSee(mustSeeCitiesList.getSelectionModel().getSelectedIndex() + 1, mustSeeImg.getImage(), mustSeeName.getText(), mustSeeDesc.getText());
            editMustSeeTitle.setText("EDIT MUST SEE");

            fillCities();
        }
    }
    
    /* Fill the choice box for the cities */

    private void fillCities() throws SQLException {

        String[] citiesNames;

        citiesNames = dc.getAllCities();

        citiesOBList.removeAll(citiesOBList);
        citiesOBList.addAll(citiesNames);

        citiesList.getItems().clear();
        citiesList.getItems().addAll(citiesOBList);
        mustSeeCitiesList.getItems().clear();
        mustSeeCitiesList.getItems().addAll(citiesOBList);
    }
    
    /* Fill the choice box for the types of the places */

    private void fillTypes() throws SQLException {

        String[] types = {"Sightseeing", "Entertainment", "Hotels", "Restaurants"};

        typesOBList.removeAll(typesOBList);
        typesOBList.addAll(types);

        placesTypesList.getItems().addAll(typesOBList);
    }

    /* fill the chice box of the places based on the selected value from the type choice box */
    
    @FXML
    private void showPlaces(ActionEvent event) throws SQLException {

        String[] placesNames;

        switch (placesTypesList.getSelectionModel().getSelectedIndex()) {
            case 0:
                placesNames = dc.getPlacesByType(placesTypesList.getSelectionModel().getSelectedItem());
                placesOBList.removeAll(placesOBList);
                placesOBList.addAll(placesNames);
                placesList.getItems().clear();
                placesList.getItems().addAll(placesOBList);
                break;
            case 1:
                placesNames = dc.getPlacesByType(placesTypesList.getSelectionModel().getSelectedItem());
                placesOBList.removeAll(placesOBList);
                placesOBList.addAll(placesNames);
                placesList.getItems().clear();
                placesList.getItems().addAll(placesOBList);
                break;
            case 2:
                placesNames = dc.getPlacesByType(placesTypesList.getSelectionModel().getSelectedItem());
                placesOBList.removeAll(placesOBList);
                placesOBList.addAll(placesNames);
                placesList.getItems().clear();
                placesList.getItems().addAll(placesOBList);
                break;
            case 3:
                placesNames = dc.getPlacesByType(placesTypesList.getSelectionModel().getSelectedItem());
                placesOBList.removeAll(placesOBList);
                placesOBList.addAll(placesNames);
                placesList.getItems().clear();
                placesList.getItems().addAll(placesOBList);
                break;
            default:
                break;
        }

        placesList.setDisable(false);
    }

    public void setCurrentUser(int userID) {

        this.userID = userID;
    }
}
