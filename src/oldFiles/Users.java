/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oldFiles;

/**
 *
 * @author fefoss454
 */
public class Users {
    
    private int ID;
    private String Type;
    private String Username;
    private String Password;
    private String First_Name;
    private String Last_Name;
    private String Email;

    public Users() {
    }

    public Users(int ID, String Type, String Username, String Password, String First_Name, String Last_Name, String Email) {
        
        this.ID = ID;
        this.Type = Type;
        this.Username = Username;
        this.Password = Password;
        this.First_Name = First_Name;
        this.Last_Name = Last_Name;
        this.Email = Email;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String First_Name) {
        this.First_Name = First_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String Last_Name) {
        this.Last_Name = Last_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
}
