import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactManagerTest {

    @Test
    public void shouldCreateContact() {
        ContactManager contactManager = new ContactManager();
        contactManager.addContact(" ", "Karapetyan", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream().
                anyMatch(contact -> contact.getFirstName().equals("Artak") &&
                        contact.getLastName().equals("Karapetyan") &&
                        contact.getPhoneNumber().equals("0123456789")));
    }

    @Test
    @DisplayName("Should not create a contact when the first name is null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        ContactManager contactManager = new ContactManager();
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Karapetyan", "0123456789");
        });
    }

    @Test
    @DisplayName("Should not create a contact when the last name is null.")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
        ContactManager contactManager = new ContactManager();
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Artak", null, "0123456789");
        });
    }

    @Test
    @DisplayName("Should not create a contact when the phone number is null.")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
        ContactManager contactManager = new ContactManager();
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Artak", "Karapetyan", null);
        });
    }

    @Test
    @DisplayName("Should not create a contact when the phone number doesn't consist of digits.")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsntDigits() {
        ContactManager contactManager = new ContactManager();
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Artak", "Karapetyan", "abcdefghij");
        });
    }
}

//    @Test
//    public void shouldCreateContact() {
//        ContactManager contactManager = new ContactManager();
//        contactManager.addContact("Artak", "Karapetyan", "0123456789");
//        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
//        Assertions.assertEquals(1, contactManager.getAllContacts().size());
//        Assertions.assertTrue(contactManager.getAllContacts().stream().
//                filter(contact -> contact.getFirstName().equals("Artak") &&
//                        contact.getLastName().equals("Karapetyan") &&
//                        contact.getPhoneNumber().equals("0123456789")).findAny().isPresent());
//    }