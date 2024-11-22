package ru.nstu.anotationeditor.MVP;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nstu.anotationeditor.MainLauncher;

import java.io.IOException;

public class EditorApp extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        EditorModel model = new EditorModel();
        FXMLLoader loader = new FXMLLoader(MainLauncher.class.getResource("EditorView.fxml"));
        Parent root = loader.load();
        EditorView view = loader.getController();
        EditorPresenter presenter = new EditorPresenter(model, view);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Object Editor App");
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
