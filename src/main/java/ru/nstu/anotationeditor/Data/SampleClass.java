package ru.nstu.anotationeditor.Data;

public class SampleClass {
    @Editable
    private final int number;

    @Editable
    private String text;

    private boolean flag;

    public SampleClass(int number, String text, boolean flag) {
        this.number = number;
        this.text = text;
        this.flag = flag;
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public boolean isFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return Integer.toString(number) + "; " + text + "; " + Boolean.toString(flag);
    }
}