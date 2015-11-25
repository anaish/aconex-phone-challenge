package com.anaish.aconex.parser;

import com.anaish.aconex.dictionary.DictionaryWords;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrew on 11/23/2015.
 * Holds matched words
 * Processes numbers and matches them with words
 */
public class WordHolder {

    private final String processed;
    private final String unProcessed;

    private static final String EMPTY_STRING = "";
    private static final List<Character> IGNORED = Arrays.asList(new Character[]{'0','1'});

    public WordHolder(final String phoneNumber){
        this(EMPTY_STRING, phoneNumber);
    }

    protected WordHolder(final String processed, final String unProcessed) {
        this.processed = processed;
        this.unProcessed = unProcessed;
    }

    public List<String> matchPhoneNumbers(DictionaryWords dictionary) {
        final List<String> numbers = new LinkedList<>();
        if(hasUnProcessedChars()) {
            numbers.add(getProcessed());
        } else {
            process(dictionary)
                    .stream()
                    .forEach(matches -> numbers.addAll(matches.matchPhoneNumbers(dictionary)));
        }
        return numbers;
    }

    public List<WordHolder> process(final DictionaryWords dictionaryWords) {

        final List<WordHolder> wordMatches = new LinkedList<>();
        final String processed = getProcessed();
        final int processedSize = processed.length();
        final String unProcessedString = getUnProcessed();
        final String nextUnProcessedLetter = unProcessedString.substring(0, 1);
        final char nextUnprocessedChar = unProcessedString.charAt(0);
        final int maxWordLength = dictionaryWords.getMaxWordLength();

        if(isIgnorable(nextUnprocessedChar)) {
            wordMatches.add(WordHolderFactory.createWordHolder(this, nextUnProcessedLetter));
            return wordMatches;
        }

        if(processedSize == 0 || isSingleDigit(processed, processedSize)) {
            wordMatches.add(WordHolderFactory.createWordHolder(this, nextUnProcessedLetter));
        }

        for(int i = 1; isWithinWordBounds(unProcessedString, maxWordLength, i); i++) {
            final String unProcessed = unProcessedString.substring(0, i);
            dictionaryWords.getWordsForNumber(unProcessed)
                    .stream()
                    .forEach(word ->  wordMatches.add(WordHolderFactory.createWordHolder(this, word)));
        }

        return wordMatches;
    }

    private boolean isSingleDigit(final String processed, final int processedSize) {
        return processedSize > 0 && !Character.isDigit(processed.charAt(processedSize - 1));
    }

    private boolean isWithinWordBounds(String unProcessedString, int maxWordLength, int i) {
        return i <= maxWordLength && i <= unProcessedString.length();
    }

    private boolean hasUnProcessedChars(){
        return getUnProcessed().length() == 0;
    }

    private boolean isIgnorable(char unprocessedChar) {
        return IGNORED.stream().filter(chr -> chr == unprocessedChar).findFirst().isPresent();
    }

    private boolean partiallyProcessed() {
        return getProcessed().length() > 0 && getUnProcessed().length() > 0;
    }

    public String getProcessed() {
        return processed;
    }

    public String getUnProcessed() {
        return unProcessed;
    }

    @Override
    public String toString() {

        return partiallyProcessed() ? getProcessed() + "-" + getUnProcessed() :  getProcessed() + getUnProcessed();

    }

}

