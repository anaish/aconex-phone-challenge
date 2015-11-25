package com.anaish.aconex.parser;

/**
 * Created by Andrew on 11/25/2015.
 */
public class WordHolderFactory {

    public static WordHolder createWordHolder(final WordHolder wordHolder, final String alreadyProcessed){
        final String processed = (hasProcessedChars(wordHolder) ? wordHolder.getProcessed() + "-" : "") + alreadyProcessed;
        final String unProcessed = wordHolder.getUnProcessed().substring(alreadyProcessed.length());
        return new WordHolder(processed, unProcessed);
    }

    public static WordHolder createWordHolder(final String phoneNumber){
        return new WordHolder(phoneNumber);
    }

    private static boolean hasProcessedChars(final WordHolder wordHolder) {
        return wordHolder.getProcessed().length() != 0;
    }

}
