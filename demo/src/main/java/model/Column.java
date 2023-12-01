package model;

import java.util.ArrayList;

public class Column {
    private String columnName;
    private ArrayList<Task> columnTaskList;
    


    public Column(String columnName, ArrayList<Task> columnTaskList ) {
        this.columnName = columnName;
        this.columnTaskList = columnTaskList;
    }
    public Column(String columnName) {
        this.columnName = columnName;
        this.columnTaskList = new ArrayList<Task>();
    }


    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public ArrayList<Task> getColumnTaskList() {
        return this.columnTaskList;
    }

    public Task getTaskFromColumnByName(String name) {
        for(int i=0; i < columnTaskList.size(); i++)
        {
            if(columnTaskList.get(i).getTaskName().equals(name))
            {
                return columnTaskList.get(i);
            }
        }
        return null;
    }

    public Task getTaskFromColumn(Task task) {
        for(int i=0; i < columnTaskList.size(); i++)
        {
            if(columnTaskList.get(i).equals(task))
            {
                return columnTaskList.get(i);
            }
        }
        return null;
    }
    public boolean addColumnTask(Task task) {
        return this.columnTaskList.add(task);
    }

    public boolean removeColumnTask(Task task) {
        return this.columnTaskList.remove(task);
    }

    public boolean moveColumnTask(Task task, Column columnMoveTo) {
        this.removeColumnTask(task);
        return columnMoveTo.addColumnTask(task);
    }

    public String getName() {
        return this.columnName;
    }

    

    @Override
    public String toString() {
        String toReturn = "";
        for(int i=0; i<columnTaskList.size();i++)
        {
            toReturn += columnTaskList.get(i).toString();
        }

        return "Column Name: " + columnName + "\n" + toReturn;
    }
}

