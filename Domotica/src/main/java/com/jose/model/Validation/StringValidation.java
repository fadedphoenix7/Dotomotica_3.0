package com.jose.model.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidation {

    public static boolean noInitSpace(String text){
        boolean isOnlySpace;
        String pat = "^[a-zA-Z].+";
        isOnlySpace = Pattern.matches(pat, text);
        return !isOnlySpace;
    }

    public static boolean noNumbers(String text){
        boolean haveNumbers = false;
        String pat = ".+[\\d].+";
        haveNumbers = Pattern.matches(pat, text);
        System.out.println(haveNumbers);
        return !haveNumbers;
    }

    public static boolean noValidChars(String text){
        boolean haveSpecialCharacters = false;
        String pat = "[\\w_+@?\\-.\\s]+";
        haveSpecialCharacters = Pattern.matches(pat, text);
        System.out.println(haveSpecialCharacters);
        return haveSpecialCharacters;
    }

    public static boolean noSpace(String text){
        boolean haveSpace = false;
        String pat = ".+[\\s].+";
        haveSpace = Pattern.matches(pat,text);
        System.out.println(haveSpace);
        return !haveSpace;
    }


}
