module com.example.statystyki {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.statystyki to javafx.fxml;
    exports com.example.statystyki;
}