package com.example;

import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.*;

public class ProjectController implements Initializable {

    @FXML
    private Button add_collab_button;

    @FXML
    private Button back_button;

    @FXML
    private Button new_column_button;

    @FXML
    private HBox hbox_cols;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("In Project Controller");
        UiFacade facade = UiFacade.getInstance();
        facade.getProjectByName("Electric Missiles");
        Project project = facade.getCurrentProject();
        System.out.println("current project " + project);
        new_column_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                project.addColumn("New Column");
                initializeColumn(project.getColumnByName("New Column"), facade);
            }
        });

        ArrayList<Column> columns = project.getColumns();

        for (Column column : columns) {
            initializeColumn(column, facade);
        }

    }

    private void initializeColumn(Column column, UiFacade facade) {
        System.out.println("Column " + column);
        VBox taskBox = new VBox();
        // columnBox.setW
        taskBox.getStyleClass().add("column");
        hbox_cols.getChildren().add(taskBox);
        hbox_cols.setSpacing(10);
        Label title = new Label();
        title.getStyleClass().add("column_title");
        title.setText(column.getColumnName());
        taskBox.getChildren().add(title);

        Button addTask = new Button();
        addTask.setText("Add Task");
        addTask.setWrapText(true);
        addTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Comment> comments = new ArrayList<>();
                Task newTask = new Task("New Task", 1, new Date(), "Test notes", comments);
                column.addColumnTask(newTask);
                initializeTask(newTask, taskBox, column);
            }
        });
        taskBox.getChildren().add(addTask);

        ArrayList<Task> tasks = column.getColumnTaskList();

        for (Task task : tasks) {
            initializeTask(task, taskBox, column);
        }

        taskBox.setOnDragOver(new EventHandler<DragEvent>() { // Drag and Drop code sourced from
                                                              // https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm
            public void handle(DragEvent event) {
                System.out.println("onDragOver");
                if (event.getGestureSource() != taskBox &&
                        event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });

        taskBox.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.println("onDragDropped");
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    Task copiedTask = facade.getTaskByName(db.getString());
                    column.addColumnTask(copiedTask);
                    initializeTask(copiedTask, taskBox, column);
                    success = true;
                }
                event.setDropCompleted(success);

                event.consume();
            }
        });
    }

    private void initializeTask(Task task, VBox columnBox, Column column) {
        Button taskButton = new Button();
        taskButton.setText(task.getTaskName());
        taskButton.setWrapText(true);

        taskButton.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Dragboard db = taskButton.startDragAndDrop(TransferMode.ANY);
                ClipboardContent taskContent = new ClipboardContent();
                taskContent.putString(taskButton.getText());
                db.setContent(taskContent);
            }
        });

        taskButton.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.println("onDragDone");
                if (event.getTransferMode() == TransferMode.MOVE) {
                    columnBox.getChildren().remove(taskButton);
                    column.removeColumnTask(task);
                    System.out.println("DragDone");
                }
                event.consume();
            }
        });

        columnBox.getChildren().add(taskButton);
    }

}