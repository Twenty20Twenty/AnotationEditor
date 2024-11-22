package ru.nstu.anotationeditor.Data;

public class SampleClass2 {
    @Editable
    private int number1;

    private String text1;

    private boolean flag;

    @Editable
    private float number2;

    private int number3;

    @Editable
    private long number4;

    @Editable
    private String text2;


    public SampleClass2(int number1, String text1, boolean flag, float number2, int number3, long number4, String text2) {
        this.number1 = number1;
        this.text1 = text1;
        this.flag = flag;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.text2 = text2;
    }

    public int getNumber() {
        return number1;
    }

    public String getText() {
        return text1;
    }

    public boolean isFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return Integer.toString(number1) + "; " + text1 + "; " + Boolean.toString(flag) + "; "
                + Float.toString(number2) + "; " + Integer.toString(number3) + "; " + Long.toString(number4) + "; " + text2;
    }
}