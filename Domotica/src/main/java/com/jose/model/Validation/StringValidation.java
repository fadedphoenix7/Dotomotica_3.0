package com.jose.model.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidation {

    public static boolean noInitSpace(String text){
        boolean isOnlySpace;
        String pat = "^[\\s].*";
        isOnlySpace = Pattern.matches(pat, text);
        return isOnlySpace;
    }

    public static boolean noNumbers(String text){
        boolean haveNumbers = false;
        String pat = ".*[\\d].*";
        haveNumbers = Pattern.matches(pat, text);
        return !haveNumbers;
    }

    public static boolean noRareChars(String text){
        boolean haveNumbers = false;
        String pat = ".*[\\W\\S].*";
        haveNumbers = Pattern.matches(pat, text);
        return !haveNumbers;
    }

    public static boolean noValidChars(String text){
        boolean haveSpecialCharacters = false;
        String pat = "[^\\w\\_\\+\\@\\?\\.].*";
        haveSpecialCharacters = Pattern.matches(pat, text);
        System.out.println(text + "  " + haveSpecialCharacters);
        return haveSpecialCharacters;
    }

    public static boolean noSpace(String text){
        boolean haveSpace = false;
        String pat = ".+[\\s].*";
        haveSpace = Pattern.matches(pat,text);
        return haveSpace;
    }

    public static boolean arroba(String text){
        boolean haveArroba = false;
        String pat = ".*@.*\\.com.*";
        haveArroba = Pattern.matches(pat,text);
        return haveArroba;
    }

}
