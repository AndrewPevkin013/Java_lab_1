package org.bank.accounts;

import org.bank.clients.Client;

public class CreditAccount extends Account {
    private double creditLimit;
    private double creditCommission;

    public CreditAccount(Client owner, String currentDate, double creditLimit, double creditCommission) {
        super(owner, currentDate);
        this.creditLimit = creditLimit;
        this.creditCommission = creditCommission;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount >= -creditLimit) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Exceeds credit limit");
        }
    }

    @Override
    public void transfer(Account toAccount, double amount) {
        if (balance - amount >= -creditLimit) {
            withdraw(amount);
            toAccount.deposit(amount);
        } else {
            throw new IllegalArgumentException("Exceeds credit limit");
        }
    }

    @Override
    public void processMonthlyOperation(double interestRate) {
        if (balance < 0) {
            balance -= creditCommission;
        }
    }
}
