package uk.co.engine.interfiction.test.functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationIT extends UIFunctionalTest {

    @Test
    @DisplayName("Can register with valid details")
    public void shouldRegisterValidUser() {

        // Navigate to the registration page
        toPage("/register");

        waitForTitle("Register");
        assertEquals("Register", driver().getTitle());

        // Fill out the form
        input("email-address", "bruce.lee@jeetkune.do");
        input("first-name", "Bruce");
        input("last-name", "Lee");
        input("frm-password", "L!nda");
        input("frm-confirm-password", "L!nda");
    }

}
