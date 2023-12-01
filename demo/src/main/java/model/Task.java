package model;

import java.util.ArrayList;
import java.util.Date;

public class Task {
    // Attributes
    private String taskName;
    private int taskPriority;
    private Date taskDueDate;
    private String taskNotes;
    private ArrayList<Comment> taskComments;
    private User assignedUser;
    private String status;


    // Constructor
    public Task(String taskName, int taskPriority, Date taskDueDate, String taskNotes , ArrayList<Comment> taskComments) {
        this.taskName = taskName;
        this.taskPriority = taskPriority;
        this.taskDueDate = taskDueDate;
        this.taskNotes = taskNotes;
        this.taskComments = taskComments; 
        this.status = "ToDo";
    }


    // Getters and Setters
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Date getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(Date taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public String getTaskNotes() {
        return taskNotes;
    }

    public void setTaskNotes(String taskNotes) {
        this.taskNotes = taskNotes;
    }

     public ArrayList<Comment> getComments(){
        return taskComments;
    }

    public boolean addComment(String authorName, String commentText, boolean completed){
        
        Comment comment = new Comment(authorName, commentText, completed, new ArrayList<Comment>());
        taskComments.add(comment);
        return true;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
    

    

    // Additional Methods
    
    public User getAssignedUser()
    {
        return assignedUser;
    }

    public void assignedUser(User user)
    {
        this.assignedUser = user; 
    }

    public void unAssignedUser()
    {
        this.assignedUser = null;
    }
   

    @Override
    public String toString() {
        String toReturn = "";
        for(int i=0; i<taskComments.size();i++)
        {
            toReturn += taskComments.get(i).toString();
        }


        return "Task Name: " + taskName +
               "\nPriority: " + taskPriority +
               "\nDue Date: " + taskDueDate +
               "\nNotes: " + taskNotes +
               toReturn;

    }
}


