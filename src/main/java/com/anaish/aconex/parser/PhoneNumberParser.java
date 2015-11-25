package com.anaish.aconex.parser;

import com.anaish.aconex.dictionary.DictionaryWords;
import com.anaish.aconex.parser.validator.PhoneNumberValidator;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Andrew on 11/23/2015.
 */
public class PhoneNumberParser {

    private final DictionaryWords dictionaryWords;


    public PhoneNumberParser(DictionaryWords dictionaryWords){
        this.dictionaryWords = dictionaryWords;
    }

    public List<String> parsePhoneNumbers(final String phoneNumberFile) throws IOException {

        final PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        return Files.readAllLines(Paths.get(phoneNumberFile), Charset.forName("UTF-8"))
                .stream()
                .filter(phoneNumberValidator::isValid)
                .collect(Collectors.toList());

    }


    public Set<String> matchPhoneNumber(String phoneNumber) {

        final String digitsOnly = phoneNumber.replaceAll("[^0-9]", "").trim();
        if(digitsOnly.length()==0){
            return Collections.emptySet();
        }
        return WordHolderFactory.createWordHolder(digitsOnly)
                .matchPhoneNumbers(dictionaryWords)
                .stream()
                .filter(word -> word.matches(".*[a-zA-Z]+.*"))
                .map(String::toUpperCase)
                .collect(Collectors.toSet());


    }





}
