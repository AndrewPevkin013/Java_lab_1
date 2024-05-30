package org.bank.accounts;

import org.bank.clients.Client;

public class DebitAccount extends Account {
    private static final double DAILY_INTEREST_DIVISOR = 365;

    public DebitAccount(Client owner, String currentDate) {
        super(owner, currentDate);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    @Override
    public void transfer(Account toAccount, double amount) {
        if (balance >= amount) {
            withdraw(amount);
            toAccount.deposit(amount);
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    @Override
    public void processMonthlyOperation(double interestRate) {
        double dailyInterest = interestRate / DAILY_INTEREST_DIVISOR;
        balance += balance * dailyInterest * 30;
    }
}
