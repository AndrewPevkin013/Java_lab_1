package org.bank;

import org.bank.transactions.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CentralBank {
    private List<Bank> bankList;
    private Map<Integer, Transaction> transactions;
    private int transactionCounter;

    public CentralBank() {
        this.bankList = new ArrayList<>();
        this.transactions = new HashMap<>();
        this.transactionCounter = 0;
    }

    public void registerBank(Bank bank) {
        bankList.add(bank);
    }

    public void executeTransaction(Transaction transaction) {
        transaction.execute();
        transactions.put(transactionCounter++, transaction);
    }

    public void rollbackTransaction(int transactionId) {
        Transaction transaction = transactions.get(transactionId);
        if (transaction != null && !transaction.isRolledBack()) {
            transaction.rollback();
            transaction.markRolledBack();
        }
    }

    public void notifyBanks() {
        for (Bank bank : bankList) {
            bank.processMonthlyOperations();
        }
    }

    public List<Bank> getBankList() {
        return bankList;
    }

    public Bank findBankByName(String bankName) {
        for (Bank bank : bankList) {
            if (bank.getBankName().equals(bankName)) {
                return bank;
            }
        }
        return null;
    }

}
