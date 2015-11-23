package com.anaish.aconex.input.validation;

import com.anaish.aconex.input.InputResult;

import java.io.File;

/**
 * Created by Andrew on 11/23/2015.
 */
public class InputValidator {

    public boolean isReadFromFile(final InputResult inputResult){
        final String phoneNumberFile = inputResult.getPhoneNumberFile();
        return phoneNumberFile !=null && new File(phoneNumberFile).exists();
    }



}
