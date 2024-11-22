package ru.nstu.anotationeditor.MVP;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ru.nstu.anotationeditor.Data.Editable;
import ru.nstu.anotationeditor.Data.SampleClass;
import ru.nstu.anotationeditor.Data.SampleClass1;
import ru.nstu.anotationeditor.Data.SampleClass2;

import java.lang.reflect.Field;
import java.util.Random;

import static ru.nstu.anotationeditor.Data.RandomStringGenerator.generateRandomString;

public class EditorPresenter {
    private EditorModel model;
    private EditorView view;

    public EditorPresenter(EditorModel model, EditorView view) {
        this.model = model;
        this.view = view;
        view.setPresenter(this);
        go();
    }

    public void go() {
        generateNewObject();

        updateView();
    }

    public void generateNewObject() {
        Random rnd = new Random();
        int flag = rnd.nextInt(3);
        Object newObj;
        switch (flag) {
            case 0:
                newObj = new SampleClass(rnd.nextInt(), generateRandomString(10), false);
                model.setObject(newObj);
                break;
            case 1:
                newObj = new SampleClass1(rnd.nextInt(), generateRandomString(10), true, rnd.nextFloat(), rnd.nextInt(), rnd.nextLong());
                model.setObject(newObj);
                break;
            case 2:
                newObj = new SampleClass2(rnd.nextInt(), generateRandomString(10), false, rnd.nextFloat(), rnd.nextInt(), rnd.nextLong(), generateRandomString(7));
                model.setObject(newObj);
                break;
        }
    }

    public GridPane createEditorGrid2(Object objectToEdit) {
        GridPane grid = new GridPane();
        int row = 0;

        Class<?> clazz = objectToEdit.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Editable.class)) {
                field.setAccessible(true);
                try {
                    Label label = new Label(field.getName() + " (" + field.getType() + ")");
                    TextField textField = new TextField(String.valueOf(field.get(objectToEdit)));

                    textField.textProperty().addListener((observable, oldValue, newValue) -> {
                        try {
                            if (field.getType() == int.class) {
                                field.setInt(objectToEdit, Integer.parseInt(newValue));
                            } else if (field.getType() == double.class) {
                                field.setDouble(objectToEdit, Double.parseDouble(newValue));
                            } else if (field.getType() == String.class) {
                                field.set(objectToEdit, newValue);
                            } else if (field.getType() == boolean.class) {
                                field.setBoolean(objectToEdit, Boolean.parseBoolean(newValue));
                            } else if (field.getType() == short.class) {
                                field.setShort(objectToEdit, Short.parseShort(newValue));
                            } else if (field.getType() == long.class) {
                                field.setLong(objectToEdit, Long.parseLong(newValue));
                            } else if (field.getType() == float.class) {
                                field.setFloat(objectToEdit, Float.parseFloat(newValue));
                            }

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (NumberFormatException e) {

                        }
                    });

                    grid.add(label, 0, row);
                    grid.add(textField, 1, row);
                    row++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return grid;
    }

    public GridPane createEditorGrid(Object objectToEdit) {
        GridPane grid = new GridPane();
        int row = 0;

        Class<?> clazz = objectToEdit.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Editable.class)) {
                field.setAccessible(true);
                try {
                    Label label = new Label(field.getName() + " (" + field.getType() + ")");
                    TextField textField = new TextField(String.valueOf(field.get(objectToEdit)));
                    if (field.getType() == int.class | field.getType() == long.class | field.getType() == short.class) {
                        textField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent keyEvent) {
                                if (!"-1234567890".contains(keyEvent.getCharacter())) {
                                    keyEvent.consume();
                                }
                            }
                        });
                    } else if (field.getType() == boolean.class) {
                        textField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent keyEvent) {
                                if (!"truefalse".contains(keyEvent.getCharacter())) {
                                    keyEvent.consume();
                                }
                            }
                        });
                    } else if (field.getType() == float.class | field.getType() == double.class) {
                        textField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent keyEvent) {
                                if (!"-1234567890.".contains(keyEvent.getCharacter())) {
                                    keyEvent.consume();
                                }
                            }
                        });
                    }
                    // Установка уникального fx:id для текстового поля
                    textField.setId(field.getName());

                    grid.add(label, 0, row);
                    grid.add(textField, 1, row);
                    row++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return grid;
    }

    public void updateFields() {
        Object objectToEdit = model.getSampleObject();
        Class<?> clazz = objectToEdit.getClass();

        // Обновляем поля объекта с использованием рефлексии
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Editable.class)) {
                field.setAccessible(true);
                try {
                    // Получаем текстовое поле по имени поля
                    TextField textField = (TextField) view.getGridPane().lookup("#" + field.getName());
                    if (textField != null) {
                        String newValue = textField.getText();
                        if (newValue.equals("")) {
                            newValue = "0";
                        }
                        // Обновляем поле в объекте с помощью рефлексии
                        if (field.getType() == int.class) {
                            field.setInt(objectToEdit, Integer.parseInt(newValue));
                        } else if (field.getType() == double.class) {
                            field.setDouble(objectToEdit, Double.parseDouble(newValue));
                        } else if (field.getType() == String.class) {
                            field.set(objectToEdit, newValue);
                        } else if (field.getType() == boolean.class) {
                            field.setBoolean(objectToEdit, Boolean.parseBoolean(newValue));
                        } else if (field.getType() == short.class) {
                            field.setShort(objectToEdit, Short.parseShort(newValue));
                        } else if (field.getType() == long.class) {
                            field.setLong(objectToEdit, Long.parseLong(newValue));
                        } else if (field.getType() == float.class) {
                            field.setFloat(objectToEdit, Float.parseFloat(newValue));
                        }
                    }
                } catch (IllegalAccessException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        updateView();
    }

    public void updateView() {
        GridPane newGridPane = createEditorGrid(model.getSampleObject());

        Pane parent = (Pane) view.getGridPane().getParent();
        parent.getChildren().remove(view.getGridPane());
        view.setGridPane(newGridPane);
        parent.getChildren().add(view.getGridPane());
    }

    public void printConsole() {
        System.out.println(model.getSampleObject().toString());
        //updateView();
    }
}
