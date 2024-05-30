package com.bank;

import org.bank.CentralBank;
import org.bank.Bank;
import org.bank.clients.Client;
import org.bank.accounts.DebitAccount;
import org.bank.transactions.Transaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {
    @Test
    public void testRegisterBank() {
        CentralBank centralBank = new CentralBank();
        Bank bank = new Bank("Test Bank", 0.01, 10000);

        centralBank.registerBank(bank);
        assertTrue(centralBank.getBankList().contains(bank));
    }

    @Test
    public void testExecuteTransaction() {
        CentralBank centralBank = new CentralBank();
        Bank senderBank = new Bank("Sender Bank", 0.01, 10000);
        Bank receiverBank = new Bank("Receiver Bank", 0.01, 10000);
        centralBank.registerBank(senderBank);
        centralBank.registerBank(receiverBank);

        Client sender = new Client("John", "Doe");
        Client receiver = new Client("Jane", "Doe");
        senderBank.addClient(sender);
        receiverBank.addClient(receiver);

        DebitAccount senderAccount = new DebitAccount(sender, "2023-01-01");
        DebitAccount receiverAccount = new DebitAccount(receiver, "2023-01-01");
        senderAccount.deposit(1000);
        senderBank.addAccount(senderAccount);
        receiverBank.addAccount(receiverAccount);

        Transaction transaction = new Transaction(senderAccount, receiverAccount, 500);
        centralBank.executeTransaction(transaction);

        assertEquals(500, senderAccount.getBalance());
        assertEquals(500, receiverAccount.getBalance());
    }

    @Test
    public void testRollbackTransaction() {
        CentralBank centralBank = new CentralBank();
        Bank senderBank = new Bank("Sender Bank", 0.01, 10000);
        Bank receiverBank = new Bank("Receiver Bank", 0.01, 10000);
        centralBank.registerBank(senderBank);
        centralBank.registerBank(receiverBank);

        Client sender = new Client("John", "Doe");
        Client receiver = new Client("Jane", "Doe");
        senderBank.addClient(sender);
        receiverBank.addClient(receiver);

        DebitAccount senderAccount = new DebitAccount(sender, "2023-01-01");
        DebitAccount receiverAccount = new DebitAccount(receiver, "2023-01-01");
        senderAccount.deposit(1000);
        senderBank.addAccount(senderAccount);
        receiverBank.addAccount(receiverAccount);

        Transaction transaction = new Transaction(senderAccount, receiverAccount, 500);
        centralBank.executeTransaction(transaction);

        assertEquals(500, senderAccount.getBalance());
        assertEquals(500, receiverAccount.getBalance());
    }
}

