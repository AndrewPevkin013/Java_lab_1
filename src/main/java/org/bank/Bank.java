package org.bank;

import org.bank.accounts.Account;
import org.bank.clients.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank {
    private List<Client> clientList;
    private List<Account> accountList;
    private final double interestOnBalance;
    private double limit;
    private String bankName;

    public Bank(String bankName, double interestOnBalance, double limit) {
        this.clientList = new ArrayList<>();
        this.accountList = new ArrayList<>();
        this.bankName = bankName;
        this.interestOnBalance = interestOnBalance;
        this.limit = limit;
    }

    public String getBankName(){
        return bankName;
    }

    public void addClient(Client client) {
        for (Client existingClient : clientList) {
            if (Objects.equals(existingClient.getFirstName(), client.getFirstName()) &&
                    Objects.equals(existingClient.getLastName(), client.getLastName())) {
                return;
            }
        }
        this.clientList.add(client);
    }

    public Client getClientByName(String firstName, String lastName) {
        for (Client client : clientList) {
            if (Objects.equals(client.getFirstName(), firstName) &&
                    Objects.equals(client.getLastName(), lastName)) {
                return client;
            }
        }
        throw new NullPointerException("No such client!");
    }

    public Account getAccountByClient(Client client) {
        for (Account account : accountList) {
            if (Objects.equals(account.getOwner(), client)) {
                return account;
            }
        }
        throw new NullPointerException("No account for this client!");
    }

    public double getInterestOnBalance() {
        return interestOnBalance;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public void processMonthlyOperations() {
        for (Account account : accountList) {
            account.processMonthlyOperation(interestOnBalance);
        }
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }
}
