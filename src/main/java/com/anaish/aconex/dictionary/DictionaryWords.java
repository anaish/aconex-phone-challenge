package com.anaish.aconex.dictionary;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by Andrew on 11/23/2015.
 */
public class DictionaryWords {

    private final List<String> words;
    private int wordCount = 0;
    private int maxWordLength = 0;

    private Map<String, Set<String>> wordMap = new HashMap<>();

    public static final char[] CHAR_MAP = {
            '-', '-', '-', '-', '-',
            '-', '-', '-', '-', '-',
            '-', '-', '-', '-', '-',
            '-', '-', '-', '-', '-',
            '-', '-', '-', '-', '-',
            '-', '-', '-', '-', '-',
            '-', '-', '-', '-', '-',
            '-', '-', '-', '-', '-',
            '-', '-', '-', '-', '-',
            '-', '-', '-', '-', '-',
            '-', '-', '-', '-', '-',
            '-', '-', '-', '-', '-',
            '-', '-', '-', '-', '-',
            '2', '2', '2', '3', '3',
            '3', '4', '4', '4', '5',
            '5', '5', '6', '6', '6',
            '7', '7', '7', '7', '8',
            '8', '8', '9', '9', '9',
            '9'
    };


    public DictionaryWords(final List<String> listOfWords){
        this.words = listOfWords;
        this.wordCount = listOfWords.size();
        this.addAllWords();
    }

    private void addAllWords(){

        this.words.stream().forEach(this::addWord);

    }

    private void addWord(String word) {
        final String number = wordToNumber(word);
        Set<String> list = wordMap.get(number);
        if(list == null) {
            list = new HashSet<>();
            wordMap.put(number, list);
        }
        list.add(word);
        if(word.length() > this.maxWordLength){
            this.maxWordLength = word.length();
        }
    }

    private String wordToNumber(String word) {
        String str = word.toUpperCase();
        final StringBuilder stringBuilder = new StringBuilder(str);
        final char[] chars = str.toCharArray();
        IntStream.range(0, chars.length)
                .forEach(index -> stringBuilder.setCharAt(index,CHAR_MAP[chars[index]]));
        return stringBuilder.toString();
    }

    public int getMaxWordLength(){
        return this.maxWordLength;
    }

    public Set<String> getWordsForNumber(String number) {
        final Set<String> words = wordMap.get(number);
        if(words == null) {
            return Collections.emptySet();
        }
        return words;
    }
}
