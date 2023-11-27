module com.pharmacyapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.pharmacyapp to javafx.fxml;
    exports com.pharmacyapp;
}