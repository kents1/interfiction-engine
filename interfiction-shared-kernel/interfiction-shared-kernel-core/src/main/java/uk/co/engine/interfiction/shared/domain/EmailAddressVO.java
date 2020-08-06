package uk.co.engine.interfiction.shared.domain;

import java.util.Objects;

public class EmailAddressVO {

    private final String emailAddress;

    public EmailAddressVO(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddressVO that = (EmailAddressVO) o;
        return emailAddress.equals(that.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

    @Override
    public String toString() {
        return "EmailAddressVO{" +
                "emailAddress='" + emailAddress + '\'' +
                '}';
    }

}
