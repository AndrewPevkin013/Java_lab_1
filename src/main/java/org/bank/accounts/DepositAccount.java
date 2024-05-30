package org.bank.accounts;

import org.bank.clients.Client;

import java.time.LocalDate;

public class DepositAccount extends Account {
    private LocalDate endDate;

    public DepositAccount(Client owner, String currentDate, LocalDate endDate) {
        super(owner, currentDate);
        this.endDate = endDate;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        LocalDate currentDate = LocalDate.parse(this.currentDate);
        if (currentDate.isBefore(endDate)) {
            throw new IllegalArgumentException("Cannot withdraw before deposit end date");
        }
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    @Override
    public void transfer(Account toAccount, double amount) {
        LocalDate currentDate = LocalDate.parse(this.currentDate);
        if (currentDate.isBefore(endDate)) {
            throw new IllegalArgumentException("Cannot transfer before deposit end date");
        }
        if (balance >= amount) {
            withdraw(amount);
            toAccount.deposit(amount);
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    @Override
    public void processMonthlyOperation(double interestRate) {
        double dailyInterest = interestRate / 365;
        balance += balance * dailyInterest * 30;
    }
}
