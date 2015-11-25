package com.anaish.aconex.parser.validator;

/**
 * Created by Andrew on 11/23/2015.
 * Validates a single phone number
 */
public class PhoneNumberValidator {

    public boolean isValid(String number){
        return !number.trim().contains(" ");
    }
}
