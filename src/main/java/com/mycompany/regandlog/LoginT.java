
package com.mycompany.regandlog;


import org.junit.Test;
import static org.junit.Assert.*;

public class L
package com.myoginT {
    




    
    
    public void testValidUsername() {
        assertTrue(ValidationUtils.isValidUsername("ab_cd"));
        assertTrue(ValidationUtils.isValidUsername("a_b"));
        assertFalse(ValidationUtils.isValidUsername("abcdef")); // too long
        assertFalse(ValidationUtils.isValidUsername("abc")); // no underscore
        assertFalse(ValidationUtils.isValidUsername("abcde_")); // 6 chars
    }
    
  
    public void testValidPassword() {
        assertTrue(ValidationUtils.isValidPassword("Pass123!"));
        assertTrue(ValidationUtils.isValidPassword("Hello1@World"));
        assertFalse(ValidationUtils.isValidPassword("pass123!")); // no uppercase
        assertFalse(ValidationUtils.isValidPassword("Password!")); // no number
        assertFalse(ValidationUtils.isValidPassword("Password1")); // no special
        assertFalse(ValidationUtils.isValidPassword("Pass1!")); // too short
    }
    
    
    public void testValidCellPhone() {
        assertTrue(ValidationUtils.isValidCellPhone("+27123456789"));
        assertTrue(ValidationUtils.isValidCellPhone("+2701234567890")); // 10 digits after +27
        assertFalse(ValidationUtils.isValidCellPhone("27123456789")); // no +
        assertFalse(ValidationUtils.isValidCellPhone("+27012345678901")); // 11 digits
        assertFalse(ValidationUtils.isValidCellPhone("+44123456789")); // wrong country code
    }
    
    
    public void testLoginSystem() {
        LoginSystem system = new LoginSystem();
        
        // Test registration
        assertTrue(system.register("jd_doe", "Pass123!", "+27831234567"));
        
        // Test duplicate registration
        assertFalse(system.register("jd_doe", "Pass123!", "+27831234567"));
        
        // Test login
        assertTrue(system.login("jd_doe", "Pass123!"));
        assertFalse(system.login("jd_doe", "WrongPass1!"));
        assertFalse(system.login("unknown", "Pass123!"));
    }
}

