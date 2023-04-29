module com.example.foorumfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.foorumfx to javafx.fxml;
    exports com.example.foorumfx;
}