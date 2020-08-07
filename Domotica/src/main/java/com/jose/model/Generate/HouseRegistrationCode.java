package com.jose.model.Generate;


import java.util.Random;

public class HouseRegistrationCode {

    private static final int size = 20;

    public static String generate(){
        String code = "";
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        code = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(code);
        return code;
    }
}
