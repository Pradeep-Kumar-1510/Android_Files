package com.example.firstapp.activity;

import org.junit.Test;
import static org.junit.Assert.*;

public class UnitTestActivityTest {

    @Test
    public void testEmailValidation() {
        assertTrue(UnitTestActivity.isEmailValid("example@example.com"));
        assertFalse(UnitTestActivity.isEmailValid("example@example"));
        assertFalse(UnitTestActivity.isEmailValid("example.com"));
    }

    @Test
    public void testUsernameValidation() {
        assertTrue(UnitTestActivity.isUsernameValid("user123"));
        assertFalse(UnitTestActivity.isUsernameValid("us"));
        assertFalse(UnitTestActivity.isUsernameValid("user!"));
    }


    @Test
    public void testPhoneNumberValidation() {
        assertTrue(UnitTestActivity.isPhoneNumberValid("1234567890"));
        assertFalse(UnitTestActivity.isPhoneNumberValid("123"));
        assertFalse(UnitTestActivity.isPhoneNumberValid("12345678901"));
    }

    @Test
    public void testUrlValidation() {
        assertTrue(UnitTestActivity.isUrlValid("https://www.example.com"));
        assertFalse(UnitTestActivity.isUrlValid("example.com"));
    }
}


