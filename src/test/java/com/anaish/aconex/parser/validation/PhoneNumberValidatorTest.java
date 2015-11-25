package com.anaish.aconex.parser.validation;

import com.anaish.aconex.parser.validator.PhoneNumberValidator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


/**
 * Created by Andrew on 11/23/2015.
 */
public class PhoneNumberValidatorTest {

    @Test
    public void testValidateSingleLinePerPhoneNumber(){

        //Setup
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        //Invoke
        boolean valid = phoneNumberValidator.isValid("180012467    180001200");
        //Verify
        assertTrue(!valid);

    }
}
