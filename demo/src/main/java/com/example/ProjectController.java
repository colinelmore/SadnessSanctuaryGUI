package com.example;

import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        ArrayList<Column> columns = project.getColumns();

        for (Column column : columns) {
            System.out.println("Column " + column);
            VBox columnBox = new VBox();
            // columnBox.setW
            columnBox.getStyleClass().add("column");
            hbox_cols.getChildren().add(columnBox);
            hbox_cols.setSpacing(10);
            Label title = new Label();
            title.getStyleClass().add("column_title");
            title.setText(column.getColumnName());
            columnBox.getChildren().add(title);

            ArrayList<Task> tasks = column.getColumnTaskList();

            for (Task task : tasks) {
                initializeTask(task, columnBox, column);
            }

            columnBox.setOnDragOver(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    System.out.println("onDragOver");
                    if (event.getGestureSource() != columnBox &&
                            event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }
                    event.consume();
                }
            });

            columnBox.setOnDragDropped(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    System.out.println("onDragDropped");
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasString()) {
                        Task copiedTask = facade.getTaskByName(db.getString());
                        column.addColumnTask(copiedTask);
                        initializeTask(copiedTask,columnBox,column);
                        success = true;
                    }
                    event.setDropCompleted(success);

                    event.consume();
                }
            });

        }

    }

    private void initializeTask(Task task, VBox columnBox, Column column) {
        Button taskButton = new Button();
        taskButton.setText(task.getTaskName());
        taskButton.setWrapText(true);

        taskButton.setOnDragDetected(new EventHandler<MouseEvent>() { // Drag and Drop code sourced from
                                                                      // https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm
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