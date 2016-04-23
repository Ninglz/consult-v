package com.dsecet.util;

import java.util.regex.Pattern;

public class Validations{

    private static Pattern REAL_NAME_PATTERN = Pattern.compile("^([\\u4e00-\\u9fa5]+|([a-zA-Z]+\\s?)+)$");
    ;
    private static Pattern CELL_NO_PATTERN = Pattern
            .compile("^1(\\d{10})$");

    private static Pattern EMAIL_PATTERN = Pattern
            .compile("\\b(^(\\S+@).+((\\.com)|(\\.net)|(\\.org)|(\\.info)|(\\.edu)|(\\.mil)|(\\.gov)|(\\.biz)|(\\.ws)|(\\.us)|(\\.tv)|(\\.cc)|(\\..{2,2}))$)\\b$");

    private static Pattern USERNAME_PATTERN = Pattern
            .compile("^[A-Za-z]{1}([A-Za-z0-9_]{5,15})$");

    private static Pattern PASSWORD_PATTERN = Pattern
            .compile("^[A-Za-z0-9_]{6,16}$");

    private static Pattern SERIAL_PATTERN = Pattern
            .compile("^[0-9]{15,20}$");

    private static IdCardHelper ID_CARD_PATTERN = new IdCardHelper();

    private static Pattern AMOUNT_PATTERN = Pattern
            .compile("^-?\\d+(\\.\\d+)?$");

    private static Pattern INT_PATTERN = Pattern
            .compile("^[0-9]+$");

    private static Pattern ZIP_CODE = Pattern.compile("^[0-9]{6}$");

    private static Pattern VERIFY_CODE = Pattern.compile("^[0-9]{6}$");

    private static Pattern MAC_INDREESS = Pattern.compile("([a-fA-F0-9]{2}[:\\-\\.]){5}[a-fA-F0-9]{2}");

    private static final boolean validatePattern(Pattern pattern, String input){
        if(pattern.matcher(input).find()) return true;
        return false;
    }

    public static final boolean validateMac(String mac){
        return validatePattern(MAC_INDREESS, mac);
    }

    public static final boolean validateRealName(String realName){
        return validatePattern(REAL_NAME_PATTERN, realName);
    }

    public static final boolean validateCellNo(String cellNo){
        return validatePattern(CELL_NO_PATTERN, cellNo);
    }

    public static final boolean validateEmail(String email){
        return validatePattern(EMAIL_PATTERN, email);
    }

    public static final boolean validateUserName(String userName){
        return validatePattern(USERNAME_PATTERN, userName);
    }

    public static final boolean validatePassword(String password){
        return validatePattern(PASSWORD_PATTERN, password);
    }

    public static final boolean validateSerial(String serial){
        return validatePattern(SERIAL_PATTERN, serial);
    }

    public static final boolean validateIdCard(String idCard){
        return ID_CARD_PATTERN.isValidate18Idcard(idCard);
    }

    public static final boolean validateAmount(double amount){
        return validatePattern(AMOUNT_PATTERN, String.valueOf(amount));
    }

    public static final boolean validateInt(String num){
        return validatePattern(INT_PATTERN, String.valueOf(num));
    }

    public static final boolean validateZipCode(String zipCode){
        return validatePattern(ZIP_CODE, zipCode);
    }

    public static final boolean validateWithdrawAmount(double amount) {return amount >=100;}

    public static final boolean validateVerifyCode(String code){return validatePattern(ZIP_CODE, code);}
}
