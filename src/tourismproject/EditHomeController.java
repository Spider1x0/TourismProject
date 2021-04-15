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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author fefoss454
 */
public class EditHomeController implements Initializable {

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
    private ImageView recOneImg;
    @FXML
    private ImageView recTwoImg;
    @FXML
    private ImageView recThreeImg;
    @FXML
    private ImageView recFourImg;
    @FXML
    private ImageView eventOneImg;
    @FXML
    private JFXTextField eventOneTitle;
    @FXML
    private JFXTextArea eventOneDisc;
    @FXML
    private ImageView eventTwoImg;
    @FXML
    private JFXTextField eventTwoTitle;
    @FXML
    private ImageView newsImg;
    @FXML
    private JFXTextField newsTitle;
    @FXML
    private JFXButton saveRecChanges;
    @FXML
    private Label editRecTitle;
    @FXML
    private ComboBox<String> recOneCB;
    @FXML
    private ComboBox<String> recTwoCB;
    @FXML
    private ComboBox<String> recThreeCB;
    @FXML
    private ComboBox<String> recFourCB;
    @FXML
    private JFXButton loadEventPicOneBtn;
    @FXML
    private JFXButton loadEventPicTwoBtn;
    @FXML
    private Label editEventTitle;
    @FXML
    private JFXButton saveEventsChanges;
    @FXML
    private JFXTextArea eventTwoDisc;
    @FXML
    private Label editNewsTitle;
    @FXML
    private JFXButton loadNewsPicOneBtn;
    @FXML
    private JFXTextArea newsDisc;
    @FXML
    private JFXButton saveNewsChanges;

    ObservableList<String> list = FXCollections.observableArrayList();

