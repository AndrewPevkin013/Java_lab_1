package org.bank.transactions;

import org.bank.accounts.Account;

public class Transaction {
    private Account fromAccount;
    private Account toAccount;
    private double amount;
    private boolean rolledBack;

    public Transaction(Account fromAccount, Account toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.rolledBack = false;
    }

    public void execute() {
        fromAccount.transfer(toAccount, amount);
    }

    public void rollback() {
        toAccount.transfer(fromAccount, amount);
        rolledBack = true;
    }

    public boolean isRolledBack() {
        return rolledBack;
    }

    public void markRolledBack() {
        this.rolledBack = true;
    }
}
