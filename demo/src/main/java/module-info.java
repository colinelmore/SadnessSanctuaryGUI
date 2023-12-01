module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    //applies to all folders that have java files
    opens com.example to javafx.fxml;
    exports com.example;

    // opens model to javafx.fxml;
    // exports model; 
}