    DatabaseConnection dc = new DatabaseConnection();
    private int userID;

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
            fillRecPlace();
        } catch (SQLException ex) {
            Logger.getLogger(EditHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void toAdmin2(ActionEvent event) throws SQLException, IOException {
        
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

    @FXML
    private void checkDupeRecs(ActionEvent event) throws SQLException {

        Image image;

        if (event.getTarget().equals(recOneCB)) {

            image = dc.getImage(recOneCB.getValue(), "Places");
            recOneImg.setImage(image);
        } else if (event.getTarget().equals(recTwoCB)) {

            image = dc.getImage(recTwoCB.getValue(), "Places");
            recTwoImg.setImage(image);
        } else if (event.getTarget().equals(recThreeCB)) {

            image = dc.getImage(recThreeCB.getValue(), "Places");
            recThreeImg.setImage(image);
        } else if (event.getTarget().equals(recFourCB)) {

            image = dc.getImage(recFourCB.getValue(), "Places");
            recFourImg.setImage(image);
        }
    }

    private void fillRecPlace() throws SQLException {

        String[] placesNames;

        placesNames = dc.getAllPlaces();

        list.removeAll(list);
        list.addAll(placesNames);

        recOneCB.getItems().addAll(list);
        recTwoCB.getItems().addAll(list);
        recThreeCB.getItems().addAll(list);
        recFourCB.getItems().addAll(list);
    }

    @FXML
    private void saveRecommendations(ActionEvent event) throws SQLException, InterruptedException {

        boolean toChange[] = {false, false, false, false};
        boolean save = true;

        if ((recOneCB.getValue() != null) && (recTwoCB.getValue() == null ? recOneCB.getValue() != null : !recTwoCB.getValue().equals(recOneCB.getValue())) && (recThreeCB.getValue() == null ? recOneCB.getValue() != null : !recThreeCB.getValue().equals(recOneCB.getValue())) && (recFourCB.getValue() == null ? (recOneCB.getValue()) != null : !recFourCB.getValue().equals(recOneCB.getValue()))) {

            toChange[0] = true;
        } else if ((recOneCB.getValue() != null) && (recTwoCB.getValue() == null ? recOneCB.getValue() != null : recTwoCB.getValue().equals(recOneCB.getValue())) || (recThreeCB.getValue() == null ? (recOneCB.getValue()) != null : recThreeCB.getValue().equals(recOneCB.getValue())) || (recFourCB.getValue() == null ? (recOneCB.getValue()) != null : recFourCB.getValue().equals(recOneCB.getValue()))) {

            save = false;
            editRecTitle.setText("PLEASE REMOVE DUPLICATES");
        }

        if ((recTwoCB.getValue() != null) && (recThreeCB.getValue() == null ? (recTwoCB.getValue()) != null : !recThreeCB.getValue().equals(recTwoCB.getValue())) && (recFourCB.getValue() == null ? (recTwoCB.getValue()) != null : !recFourCB.getValue().equals(recTwoCB.getValue()))) {

            toChange[1] = true;
        } else if ((recTwoCB.getValue() != null) && (recThreeCB.getValue() == null ? (recTwoCB.getValue()) != null : recThreeCB.getValue().equals(recTwoCB.getValue())) || (recFourCB.getValue() == null ? (recTwoCB.getValue()) != null : recFourCB.getValue().equals(recTwoCB.getValue()))) {

            save = false;
            editRecTitle.setText("PLEASE REMOVE DUPLICATES");
        }

        if ((recThreeCB.getValue() != null) && (recFourCB.getValue() == null ? (recThreeCB.getValue()) != null : !recFourCB.getValue().equals(recThreeCB.getValue()))) {

            toChange[2] = true;
        } else if ((recThreeCB.getValue() != null) && (recFourCB.getValue() == null ? (recThreeCB.getValue()) != null : recFourCB.getValue().equals(recThreeCB.getValue()))) {

            save = false;
            editRecTitle.setText("PLEASE REMOVE DUPLICATES");
        }

        if ((recFourCB.getValue() != null)) {

            toChange[3] = true;
        }

        if (save) {

            if (toChange[0]) {

                dc.saveRecommendation(1, dc.getPlaceID(recOneCB.getValue()));
            }

            if (toChange[1]) {

                dc.saveRecommendation(2, dc.getPlaceID(recTwoCB.getValue()));
            }

            if (toChange[2]) {

                dc.saveRecommendation(3, dc.getPlaceID(recThreeCB.getValue()));
            }

            if (toChange[3]) {

                dc.saveRecommendation(4, dc.getPlaceID(recFourCB.getValue()));
            }

            editRecTitle.setText("EDIT RECOMMENDATIONS");
        }
    }

    @FXML
    private void loadPicture(ActionEvent event) throws FileNotFoundException {

        if (event.getTarget().equals(loadEventPicOneBtn)) {

            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(loadEventPicOneBtn.getScene().getWindow());
            FileInputStream fis1 = new FileInputStream(f);
            InputStream is = fis1;
            eventOneImg.setImage(new Image(is));
        } else if (event.getTarget().equals(loadEventPicTwoBtn)) {

            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(loadEventPicOneBtn.getScene().getWindow());
            FileInputStream fis2 = new FileInputStream(f);
            InputStream is = fis2;
            eventTwoImg.setImage(new Image(is));
        } else if (event.getTarget().equals(loadNewsPicOneBtn)) {

            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(loadEventPicOneBtn.getScene().getWindow());
            FileInputStream fis3 = new FileInputStream(f);
            InputStream is = fis3;
            newsImg.setImage(new Image(is));
        }
    }

    @FXML
    private void saveEvents(ActionEvent event) throws SQLException, IOException {

        boolean toChange[] = {false, false};
        boolean save = true;

        if (!eventOneTitle.getText().isEmpty() && !eventOneDisc.getText().isEmpty() && eventOneImg.getImage() != null) {

            toChange[0] = true;
        } else if ((!eventOneTitle.getText().isEmpty() && (eventOneDisc.getText().isEmpty() || eventOneImg.getImage() == null)) || (!eventOneDisc.getText().isEmpty() && (eventOneTitle.getText().isEmpty() || eventOneImg.getImage() == null)) || (eventOneImg.getImage() != null && (eventOneTitle.getText().isEmpty() || eventOneDisc.getText().isEmpty()))) {
            
            save = false;
            editEventTitle.setText("PLEASE FILL THE INFORMATION");
        }

        if (!eventTwoTitle.getText().isEmpty() && !eventTwoDisc.getText().isEmpty() && eventTwoImg.getImage() != null) {

            toChange[1] = true;
        } else if (!eventTwoTitle.getText().isEmpty() && (eventTwoDisc.getText().isEmpty() || eventTwoImg.getImage() == null) || !eventTwoDisc.getText().isEmpty() && (eventTwoTitle.getText().isEmpty() || eventTwoImg.getImage() == null) || eventTwoImg.getImage() != null && (eventTwoTitle.getText().isEmpty() || eventTwoDisc.getText().isEmpty())) {

            save = false;
            editEventTitle.setText("PLEASE FILL THE INFORMATION");
        }

        if (save) {

            if (toChange[0]) {

                dc.saveEvents(eventOneImg.getImage(), eventOneTitle.getText(), eventOneDisc.getText(), 1);
            }

            if (toChange[1]) {

                dc.saveEvents(eventTwoImg.getImage(), eventTwoTitle.getText(), eventTwoDisc.getText(), 2);
            }

            editEventTitle.setText("EDIT CURRENT EVENTS");
        }
    }

    @FXML
    private void saveNews(ActionEvent event) throws SQLException, IOException {

        boolean save = false;

        if (!newsTitle.getText().isEmpty() && !newsDisc.getText().isEmpty() && newsImg.getImage() != null) {

            save = true;
        } else if (!newsTitle.getText().isEmpty() && (newsDisc.getText().isEmpty() || newsImg.getImage() == null) || !newsDisc.getText().isEmpty() && (newsTitle.getText().isEmpty() || newsImg.getImage() == null) || newsImg.getImage() != null && (newsTitle.getText().isEmpty() || newsDisc.getText().isEmpty())) {

            editNewsTitle.setText("FILL THE INFORMATION FIRST");
        }

        if (save) {

            dc.saveNews(newsImg.getImage(), newsTitle.getText(), newsDisc.getText());
            editNewsTitle.setText("EDIT NEWS");
        }
    }
}
