package ru.nstu.anotationeditor.MVP;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class EditorView {
    private EditorPresenter presenter;
    @FXML
    private Pane mainPane;
    @FXML
    private GridPane gridPane;

    public void setPresenter(EditorPresenter presenter) {
        this.presenter = presenter;
    }

    @FXML
    private void goAction() {
        presenter.go();
    }

    @FXML
    private void printObjectAction() {
        presenter.printConsole();
    }

    @FXML
    private void updateFieldsAction() {
        presenter.updateFields();
    }

    public Pane getMainPane() {
        return mainPane;
    }

    public void setMainPane(Pane mainPane) {
        this.mainPane = mainPane;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }
}
