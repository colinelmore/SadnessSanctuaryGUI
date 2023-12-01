package model;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;;

public class DataWriter {
    public static boolean saveUsers() {
		ArrayList<User> users = UserManagement.getInstance().getUserList();
		JSONArray jsonUsers = new JSONArray();
		
		//creating all the json objects
		for(User user : users) {
			jsonUsers.add(getUserJSON(user));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter("json/users.json")) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
			return false;
        }
		return true;
	}

	public static boolean saveProjects() {
		ArrayList<Project> projects = ProjectManager.getInstance().getAllProjects();
		JSONArray jsonProjects = new JSONArray();
	
		for (Project project : projects) {
			jsonProjects.add(getProjectJSON(project));
		}
	
		try (FileWriter file = new FileWriter("json/projects.json")) {
			file.write(jsonProjects.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	 public static JSONObject getProjectJSON(Project project) {

		JSONObject projectDetails = new JSONObject();
		
		JSONArray columnJSONS = new JSONArray();
		for(Column column : project.getColumns()){
			columnJSONS.add(getColumnJSON(column));
		}

		projectDetails.put("projectId", project.getProjectId().toString());
		projectDetails.put("projectName", project.getProjectName());
		projectDetails.put("columns", columnJSONS);
		// Add any other properties of the Project class that you want to save here.
		return projectDetails;
	}	
	
	
	public static JSONObject getColumnJSON(Column column) {
		JSONObject columnDetails = new JSONObject();
		columnDetails.put("tasks", column.getColumnTaskList());
		columnDetails.put("columnName", column.getName());

		
		JSONArray tasksJSONS = new JSONArray();
		ArrayList<Task> tasks = column.getColumnTaskList();
		for(Task task : tasks){
			tasksJSONS.add(getTaskJSON(task));
		}

		columnDetails.put("tasks", tasksJSONS);

		return columnDetails;
	}

	public static JSONObject getTaskJSON(Task task){
		JSONObject taskDetails = new JSONObject();
		taskDetails.put("taskName", task.getTaskName());
		taskDetails.put("taskPriority", task.getTaskPriority());
		taskDetails.put("taskDueDate", toDateString(task.getTaskDueDate()));
		taskDetails.put("taskNotes", task.getTaskNotes());
		JSONArray commentsJSON = new JSONArray();

		if(task.getComments() != null) {
			for (Comment comment : task.getComments()) {
				commentsJSON.add(getCommentJSON(comment));
			}
			taskDetails.put("comments", commentsJSON);
		} else {
			commentsJSON = null;
			taskDetails.put("comments", commentsJSON);
		}
		return taskDetails;
	}

	public static JSONObject getCommentJSON(Comment comment){
		JSONObject commentDetails = new JSONObject();
		commentDetails.put("authorUserName", comment.getCommentAuthor());
		commentDetails.put("commentText", comment.getCommentText());
		commentDetails.put("completed", comment.getCompletion());
		JSONArray repliesJSON = new JSONArray();

		for(Comment reply : comment.getReplies()) {
			repliesJSON.add(getCommentJSON(reply));
		}

		return commentDetails;
	}

	public static JSONObject getUserJSON(User user) {
		JSONObject personDetails = new JSONObject();
		personDetails.put("id", user.getId().toString());
		personDetails.put("firstName", user.getFirstName());
		personDetails.put("lastName", user.getLastName());
        personDetails.put("userName", user.getUserName());
		personDetails.put("password", user.getPassword());
		personDetails.put("email", user.getEmail());
		personDetails.put("phoneNumber", user.getPhoneNumber());
		personDetails.put("address", user.getAddress());
		personDetails.put("userRole", user.getUserRole().toString());
        return personDetails;
	}

	

	public static String toDateString(Date date){
		if(date != null) {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.format(date);
		} else {
			return null;
		}
	}

	public static void main(String[] args){
		DataWriter.saveProjects();
	}
}
