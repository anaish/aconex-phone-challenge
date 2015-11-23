package com.anaish.aconex.input;


import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Andrew on 11/23/2015.
 */
public class Input {

    public static String OPT_PHONE_NUMBER_FILE = "-p";
    public static String OPT_DICTIONARY_FILE = "-d";

    public InputResult readInput(String[] commands){

        final InputResult inputResult = new InputResult();

        getCommandOption(commands , OPT_PHONE_NUMBER_FILE).ifPresent(inputResult::setPhoneNumberFile);
        getCommandOption(commands , OPT_DICTIONARY_FILE).ifPresent(inputResult::setDictionaryFile);

        return inputResult;

    }

    private Optional<String> getCommandOption(final String[] commands, final String option) {

        final Optional<String> commandOption;
        final int paramIndex = Arrays.asList(commands).indexOf(option);

        if(paramIndex >= 0 && paramIndex < commands.length - 1){
            commandOption = Optional.of(commands[paramIndex + 1]);
        } else{
            commandOption = Optional.empty();
        }

        return commandOption;

    }

}
