package com.anaish.aconex.command;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Andrew on 11/23/2015.
 */
public class CommandInputTest {

    final static String mockPhoneNumberFileName = ".\\src\\test\\resources\\mock-phone-number-file.txt";
    final static String mockDictionaryFileName = ".\\src\\test\\resources\\mock-dictionary-file.txt";

    final static String[] readFromFilesCommand = new String[]{
            CommandInput.OPT_PHONE_NUMBER_FILE,
            mockPhoneNumberFileName,
            CommandInput.OPT_DICTIONARY_FILE,
            mockDictionaryFileName
    };

    final static String[] readFromStdinCommand = new String[]{
            CommandInput.OPT_DICTIONARY_FILE,
            mockDictionaryFileName
    };

    @Test
    public void testReadFromFile(){

        //Setup
        final CommandInput commandInput = new CommandInput();

        //Invoke
        final CommandInputResult commandInputResult = commandInput.readInput(readFromFilesCommand);

        //Verify
        assertTrue(commandInputResult.isReadFromFile());
        assertTrue(commandInputResult.getPhoneNumberFile() != null);
        assertTrue(commandInputResult.getPhoneNumberFile().equals(mockPhoneNumberFileName));
    }

    @Test
    public void testReadFromStdIn(){

        //Setup
        final CommandInput commandInput = new CommandInput();

        //Invoke
        final CommandInputResult commandInputResult = commandInput.readInput(readFromStdinCommand);

        //Verify
        assertTrue(!commandInputResult.isReadFromFile());
        assertTrue(commandInputResult.getPhoneNumberFile() == null);

    }

    @Test
    public void testDictionaryPresent(){

        //Setup
        final CommandInput commandInput = new CommandInput();

        //Invoke
        final CommandInputResult commandInputResult = commandInput.readInput(readFromStdinCommand);

        //Verify
        assertTrue(commandInputResult.getDictionaryFile() != null);
        assertTrue(commandInputResult.getDictionaryFile().equals(mockDictionaryFileName));

    }



}
