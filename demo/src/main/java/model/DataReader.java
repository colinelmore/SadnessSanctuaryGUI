package model;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader {

    public static ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();

        try {
            FileReader reader = new FileReader("demo/src/main/java/data/users.json");  // Corrected the file path
            JSONParser parser = new JSONParser();
            JSONArray userListJSON = (JSONArray) parser.parse(reader);

            for (int i = 0; i < userListJSON.size(); i++) {
                JSONObject userJSON = (JSONObject) userListJSON.get(i);
                UUID id = UUID.fromString((String) userJSON.get("id"));
                String firstName = (String) userJSON.get("firstName");
                String lastName = (String) userJSON.get("lastName");
                String userName = (String) userJSON.get("userName");
                String password = (String) userJSON.get("password");
                String email = (String) userJSON.get("email");
                String phoneNumber = (String) userJSON.get("phoneNumber");
                String address = (String) userJSON.get("address");
                Role userRole = Role.valueOf((String) userJSON.get("userRole"));  // Assuming Role is an enum
                
                userList.add(new User(id, firstName, lastName, userName, password, email, phoneNumber, address, userRole));
            }
            return userList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Project> getProjects() {
        ArrayList<Project> projectList = new ArrayList<>();

        try {
            FileReader reader = new FileReader("json/projects.json");  // Corrected the file path
            JSONParser parser = new JSONParser();
            JSONArray projectListJSON = (JSONArray) parser.parse(reader);

            for (int i = 0; i < projectListJSON.size(); i++) {
                JSONObject projectJSON = (JSONObject) projectListJSON.get(i);
                String projectName = (String) projectJSON.get("projectName");
                // Assuming projectDate is stored as a string, you may need to parse it accordingly
                ArrayList<Column> columns = getColumns((JSONArray)projectJSON.get("columns"));

                String columnName = (String) projectJSON.get("columnName"); 

                projectList.add(new Project(projectName, columns));
            }
            return projectList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

     public static ArrayList<Column> getColumns(JSONArray columnListJSON) {
        ArrayList<Column> columnList = new ArrayList<>();

        for (int i = 0; i < columnListJSON.size(); i++) {
            JSONObject columnJSON = (JSONObject) columnListJSON.get(i);
            String columnName = (String) columnJSON.get("columnName");
            ArrayList<Task> columnTaskList = getTasks((JSONArray)columnJSON.get("tasks"));

            columnList.add(new Column(columnName, columnTaskList));
        }
        return columnList;
    }

    public static ArrayList<Task> getTasks(JSONArray taskListJSON) {
        ArrayList<Task> taskList = new ArrayList<>();

        for (int i = 0; i < taskListJSON.size(); i++) {
            JSONObject taskJSON = (JSONObject) taskListJSON.get(i);
            String taskName = (String) taskJSON.get("taskName");
            int taskPriority = ((Long) taskJSON.get("taskPriority")).intValue();
            Date taskDueDate = parseDate((String) taskJSON.get("taskDueDates"));
            String taskNotes = (String) taskJSON.get("taskNotes");
            ArrayList<Comment> taskComments = getComments((JSONArray)taskJSON.get("comments"));

            taskList.add(new Task(taskName, taskPriority, taskDueDate, taskNotes, taskComments)); //replace null with taskComments
        }
        return taskList;
    }

    public static ArrayList<Comment> getComments(JSONArray commentListJSON){
        ArrayList<Comment> commentList = new ArrayList<>();
        if(commentListJSON != null) {
            for (int i = 0; i < commentListJSON.size(); i++) {
                JSONObject commentJSON = (JSONObject) commentListJSON.get(i);
                String authorUserName = (String) commentJSON.get("authorUserName");
                String commentText = (String) commentJSON.get("commentText");
                boolean completed = (boolean) commentJSON.get("completed");
                ArrayList<Comment> replies = getComments((JSONArray) commentJSON.get("replies"));
                commentList.add(new Comment(authorUserName, commentText, completed, replies));
            }
        }
        return commentList;
    }

    // Add a method to parse Date from a string
    private static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // format
        try {
            if(dateString != null) {
                Date parseDate = dateFormat.parse(dateString);
                return parseDate;
            }
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the parsing exception
            return null;
        }
        return null;
    }

    public static void main(String[] args){
        ArrayList<Project> projects = getProjects();

        for(Project project : projects){
            System.out.println(project);
        }
        DataWriter.saveProjects();
    }
}
