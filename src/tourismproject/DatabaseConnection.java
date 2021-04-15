/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourismproject;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author fefoss454
 */
public class DatabaseConnection {

    private Connection connect() throws SQLException {

        Connection con;
        String url = "jdbc:sqlite:C:/Users/fefoss454/Documents/NetBeansProjects/TourismProject/src/database/Rahal.db";
        con = DriverManager.getConnection(url);
        return con;
    }

    /* Validates user login information */
    
    public boolean checkLogin(String username, String password) throws SQLException {

        String sql = "SELECT * FROM Users";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        boolean checkUser = false;

        while (rs.next()) {

            String u = rs.getString("Username");
            String p = rs.getString("Password");

            if (username.equals(u) && password.equals(p)) {
                checkUser = true;
                break;
            }
        }

        st.close();
        con.close();
        return checkUser;
    }

    /* Check if the username or password already exist and return true is it does */
    
    public boolean checkDuplicateAccounts(String check, String field) throws SQLException {

        String sql = "SELECT * FROM Users";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        boolean checkUser = false;

        while (rs.next()) {

            String lookFor = rs.getString(field);

            if (field.equals("Username")) {
                if (check.equals(lookFor)) {
                    checkUser = true;
                    break;
                }
            } else if (field.equals("Email")) {
                if (check.toLowerCase().equals(lookFor.toLowerCase())) {
                    checkUser = true;
                    break;
                }
            }
        }

        st.close();
        con.close();
        return checkUser;
    }

    /* Create user account with the given information */
    
    public void createAccount(String username, String email, String password) throws SQLException {

        String sql = "INSERT INTO Users(Type, Username, Password, Email) VALUES('" + "Normal" + "', '" + username + "', '" + password + "', '" + email.toLowerCase() + "')";
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("USER HAS BEEN ADDED !!!");
    }

    /* Get user's ID with username */
    
    public int getUserID(String username) throws SQLException {

        String sql = "SELECT * FROM Users";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        int userID = 0;

        while (rs.next()) {

            String user = rs.getString("Username");

            if (user.equals(username)) {
                userID = rs.getInt("ID");
                break;
            }
        }

        st.close();
        con.close();

        return userID;
    }

    /* Return all of the user's information with an array using the user's id */
    
    public String[] getUserInfo(int id) throws SQLException {

        String sql = "SELECT * FROM Users";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String userInfo[] = new String[6];

        while (rs.next()) {

            if (id == rs.getInt("ID")) {

                userInfo[0] = rs.getString("Type");
                userInfo[1] = rs.getString("Username");
                userInfo[2] = rs.getString("Password");
                userInfo[3] = rs.getString("First_Name");
                userInfo[4] = rs.getString("Last_Name");
                userInfo[5] = rs.getString("Email");

                break;
            }
        }

        st.close();
        con.close();

        return userInfo;
    }

    /* Update any of the user's information given a column and a value and an id */
    
    public void editAccountInfo(String changeTo, String field, int id) throws SQLException {

        String sql = "UPDATE Users SET " + field + " = '" + changeTo + "' WHERE ID = " + id;
        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        System.out.println("USER'S " + field + " CHANGED TO " + changeTo);

        st.close();
        con.close();
    }

    /* Return title and description from the news table */
    
    public String[] getNews() throws SQLException {

        String sql = "SELECT * FROM News";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String news[] = new String[2];

        news[0] = rs.getString("Title");
        news[1] = rs.getString("Description");

        st.close();
        con.close();

        return news;
    }

    /* Get event's title and description and retrun them with an array */
    
    public String[] getEvents(int eventID) throws SQLException {

        String sql = "SELECT * FROM Events";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String event[] = new String[2];

        while (rs.next()) {

            if (eventID == rs.getInt("ID")) {

                event[0] = rs.getString("Title");
                event[1] = rs.getString("Description");

                break;
            }
        }

        st.close();
        con.close();

        return event;
    }

    /* Get city information based on the given city id */
    
    public String[] getCities(int cityID) throws SQLException {

        String sql = "SELECT * FROM City";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String cityInfo[] = new String[2];

        while (rs.next()) {

            if (cityID == rs.getInt("ID")) {

                cityInfo[0] = rs.getString("Name");
                cityInfo[1] = rs.getString("Description");

                break;
            }
        }

        st.close();
        con.close();

        return cityInfo;
    }

