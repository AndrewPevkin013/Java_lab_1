package com.bank;

import org.bank.clients.Client;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test
    public void testClientCreationWithFullDetails() {
        Client client = new Client("John", "Doe", "123 Main St", "123456789");
        assertEquals("John", client.getFirstName());
        assertEquals("Doe", client.getLastName());
        assertEquals("123 Main St", client.getAddress());
        assertEquals("123456789", client.getPassportNumber());
    }

    @Test
    public void testClientCreationWithMandatoryDetails() {
        Client client = new Client("Jane", "Doe");
        assertEquals("Jane", client.getFirstName());
        assertEquals("Doe", client.getLastName());
        assertNull(client.getAddress());
        assertEquals(null, client.getPassportNumber());
    }
}

