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
public class Message extends User{
    
    private int messageID;
    private int senderID;
    private int receiverID;
    private String Message;
    private String Date;

    public Message(int messageID, int senderID, int receiverID, String Message, String Date, int userID, String userName, String userSurname, String userNickName, String userPassword, String userEmail, String userBirthDate, int userAuthority) {
        super(userID, userName, userSurname, userNickName, userPassword, userEmail, userBirthDate, userAuthority);
        this.messageID = messageID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.Message = Message;
        this.Date = Date;
    }

   

    public int getMessageID() {
        return messageID;
    }


    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    
    
    
}
