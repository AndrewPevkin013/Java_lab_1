package org.bank.clients;

public class Client {
    private final String firstName;
    private final String lastName;
    private String address;
    private String passportNumber;
    private boolean isSuspicious;

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isSuspicious = true;
    }

    public Client(String firstName, String lastName, String address, String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.passportNumber = passportNumber;
        this.isSuspicious = false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public boolean isSuspicious() {
        return isSuspicious;
    }

    public void setAddress(String address) {
        this.address = address;
        checkSuspiciousStatus();
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        checkSuspiciousStatus();
    }

    private void checkSuspiciousStatus() {
        if (this.address != null && this.passportNumber != null) {
            this.isSuspicious = false;
        }
    }
}
