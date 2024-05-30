package com.bank;

import org.bank.Bank;
import org.bank.clients.Client;
import org.bank.accounts.DebitAccount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    @Test
    public void testAddClient() {
        Bank bank = new Bank("Test Bank", 0.01, 10000);
        Client client = new Client("John", "Doe");
        bank.addClient(client);

        assertEquals(client, bank.getClientByName("John", "Doe"));
    }

    @Test
    public void testAddAccount() {
        Bank bank = new Bank("Test Bank", 0.01, 10000);
        Client client = new Client("John", "Doe");
        bank.addClient(client);
        DebitAccount account = new DebitAccount(client, "2023-01-01");

        bank.addAccount(account);
        assertEquals(account, bank.getAccountByClient(client));
    }

}

