package com.anaish.aconex.input;


import com.anaish.aconex.input.validation.InputValidator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Andrew on 11/23/2015.
 */
public class InputTest {

    final static String mockPhoneNumberFileName = ".\\src\\test\\resources\\mock-phone-number-file.txt";
    final static String mockDictionaryFileName = ".\\src\\test\\resources\\mock-dictionary-file.txt";

    final static String[] readFromFilesCommand = new String[]{
            Input.OPT_PHONE_NUMBER_FILE,
            mockPhoneNumberFileName,
            Input.OPT_DICTIONARY_FILE,
            mockDictionaryFileName
    };

    final static String[] readFromStdinCommand = new String[]{
            Input.OPT_DICTIONARY_FILE,
            mockDictionaryFileName
    };

    @Test
    public void testReadFromFile(){

        //Setup
        final Input input = new Input();
        final InputValidator inputValidator = new InputValidator();

        //Invoke
        final InputResult inputResult = input.readInput(readFromFilesCommand);

        //Verify
        assertTrue(inputValidator.isReadFromFile(inputResult));
        assertTrue(inputResult.getPhoneNumberFile() != null);
        assertTrue(inputResult.getPhoneNumberFile().equals(mockPhoneNumberFileName));
    }

    @Test
    public void testReadFromStdIn(){

        //Setup
        final Input input = new Input();
        final InputValidator inputValidator = new InputValidator();

        //Invoke
        final InputResult inputResult = input.readInput(readFromStdinCommand);

        //Verify
        assertTrue(!inputValidator.isReadFromFile(inputResult));
        assertTrue(inputResult.getPhoneNumberFile() == null);

    }

    @Test
    public void testDictionaryPresent(){

        //Setup
        final Input input = new Input();

        //Invoke
        final InputResult inputResult = input.readInput(readFromStdinCommand);

        //Verify
        assertTrue(inputResult.getDictionaryFile() != null);
        assertTrue(inputResult.getDictionaryFile().equals(mockDictionaryFileName));

    }



}
