module com.example.sogame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sogame to javafx.fxml;
    exports com.example.sogame;
    exports com.example.sogame.status;
    opens com.example.sogame.status to javafx.fxml;
    exports com.example.sogame.characters;
    opens com.example.sogame.characters to javafx.fxml;
    exports com.example.sogame.effectStatusClasses;
    opens com.example.sogame.effectStatusClasses to javafx.fxml;
}