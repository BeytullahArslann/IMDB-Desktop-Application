/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nypProject;

/**
 *
 * @author beytu
 */
public class WatchList extends Movie {
    private int userID;
    private String userNickName;
    private String addDate;
    private int Status;

    public WatchList(int userID, String userNickName, String date, int Status, int id, String name, String director, String writers, int ımdbscore, String relasedate, String type) {
        super(id, name, director, writers, ımdbscore, relasedate, type);
        this.userID = userID;
        this.userNickName = userNickName;
        this.addDate = date;
        this.Status = Status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getDate() {
        return addDate;
    }

    public void setDate(String date) {
        this.addDate = date;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
    
}
