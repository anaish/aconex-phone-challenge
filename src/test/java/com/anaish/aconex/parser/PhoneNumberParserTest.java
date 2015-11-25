package com.anaish.aconex.parser;

import com.anaish.aconex.dictionary.DictionaryParser;
import com.anaish.aconex.dictionary.DictionaryWords;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertTrue;

/**
 * Created by Andrew on 11/23/2015.
 */
public class PhoneNumberParserTest {

    final static String mockPhoneNumberFileName = ".\\src\\test\\resources\\mock-phone-number-file.txt";
    final static String mockSmallDictionaryFileName = ".\\src\\test\\resources\\mock-dictionary-file-small.txt";
    public static final String VALID_PHONE_NUMBER = "2255.63";
    public static final String VALID_PHONE_NUMBER_SINGLE_DIGIT = "02255.63";
    public static final String INVALID_PHONE_NUMBER_DOUBLE_DIGIT = "00";
    public static final String VALID_PHONE_NUMBER_NO_MATCH = "12345";
    public static final String VALID_PHONE_NUMBER_PUNCTUATION = "2255.!\",!()*^%$#@63";
    public static final String EXPECTED_RESULT_CALL_ME = "CALL-ME";
    public static final String UNEXPECTED_RESULT_CALL_ME_LOWER_CASE = "call-me";
    public static final String EXPECTED_RESULT_CALL_ME_SINGLE_DIGIT = "0-CALL-ME";

    private PhoneNumberParser createParser(String dictionaryFileName) throws IOException {
            final DictionaryParser dictionaryParser = new DictionaryParser();
            final List<String> dictionaryWordList = dictionaryParser.parseDictionary(dictionaryFileName);
            final DictionaryWords dictionaryWords = new DictionaryWords(dictionaryWordList);
            final PhoneNumberParser phoneNumberParser = new PhoneNumberParser(dictionaryWords);
            return phoneNumberParser;
    }

    @Test
    public void testParseInput(){

        try {
            //Setup
            final PhoneNumberParser phoneNumberParser = createParser(mockSmallDictionaryFileName);

            //invoke
            final List<String> numbers = phoneNumberParser.parsePhoneNumbers(mockPhoneNumberFileName);

            //verify
            assertTrue(!numbers.isEmpty());
        } catch (IOException e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void testParseEveryDigitMatch(){

        try {
            //Setup
            final PhoneNumberParser phoneNumberParser = createParser(mockSmallDictionaryFileName);

            //invoke
            final Set<String> match = phoneNumberParser.matchPhoneNumber(VALID_PHONE_NUMBER);

            //verify
            assertTrue(!match.isEmpty());
            assertTrue(match.contains(EXPECTED_RESULT_CALL_ME));

        } catch (IOException e) {
            fail(e.getMessage());
        }

    }
    @Test
    public void testSingleDigitUnmatched(){
        try {
            //Setup
            final PhoneNumberParser phoneNumberParser = createParser(mockSmallDictionaryFileName);

            //invoke
            final Set<String> match = phoneNumberParser.matchPhoneNumber(VALID_PHONE_NUMBER_SINGLE_DIGIT);

            //verify
            assertTrue(!match.isEmpty());
            assertTrue(match.contains(EXPECTED_RESULT_CALL_ME_SINGLE_DIGIT));

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testNoTwoDigitsUnmatched(){
        try {
            //Setup
            final PhoneNumberParser phoneNumberParser = createParser(mockSmallDictionaryFileName);

            //invoke
            final Set<String> match = phoneNumberParser.matchPhoneNumber(INVALID_PHONE_NUMBER_DOUBLE_DIGIT);

            //verify
            assertTrue(match.isEmpty());

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testUnmatchedPhoneNumber(){
        try {
            //Setup
            final PhoneNumberParser phoneNumberParser = createParser(mockSmallDictionaryFileName);

            //invoke
            final Set<String> match = phoneNumberParser.matchPhoneNumber(VALID_PHONE_NUMBER_NO_MATCH);

            //verify
            assertTrue(match.isEmpty());

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testPunctuationIgnored(){
        try {
            //Setup
            final PhoneNumberParser phoneNumberParser = createParser(mockSmallDictionaryFileName);

            //invoke
            final Set<String> match = phoneNumberParser.matchPhoneNumber(VALID_PHONE_NUMBER_PUNCTUATION);

            //verify
            assertTrue(!match.isEmpty());
            assertTrue(match.contains(EXPECTED_RESULT_CALL_ME));

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
    @Test
    public void testCaseInsensitive(){

        try {
            //Setup
            final PhoneNumberParser phoneNumberParser = createParser(mockSmallDictionaryFileName);

            //invoke
            final Set<String> match = phoneNumberParser.matchPhoneNumber(VALID_PHONE_NUMBER_PUNCTUATION);

            //verify
            assertTrue(!match.isEmpty());
            assertTrue(!match.contains(UNEXPECTED_RESULT_CALL_ME_LOWER_CASE));

        } catch (IOException e) {
            fail(e.getMessage());
        }


    }
    @Test
    public void testOutputFormat(){
        try {
            //Setup
            final PhoneNumberParser phoneNumberParser = createParser(mockSmallDictionaryFileName);

            //invoke
            final Set<String> match = phoneNumberParser.matchPhoneNumber(VALID_PHONE_NUMBER);

            //verify
            assertTrue(!match.isEmpty());
            assertTrue(match.stream().filter(word -> word.charAt(4) == '-').findFirst().isPresent());

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }



}
