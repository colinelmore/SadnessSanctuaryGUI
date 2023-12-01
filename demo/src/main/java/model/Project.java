package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Project {
    private String projectName;
    private UUID projectId;
    private ArrayList<Column> columns;

    // Constructor for the Project class
    public Project(String projectName, ArrayList<Column> columns) {
        this.projectName = projectName;
        this.columns = columns;
        this.projectId = UUID.randomUUID();
    }

    public Project(String projectName){
        this.projectName = projectName;
        this.columns = new ArrayList<>();
        this.projectId = UUID.randomUUID();
    }

    public String getProjectName() {
        return projectName;
    }

    public Object getProjectId() {
        return projectId;
    }

    public boolean addColumn(String columnName){
        Column column = new Column(columnName);
        columns.add(column);
        return true;
    }

    public boolean addTask(String taskName, int taskPriority, Date taskDueDate, String taskNotes, String columnName){
        Column column = getColumnByName(columnName);
        Task task = new Task(taskName, taskPriority, taskDueDate, taskNotes, new ArrayList<Comment>());
        column.addColumnTask(task);
        return true;
    }

    public Task getTaskFromProjectByName(String name) {
        for(int i=0; i < columns.size(); i++)
        {
            Task task = columns.get(i).getTaskFromColumnByName(name);
            if(task != null) {
                if (task.getTaskName().equals(name)) {
                    return task;
                }
            }
        }
        return null;
    }

    public Column getColumnOfTask(Task task) {
        for(int i=0; i < columns.size(); i++)
        {
            Task tempTask = columns.get(i).getTaskFromColumn(task);
            if(tempTask != null) {
                if (tempTask.equals(task)) {
                    return columns.get(i);
                }
            }
        }
        return null;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public Column getColumnByName(String columnName) {
        for(int i=0; i < columns.size(); i++)
        {
            if(columns.get(i).equals(null)) {
                return null;
            }
            else if(columns.get(i).getColumnName().equals(columnName))
            {
                return columns.get(i);
            }
        }
        return null;
    }

    public String toString() {

        String toReturn = "";
        for(int i=0; i<columns.size();i++)
        {
            toReturn += columns.get(i).toString();
        }

        return projectName + "\n" + toReturn;
    }

    
}
 