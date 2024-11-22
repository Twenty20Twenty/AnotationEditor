package ru.nstu.anotationeditor.Data;

import java.util.Random;

public class RandomStringGenerator {
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            char randomChar = LETTERS.charAt(index);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
}