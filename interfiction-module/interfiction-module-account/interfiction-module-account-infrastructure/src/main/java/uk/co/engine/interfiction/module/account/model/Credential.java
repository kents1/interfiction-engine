package uk.co.engine.interfiction.module.account.model;

public record Credential(String emailAddress, String password) {

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

}
