package com.example.myapplication;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class LogIn_Test {
    @Test
    public void multiplicationOfZeroIntegersShouldReturnZero() {
        MainActivity tester = new MainActivity(); // MyClass is tested
        // assert statements
       assertFalse("enter details", tester.ValidDetails(0,0));
        assertFalse("enter email", tester.ValidDetails(0,6));
        assertFalse("enter passward", tester.ValidDetails(1,0));
        assertTrue("", tester.ValidDetails(10,5));
    }
}
