module com.mycompany.preguntame {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.preguntame to javafx.fxml;
    exports com.mycompany.preguntame;
}
