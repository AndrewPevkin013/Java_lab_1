package org.bank.accounts;

import org.bank.clients.Client;

public abstract class Account {
    protected double balance;
    protected String currentDate;
    protected Client owner;

    public Account(Client owner, String currentDate) {
        this.owner = owner;
        this.currentDate = currentDate;
        this.balance = 0;
    }

    public Client getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract void transfer(Account toAccount, double amount);

    public abstract void processMonthlyOperation(double interestRate);
}
