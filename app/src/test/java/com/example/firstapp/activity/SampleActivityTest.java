package com.example.firstapp.activity;

import org.junit.Test;
import static org.junit.Assert.*;

public class SampleActivityTest {

    @Test
    public void testEmailValidation() {
        assertTrue(SampleActivity.isEmailValid("example@example.com"));
        assertFalse(SampleActivity.isEmailValid("example@example"));
        assertFalse(SampleActivity.isEmailValid("example.com"));
    }

    @Test
    public void testUsernameValidation() {
        assertTrue(SampleActivity.isUsernameValid("user123"));
        assertFalse(SampleActivity.isUsernameValid("us"));
        assertFalse(SampleActivity.isUsernameValid("user!"));
    }


    @Test
    public void testPhoneNumberValidation() {
        assertTrue(SampleActivity.isPhoneNumberValid("1234567890"));
        assertFalse(SampleActivity.isPhoneNumberValid("123"));
        assertFalse(SampleActivity.isPhoneNumberValid("12345678901"));
    }

    @Test
    public void testUrlValidation() {
        assertTrue(SampleActivity.isUrlValid("https://www.example.com"));
        assertFalse(SampleActivity.isUrlValid("example.com"));
    }
}


