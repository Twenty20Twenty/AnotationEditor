package ru.nstu.anotationeditor.Data;

public class SampleClass1 {
    @Editable
    private int number1;

    private String text;

    @Editable
    private boolean flag;

    @Editable
    private float number2;

    @Editable
    private int number3;

    @Editable
    private long number4;


    public SampleClass1(int number1, String text, boolean flag, float number2, int number3, long number4) {
        this.number1 = number1;
        this.text = text;
        this.flag = flag;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
    }

    public int getNumber() {
        return number1;
    }

    public String getText() {
        return text;
    }

    public boolean isFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return Integer.toString(number1) + "; " + text + "; " + Boolean.toString(flag) + "; " + Float.toString(number2) + "; " + Integer.toString(number3) + "; " + Long.toString(number4);
    }
}