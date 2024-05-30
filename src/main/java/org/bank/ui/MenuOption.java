package org.bank.ui;

import org.bank.*;
import org.bank.accounts.*;
import org.bank.clients.Client;
import org.bank.transactions.Transaction;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuOption {
    private static CentralBank centralBank = new CentralBank();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Register Bank");
            System.out.println("2. Create Client");
            System.out.println("3. Create Account");
            System.out.println("4. Execute Transaction");
            System.out.println("5. Rollback Transaction");
            System.out.println("6. Process Monthly Operations");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    registerBank(scanner);
                    break;
                case 2:
                    createClient(scanner);
                    break;
                case 3:
                    createAccount(scanner);
                    break;
                case 4:
                    executeTransaction(scanner);
                    break;
                case 5:
                    rollbackTransaction(scanner);
                    break;
                case 6:
                    processMonthlyOperations();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void registerBank(Scanner scanner) {
        System.out.print("Enter bank name: ");
        String bankName = scanner.nextLine();
        System.out.print("Enter interest rate on balance: ");
        double interestRate = scanner.nextDouble();
        System.out.print("Enter suspicious transaction limit: ");
        double limit = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Bank bank = new Bank(bankName, interestRate, limit);
        centralBank.registerBank(bank);
        System.out.println("Bank registered successfully!");
    }

    private static void createClient(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter address (optional): ");
        String address = scanner.nextLine();
        System.out.print("Enter passport number (optional): ");
        String passportNumber = scanner.nextLine();

        Client client;
        if (!address.isEmpty() && !passportNumber.isEmpty()) {
            client = new Client(firstName, lastName, address, passportNumber);
        } else {
            client = new Client(firstName, lastName);
        }

        // Assuming bank is selected somehow
        // Bank bank = centralBank.getBankByName(bankName);
        // bank.addClient(client);

        System.out.println("Client created successfully!");
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter bank name: ");
        String bankName = scanner.nextLine();

        Bank bank = findBankByName(bankName);
        if (bank == null) {
            System.out.println("Bank not found!");
            return;
        }

        System.out.print("Enter client first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter client last name: ");
        String lastName = scanner.nextLine();

        Client client = bank.getClientByName(firstName, lastName);
        if (client == null) {
            System.out.println("Client not found!");
            return;
        }

        System.out.println("Select account type:");
        System.out.println("1. Debit Account");
        System.out.println("2. Deposit Account");
        System.out.println("3. Credit Account");
        int accountType = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Account account;
        switch (accountType) {
            case 1:
                account = new DebitAccount(client, LocalDate.now().toString());
                break;
            case 2:
                System.out.print("Enter deposit end date (yyyy-MM-dd): ");
                String endDateStr = scanner.nextLine();
                LocalDate endDate = LocalDate.parse(endDateStr);
                account = new DepositAccount(client, LocalDate.now().toString(), endDate);
                break;
            case 3:
                System.out.print("Enter credit limit: ");
                double creditLimit = scanner.nextDouble();
                System.out.print("Enter credit commission: ");
                double creditCommission = scanner.nextDouble();
                account = new CreditAccount(client, LocalDate.now().toString(), creditLimit, creditCommission);
                break;
            default:
                System.out.println("Invalid account type selected!");
                return;
        }

        account.deposit(initialDeposit);
        bank.addAccount(account);
        System.out.println("Account created successfully!");
    }

    private static Bank findBankByName(String bankName) {
        for (Bank bank : centralBank.getBankList()) {
            if (bank.getBankName().equals(bankName)) {
                return bank;
            }
        }
        return null;
    }

    private static void executeTransaction(Scanner scanner) {
        System.out.print("Enter sender bank name: ");
        String senderBankName = scanner.nextLine();

        Bank senderBank = findBankByName(senderBankName);
        if (senderBank == null) {
            System.out.println("Sender bank not found!");
            return;
        }

        System.out.print("Enter sender first name: ");
        String senderFirstName = scanner.nextLine();
        System.out.print("Enter sender last name: ");
        String senderLastName = scanner.nextLine();

        Client sender = senderBank.getClientByName(senderFirstName, senderLastName);
        if (sender == null) {
            System.out.println("Sender client not found!");
            return;
        }

        Account senderAccount = senderBank.getAccountByClient(sender);

        System.out.print("Enter receiver bank name: ");
        String receiverBankName = scanner.nextLine();

        Bank receiverBank = findBankByName(receiverBankName);
        if (receiverBank == null) {
            System.out.println("Receiver bank not found!");
            return;
        }

        System.out.print("Enter receiver first name: ");
        String receiverFirstName = scanner.nextLine();
        System.out.print("Enter receiver last name: ");
        String receiverLastName = scanner.nextLine();

        Client receiver = receiverBank.getClientByName(receiverFirstName, receiverLastName);
        if (receiver == null) {
            System.out.println("Receiver client not found!");
            return;
        }

        Account receiverAccount = receiverBank.getAccountByClient(receiver);

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Transaction transaction = new Transaction(senderAccount, receiverAccount, amount);
        centralBank.executeTransaction(transaction);

        System.out.println("Transaction executed successfully!");
    }


    private static void rollbackTransaction(Scanner scanner) {
        System.out.print("Enter transaction ID to rollback: ");
        int transactionId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        centralBank.rollbackTransaction(transactionId);
        System.out.println("Transaction rolled back successfully!");
    }


    private static void processMonthlyOperations() {
        centralBank.notifyBanks();
        System.out.println("Monthly operations processed for all banks!");
    }
}
