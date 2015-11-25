package com.anaish.aconex.parser.validator;

/**
 * Created by Andrew on 11/23/2015.
 */
public class PhoneNumberValidator {

    public boolean isValid(String number){
        return !number.contains(" ");
    }
}
