module ru.nstu.anotationeditor {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nstu.anotationeditor to javafx.fxml;
    exports ru.nstu.anotationeditor;
    exports ru.nstu.anotationeditor.MVP;
    opens ru.nstu.anotationeditor.MVP to javafx.fxml;
    exports ru.nstu.anotationeditor.Data;
    opens ru.nstu.anotationeditor.Data to javafx.fxml;
}