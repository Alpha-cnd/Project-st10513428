
package com.mycompany.regandlog;


import co.abit.api.identity.domain.User;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegAndLog {


    
    // Storage for registered users
    private static ArrayList<User> users = new ArrayList<>();
    
    // Regular expression patterns
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{1,5}$";
    private static final Pattern USERNAME_REGEX = Pattern.compile(USERNAME_PATTERN);
    
    // Cell phone pattern: + or 0 followed by country code and up to 10 digits
    // South Africa uses +27 or 0, pattern allows any international code
    private static final String PHONE_PATTERN = "^\\+\\d{1,4}\\d{1,10}$";
    private static final Pattern PHONE_REGEX = Pattern.compile(PHONE_PATTERN);
    
    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            String[] options = {"Register", "Login", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, 
                "Welcome to MONEYSITAMOL", 
                "Main Menu",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
            
            switch (choice) {
                case 0 -> // Register
                    registerUser();
                case 1 -> // Login
                    loginUser();
                case 2, -1 -> // Exit
                {
                    // Cancel
                    running = false;
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                }
            }
            // Exit
                    }
    }
    
    // ==================== REGISTRATION ====================
    
    public static void registerUser() {
        JOptionPane.showMessageDialog(null, "=== CREATE NEW ACCOUNT ===");
        
        // Get and validate username
        String username = JOptionPane.showInputDialog("Enter username:(Must contain underscore, max 5 chars)");
        if (username == null) return; // User cancelled
        
        String usernameCheck = checkUserName(username);
        JOptionPane.showMessageDialog(null, usernameCheck);
        
        if (!usernameCheck.contains("successfully")) {
            return; // Invalid username, stop registration
        }
        
        // Get and validate password
        String password = JOptionPane.showInputDialog("Enter password:(Min 8 chars, uppercase, number, special char)");
        if (password == null) return;
        
        String passwordCheck = checkPasswordComplexity(password);
        JOptionPane.showMessageDialog(null, passwordCheck);
        
        if (!passwordCheck.contains("successfully")) {
            return; // Invalid password, stop registration
        }
        
        // Get and validate cell phone number
        String phone = JOptionPane.showInputDialog("Enter South African cell phone number:(Format: (0) followed by number, max 10 digits total)");
        if (phone == null) return;
        
        String phoneCheck = checkCellPhoneNumber(phone);
        JOptionPane.showMessageDialog(null, phoneCheck);
        
        if (!phoneCheck.contains("successfully")) {
            return; // Invalid phone, stop registration
        }
        
        // All validations passed - create user
        
    
        JOptionPane.showMessageDialog(null, 
            " REGISTRATION SUCCESSFUL!" +
            "Username: " + username + "" +
            "Cell: " + phone + "" +
            "You can now login with your credentials.");
    }
    
    // ==================== VALIDATION METHODS ====================
    
    public static String checkUserName(String username) {
        // Check 1: Contains underscore
        boolean hasUnderscore = username.contains("_");
        
        // Check 2: Length not more than 5 characters
        boolean validLength = username.length() <= 5;
        
        if (hasUnderscore && validLength) {
            return "Username successfully captured.";
        } else {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
    }
    
    public static String checkPasswordComplexity(String password) {
        // Check all requirements
        boolean minLength = password.length() >= 8;
        boolean hasUpperCase = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpperCase = true;
            if (Character.isDigit(c)) hasNumber = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        
        if (minLength && hasUpperCase && hasNumber && hasSpecial) {
            return "Password successfully captured.";
        } else {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
    }
    
    public static String checkCellPhoneNumber(String phoneNumber) {
        // Remove spaces for validation
        String cleanPhone = phoneNumber.replaceAll("","");
        
        // Check: Starts with +27 or 0 (international code) and max 10 digits after country code
        // Pattern: +[country code][number] where total digits after  + > 10
        if (!cleanPhone.startsWith("0")) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        // Extract digits only (after the 0)
        String digitsOnly = cleanPhone.substring(1); 
        
     
          
        // Check total length: country code (1-3 digits typically) + number (max 10 digits)
        // So max total digits after + should be around , but assignment says "no more than 10 characters long"
        // Interpreting as: the number part (after country code) should be max 10 digits
        
        if (digitsOnly.length() > 10) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        return "Cell phone number successfully added.";
    }
    
    // ==================== LOGIN ====================
    
    public static void loginUser() {
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "No registered users found.Please register first.");
            return;
        }
        
        JOptionPane.showMessageDialog(null, "=== LOGIN ===");
        
        String username = JOptionPane.showInputDialog("Enter username:");
        if (username == null) return;
        
        String password = JOptionPane.showInputDialog("Enter password:");
        if (password == null) return;
        
        boolean found = false;
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                found = true;
                String phoneNumber = null;
                JOptionPane.showMessageDialog(null, 
                    "LOGIN SUCCESSFUL!" +
                    "Welcome back, " + username + "!" +
                    "Your registered cell: " + phoneNumber);
                break;
            }
        }
        
        if (!found) {
            JOptionPane.showMessageDialog(null, 
                " LOGIN FAILED" +
                "Invalid username or password." +
                "Please try again or register a new account.");
        }
    }
    
    // ==================== RETURN LOGIN STATUS ====================
    
    public static boolean returnLoginStatus(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    // Getters for testing
    public static ArrayList<User> getUsers() {
        return users;
    }
}
    

