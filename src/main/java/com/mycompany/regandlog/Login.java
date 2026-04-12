
package com.mycompany.regandlog;


public class Login {
    


    private final String username;
    private final String password;
    private String cellPhone;
    
    public Login(String username, String password, String cellPhone) {
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
    }
    
    // Getters
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getCellPhone() {
        return cellPhone;
    }
    
    // Setters (if needed)
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
    
    @Override
    public String toString() {
        return "User{username='" + username + "', cellPhone='" + cellPhone + "'}";
    }
}

