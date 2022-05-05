
package nypProject;


public class Comment {

        private int commentID;
        private String userNickName;
        private String movieName;
        private String comment;
        private String commentDate;

    public Comment(int commentID, String userNickName, String movieName, String comment, String commentDate) {
        this.commentID = commentID;
        this.userNickName = userNickName;
        this.movieName = movieName;
        this.comment = comment;
        this.commentDate = commentDate;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

   
}
