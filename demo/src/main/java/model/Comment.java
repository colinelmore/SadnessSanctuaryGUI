package model;

import java.util.ArrayList;

// The Comments class is responsible for managing comments related to a user.
public class Comment {
    // The User who owns these comments.
    private String authorName;
    // An ArrayList to store the comments.
    private String commentText;
    private boolean completed;
    private ArrayList<Comment> replies;

    // Constructor for the Comments class.
    public Comment(String authorUserName, String commentText, boolean completed, ArrayList<Comment> replies) {
        this.authorName = authorUserName;
        this.commentText = commentText;
        this.completed = completed;
        this.replies = replies;
    }


    // Method to add a comment. Returns true if the comment was added successfully.
/*    public boolean addComment(Comment comment) {
        return this.comments.add(comment);
    }

    public String addCommentText(){
        return this.commentText;
    }

    // Method to remove a comment. Returns true if the comment was removed successfully.
    public boolean removeComment(String comment) {
        return comments.remove(comment);
    } */
   


    public String getCommentAuthor() {
        return this.authorName;
    }

    public void setCommentAuthor(String authorName) {
        this.authorName = authorName;
    }

    public String getCommentText() {
        return this.commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    


    public void setCompletion(boolean completed) {
        this.completed = completed;
    }

    public boolean getCompletion() {
        return this.completed;
    }

    public void addReply(String authorName, String commentText, boolean completed) {
        this.replies.add(new Comment(authorName, commentText, completed, new ArrayList<Comment>()));
    }

    public ArrayList<Comment> getReplies() 
    {
        return this.replies;
    }

    public void removeReply(Comment reply) {
        this.replies.remove(reply);
    }


    // Overridden toString method to provide a string representation of the Comments object.
    @Override
    public String toString() {

        return "Comments{" +
                "user=" + authorName +
                ",completed=" + completed +
                ",comments=" + replies +
                '}';
    }
}