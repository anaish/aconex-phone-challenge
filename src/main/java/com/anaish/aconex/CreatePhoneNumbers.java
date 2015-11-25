package com.anaish.aconex;

import com.anaish.aconex.command.CommandInput;
import com.anaish.aconex.command.CommandInputResult;
import com.anaish.aconex.dictionary.DictionaryParser;
import com.anaish.aconex.dictionary.DictionaryWords;
import com.anaish.aconex.parser.PhoneNumberParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrew on 11/25/2015.
 * Main input class
 */
public class CreatePhoneNumbers {

    public static final void main(String[] args){

        final CommandInput commandInput = new CommandInput();
        final CommandInputResult commandInputResult = commandInput.readInput(args);
        final String dictionaryFile = commandInputResult.getDictionaryFile();
        if(dictionaryFile == null){
            System.out.println("Usage: CreatePhoneNumbers [-p <phone number file>] <-d dictionary file>");
            System.exit(1);
        } else {
            processInput(commandInputResult, dictionaryFile);
        }


    }

    private static void processInput(CommandInputResult commandInputResult, String dictionaryFile) {
        if (commandInputResult.isReadFromFile()) {
            readFromFile(commandInputResult, dictionaryFile);
        } else {
            readFromStdIn(dictionaryFile);
        }
    }

    private static void readFromStdIn(String dictionaryFile) {
        try {
            readInput(System.in, dictionaryFile);
        } catch (IOException e) {
            System.out.println("Unable to read input");
            e.printStackTrace();
        }
    }

    private static void readFromFile(CommandInputResult commandInputResult, String dictionaryFile) {
        try {
            readInput(new FileInputStream(new File(commandInputResult.getPhoneNumberFile())), dictionaryFile);
        } catch (IOException e) {
            System.out.println("Unable to find file " + commandInputResult.getPhoneNumberFile());
            e.printStackTrace();
        }
    }

    private static void readInput(InputStream in, String dictionaryFile) throws IOException {

        final DictionaryParser dictionaryParser = new DictionaryParser();
        final List<String> dictionaryWordList = dictionaryParser.parseDictionary(dictionaryFile);
        final DictionaryWords dictionaryWords = new DictionaryWords(dictionaryWordList);
        final PhoneNumberParser phoneNumberParser = new PhoneNumberParser(dictionaryWords);
        final List<Set<String>> matches = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while((line = br.readLine()) != null) {
                Set<String> strings = phoneNumberParser.matchPhoneNumber(line);
                matches.add(strings);
            }
        }
        matches.stream().forEach(matchSet -> matchSet.stream().forEach(System.out::println));

    }
}
