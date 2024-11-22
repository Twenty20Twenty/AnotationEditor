package ru.nstu.anotationeditor.MVP;

import ru.nstu.anotationeditor.Data.SampleClass;

public class EditorModel {
    private Object object;

    public EditorModel() {
        object = new SampleClass(42, "Hello, World!", true);
    }

    public Object getSampleObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
