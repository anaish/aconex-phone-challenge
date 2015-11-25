package com.anaish.aconex.command;

import java.io.File;

/**
 * Created by Andrew on 11/23/2015.
 * Holds command results
 */
public class CommandInputResult {

    private String phoneNumberFile;
    private String dictionaryFile;

    public String getPhoneNumberFile() {
        return phoneNumberFile;
    }

    public void setPhoneNumberFile(String phoneNumberFile) {
        this.phoneNumberFile = phoneNumberFile;
    }

    public String getDictionaryFile() {
        return dictionaryFile;
    }

    public void setDictionaryFile(String dictionaryFile) {
        this.dictionaryFile = dictionaryFile;
    }

    public boolean isReadFromFile(){
        final String phoneNumberFile = this.getPhoneNumberFile();
        return phoneNumberFile !=null && new File(phoneNumberFile).exists();
    }

}
