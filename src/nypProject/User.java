
package nypProject;

import javax.swing.JTextField;


public class User {
    private int userID;
    private String userName;
    private String userSurname;
    private String userNickName;
    private String userPassword;
    private String userEmail;
    private String userBirthDate;
    private int userAuthority;

    public User(int userID, String userName, String userSurname, String userNickName, String userPassword, String userEmail, String userBirthDate, int userAuthority) {
        this.userID = userID;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userNickName = userNickName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userBirthDate = userBirthDate;
        this.userAuthority = userAuthority;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(String userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public int getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(int userAuthority) {
        this.userAuthority = userAuthority;
    }

    void setUserNickName(JTextField txtNickName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
