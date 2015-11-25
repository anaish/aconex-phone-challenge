package com.anaish.aconex.dictionary;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andrew on 11/23/2015.
 * Parses a dictionary file
 */
public class DictionaryParser {

    public List<String> parseDictionary(final String dictionaryFile) throws IOException {

        return Files.readAllLines(Paths.get(dictionaryFile), Charset.forName("UTF-8"))
                .stream()
                .collect(Collectors.toList());

    }
}
