package com.anaish.aconex.command;


import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Andrew on 11/23/2015.
 * Responsible for processing commands and returning results
 */
public class CommandInput {

    public static String OPT_PHONE_NUMBER_FILE = "-p";
    public static String OPT_DICTIONARY_FILE = "-d";

    public CommandInputResult readInput(String[] commands){

        final CommandInputResult commandInputResult = new CommandInputResult();

        getCommandOption(commands , OPT_PHONE_NUMBER_FILE).ifPresent(commandInputResult::setPhoneNumberFile);
        getCommandOption(commands , OPT_DICTIONARY_FILE).ifPresent(commandInputResult::setDictionaryFile);

        return commandInputResult;

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
