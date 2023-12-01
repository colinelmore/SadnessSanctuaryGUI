package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UiFacade {
    // Add fields for managing tasks, columns, etc.

    private User user;
    private Project currentProject;
    private List<Column> columns;
    private String currentUserName;


    public UiFacade() {
        // Initialize other managers and data structures
        columns = new ArrayList<Column>();
    }

    //add a login method, and a signup method, each is one line and just calls the appropriate method on the usermanager

    public boolean userLogin(String userName, String userPassword) {
    user = UserManagement.getInstance().getUser(userName, userPassword);
    return user != null;
    }

    public void login(String userName, String userPassword) {
        if (userLogin(userName, userPassword)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
    }
    
    public void logout() {
            ProjectManager.getInstance().saveProjects();
            UserManagement.getInstance().saveUsers();
            user = null; // Reset the current user after logout
    }

    // Task-related methods

    public boolean addProject(String name){
        return ProjectManager.getInstance().addProject(name);
    }

    public boolean addUser(String firstName, String lastName, String userName, String password, String email, String phoneNumber, String address, String type) {
            
            return UserManagement.getInstance().addUser(firstName, lastName, userName, password, email, phoneNumber, address);
        
    }

    public Object getCurrentUser() {
        return user;
    }

    public void getUserByName(String username, String password) {
        user = UserManagement.getInstance().getUser(username, password);
        currentUserName = username;
    }
    public String getCurrentUserName()
    {
        return currentUserName;
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public void getProjectByName(String name) {
        currentProject = ProjectManager.getInstance().getProject(name);
    }

    public Task getTaskByName(String name) {
        return currentProject.getTaskFromProjectByName(name);
    }
   
    public boolean addColumn(String columnName) {
        if (user != null) {
            return currentProject.addColumn(columnName);
        }
        return false; // User is not logged in
    }

    public void moveTaskToColumn(Task task, Column targetColumn) {
        Column column = currentProject.getColumnOfTask(task);
        column.moveColumnTask(task, targetColumn);
    }

    public void moveTaskToColumnByName(String taskName, String targetColumnName) {
        Task task = getTaskByName(taskName);
        Column targetColumn = currentProject.getColumnByName(targetColumnName);
        moveTaskToColumn(task,targetColumn);
    }
    
    public boolean addTask(String taskName, int taskPriority, Date taskDueDate, String taskNotes, String columnName) {
        if (user != null) {
//             currentProject.addTask(taskName, taskPriority, taskDueDate, taskNotes, columnName);
            // Create a new task using the provided information
         //   Task newTask = new Task(taskName, taskPriority, taskDueDate, String taskNotes , ArrayList<Comment> taskComments);
            // Add the task using the TaskManager
            return currentProject.addTask(taskName, taskPriority, taskDueDate, taskNotes, columnName);
        }
        return false; // User is not logged in
    }

    public boolean addTaskComments(Task task, String commentText, boolean completed) {
        task.addComment(user.getUserName(), commentText, completed);
        return false; // User is not logged in
    }

    public void replyToComment(Comment comment, String text, Boolean completed) {
        comment.addReply(user.getUserName(),text,completed);
    }



    public static void main(String[] args){
        //?
    }
}