    /* Return must see from the database based on a given id */
    
    public String[] getMustSee(int mustSeeID) throws SQLException {

        String sql = "SELECT * FROM Must_See";
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String cityInfo[] = new String[2];

        while (rs.next()) {

            if (mustSeeID == rs.getInt("ID")) {

                cityInfo[0] = rs.getString("Title");
                cityInfo[1] = rs.getString("Description");

                break;
            }
        }

        st.close();
        con.close();

        return cityInfo;
    }

    /* Get place name based on the given ID */
    
    public String getPlaces(int id) throws SQLException {

        String sql = "SELECT * FROM Places WHERE ID LIKE " + id;
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String place;

        place = rs.getString("Name");

        st.close();
        con.close();

        return place;
    }

    /* Get place name and description based on the given id */
    
    public String[] getPlaceInfo(int id) throws SQLException {

        String sql = "SELECT * FROM Places WHERE ID LIKE " + id;
        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String info[] = new String[2];

        info[0] = rs.getString("Name");
        info[1] = rs.getString("Description");

        st.close();
        con.close();

        return info;
    }

    /* Return an image with the requested table and id */
    
    public Image getImage(int id, String table) throws SQLException {

        String sql = "SELECT Photo FROM " + table + " WHERE id = " + id;
        Connection con = connect();
        Statement st = con.createStatement();
        Image i = null;
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            InputStream input = rs.getBinaryStream("Photo");
            i = new Image(input);
            break;
        }

        st.close();
        con.close();
        return i;
    }

    /* Return an image with the requested table and name of the place */
    
    public Image getImage(String name, String table) throws SQLException {

        String sql = "SELECT Photo FROM " + table + " WHERE Name LIKE \"" + name + "\"";
        Connection con = connect();
        Statement st = con.createStatement();
        Image i = null;
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            InputStream input = rs.getBinaryStream("Photo");
            i = new Image(input);
            break;
        }

        st.close();
        con.close();
        return i;
    }

    /* Check the number of favourites for a given user id and return true if there are 4 favourites */
    
    public boolean checkNumberOfFavs(int userID) throws SQLException {

        int favCount = 0;
        boolean fav = false;
        String sql = "SELECT * FROM Favourite WHERE User_ID = " + userID;
        Connection con = connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            favCount++;
        }

        if (favCount >= 4) {
            fav = true;
        }

        st.close();
        con.close();
        return fav;
    }

    /* Check if the requested place is already favorited based on the place's name and id */
    
    public boolean checkDuplicateFavs(int userID, String placeName) throws SQLException {

        boolean fav = false;
        String sql = "SELECT Places.Name, Favourite.User_ID, Places.ID FROM Places INNER JOIN Favourite ON Favourite.Place_ID = Places.ID";
        Connection con = connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            if (placeName.equals(rs.getString("Name")) && userID == rs.getInt("User_ID")) {
                fav = true;
            }
        }

        st.close();
        con.close();
        return fav;
    }

    /* Add the favourite to the user's account based on the user's id and the place's name */
    
    public void addFav(int userID, String placeName) throws SQLException {

        int placeID;
        Connection con;
        Statement st;
        ResultSet rs;

        String sql = "SELECT * FROM Places WHERE Name LIKE '" + placeName + "'";

        con = connect();
        st = con.createStatement();
        rs = st.executeQuery(sql);

        placeID = rs.getInt("ID");

        st.close();
        con.close();

        sql = "INSERT INTO Favourite(Place_ID, User_ID) VALUES('" + placeID + "', '" + userID + "')";

        con = connect();
        st = con.createStatement();
        st.executeUpdate(sql);

        System.out.println("FAVOURITE ADDED !");
        st.close();
        con.close();
    }
    
    /* Return an array containing the ids of all favorited places from a given user id */

    public int[] getFavPlaces(int userID) throws SQLException {

        int favNum = 0;
        int favPlaces[];
        Connection con;
        Statement st;
        ResultSet rs;

        String sql = "SELECT * FROM Favourite WHERE User_ID = " + userID;

        con = connect();
        st = con.createStatement();
        rs = st.executeQuery(sql);

        while (rs.next()) {
            favNum++;
        }

        favPlaces = new int[favNum];

        rs = st.executeQuery(sql);

        for (int i = 0; i < favPlaces.length; i++) {

            rs.next();
            favPlaces[i] = rs.getInt("Place_ID");
        }

        st.close();
        con.close();

        return favPlaces;
    }

    /* Remove a favourite from a user using the user's id and the number of the favorite to be removed */
    
    public void removeFav(int userID, int rowNum) throws SQLException {

        int placeID = 0;
        Connection con;
        Statement st;
        ResultSet rs;

        String sql = "SELECT * FROM Favourite WHERE User_ID = " + userID;

        con = connect();
        st = con.createStatement();
        rs = st.executeQuery(sql);

        rs.next();
        while (rowNum > 1) {

            rowNum--;
            rs.next();
        }

        placeID = rs.getInt("Place_ID");

        st.close();
        con.close();

        sql = "DELETE FROM Favourite WHERE User_ID LIKE " + userID + " AND Place_ID LIKE " + placeID;

        con = connect();
        st = con.createStatement();
        st.executeUpdate(sql);

        st.close();
        con.close();
    }
    
    /* Get recommended places from the database */

    public int[] getRecommended() throws SQLException {

        int recPlaces[] = new int[4];
        int i = 0;
        String sql = "SELECT * FROM Recommendation";

        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            recPlaces[i++] = rs.getInt("Place_ID");
        }

        st.close();
        con.close();

        return recPlaces;
    }
    
    /* Get a list of all the ids from a place type given the type of the places */

    public void getIdList(ArrayList<Integer> arr, String type) throws SQLException {

        String sql = "SELECT * FROM Places WHERE Type LIKE '" + type + "'";

        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            arr.add(rs.getInt("ID"));
        }

        st.close();
        con.close();
    }

    /* Return all the available locations in the database */
    
    public String[] getAllPlaces() throws SQLException {

        String placesNames[] = new String[48];
        String sql = "SELECT Name FROM Places";
        int i = 0;

        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            placesNames[i] = rs.getString("Name");
            i++;
        }

        st.close();
        con.close();
        return placesNames;
    }

    /* Return a specific location id with its name */
    
    public int getPlaceID(String name) throws SQLException {

        String sql = "SELECT ID FROM Places WHERE Name LIKE \"" + name + "\"";
        int id;

        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        id = rs.getInt("ID");

        st.close();
        con.close();
        return id;
    }

    /* Save recommendations based on a given place id */
    
    public void saveRecommendation(int recID, int placeID) throws SQLException {

        String sql = "UPDATE Recommendation SET Place_ID = " + placeID + " WHERE ID LIKE " + recID;

        Connection con = this.connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        System.out.println("RECOMMENDATION (" + recID + ") IS SET TO \"" + getPlaces(placeID) + "\"");

        st.close();
        con.close();
    }
    
    /* save event's image, title and descritpion based on the given information and an id for the event */

    public void saveEvents(Image img, String title, String desc, int id) throws SQLException, IOException {

        String sql = "UPDATE Events SET Title = \"" + title + "\", Description = \"" + desc + "\", Photo = ? WHERE ID LIKE " + id;
        Connection con = connect();
        PreparedStatement ps = con.prepareStatement(sql);

        BufferedImage bi = SwingFXUtils.fromFXImage(img, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "PNG", baos);
        byte[] b = baos.toByteArray();
        InputStream is = new ByteArrayInputStream(b);

        ps.setBinaryStream(1, is, is.available());
        ps.execute();

        System.out.println("EVENT " + id + " TITLE CHANGED TO " + title);
        System.out.println("EVENT " + id + " DESCRIPTION CHANGED TO " + desc);
        System.out.println("EVENT " + id + " IMAGE CHANGED");

        ps.close();
        con.close();
    }

    /* save news image, title and descritpion based on the given information */
    
    public void saveNews(Image img, String title, String desc) throws SQLException, IOException {

        String sql = "UPDATE News SET Title = \"" + title + "\", Description = \"" + desc + "\", Photo = ? ";
        Connection con = connect();
        PreparedStatement ps = con.prepareStatement(sql);

        BufferedImage bi = SwingFXUtils.fromFXImage(img, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "PNG", baos);
        byte[] b = baos.toByteArray();
        InputStream is = new ByteArrayInputStream(b);

        ps.setBinaryStream(1, is, is.available());
        ps.execute();

        System.out.println("NEWS TITLE CHANGED TO " + title);
        System.out.println("NEWS DESCRIPTION CHANGED TO " + desc);
        System.out.println("NEWS IMAGE CHANGED");

        ps.close();
        con.close();
    }

    /* Return the names of all cities in the database */
    
    public String[] getAllCities() throws SQLException {

        String citiesNames[] = new String[2];
        String sql = "SELECT Name FROM City";
        int i = 0;

        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            citiesNames[i] = rs.getString("Name");
            i++;
        }

        st.close();
        con.close();
        return citiesNames;
    }

    /* Save the image, name and description of the city based on a given city id */
    
    public void saveCity(int id, Image img, String title, String desc) throws SQLException, IOException {

        String sql = "UPDATE City SET Name = \"" + title + "\", Description = \"" + desc + "\", Photo = ? WHERE ID = " + id;
        Connection con = connect();
        PreparedStatement ps = con.prepareStatement(sql);

        BufferedImage bi = SwingFXUtils.fromFXImage(img, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "PNG", baos);
        byte[] b = baos.toByteArray();
        InputStream is = new ByteArrayInputStream(b);

        ps.setBinaryStream(1, is, is.available());
        ps.execute();

        System.out.println("CITY " + id + " TITLE CHANGED TO " + title);
        System.out.println("CITY " + id + " DESCRIPTION CHANGED TO " + desc);
        System.out.println("CITY " + id + " IMAGE CHANGED");

        ps.close();
        con.close();
    }

    /* Return all of the names of a location type given the requested type name */
    
    public String[] getPlacesByType(String type) throws SQLException {

        String placesNames[] = new String[12];
        String sql = "SELECT Name FROM Places WHERE Type LIKE '" + type + "'";
        int i = 0;

        Connection con = this.connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            placesNames[i] = rs.getString("Name");
            i++;
        }

        st.close();
        con.close();
        return placesNames;
    }

    /* Change place name, image and description by the given information based on a given place name  */
    
    public void savePlace(String name, Image img, String title, String desc) throws SQLException, IOException {

        String sql = "UPDATE Places SET Name = \"" + title + "\", Description = \"" + desc + "\", Photo = ? WHERE Name LIKE \"" + name + "\"";
        Connection con = connect();
        PreparedStatement ps = con.prepareStatement(sql);

        BufferedImage bi = SwingFXUtils.fromFXImage(img, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "PNG", baos);
        byte[] b = baos.toByteArray();
        InputStream is = new ByteArrayInputStream(b);

        ps.setBinaryStream(1, is, is.available());
        ps.execute();

        System.out.println("PLACE " + name + " TITLE CHANGED TO " + title);
        System.out.println("PLACE " + name + " DESCRIPTION CHANGED TO " + desc);
        System.out.println("PLACE " + name + " IMAGE CHANGED");

        ps.close();
        con.close();
    }

    /* Change must see image, title and description by the given information based on a given must see id */
    
    public void saveMustSee(int id, Image img, String title, String desc) throws SQLException, IOException {

        String sql = "UPDATE Must_See SET Title = \"" + title + "\", Description = \"" + desc + "\", Photo = ? WHERE ID = " + id;
        Connection con = connect();
        PreparedStatement ps = con.prepareStatement(sql);

        BufferedImage bi = SwingFXUtils.fromFXImage(img, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "PNG", baos);
        byte[] b = baos.toByteArray();
        InputStream is = new ByteArrayInputStream(b);

        ps.setBinaryStream(1, is, is.available());
        ps.execute();

        System.out.println("MUST SEE " + id + " TITLE CHANGED TO " + title);
        System.out.println("MUST SEE " + id + " DESCRIPTION CHANGED TO " + desc);
        System.out.println("MUST SEE " + id + " IMAGE CHANGED");

        ps.close();
        con.close();
    }
}
