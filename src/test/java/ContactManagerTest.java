import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {
    ContactManager contactManager;

    @BeforeAll
    public void setupAll() {
        System.out.println("Printed before all.");
    }

    @BeforeEach
    public void setup() {
        contactManager = new ContactManager();
    }

    @Test
    public void shouldCreateContact() {
        contactManager.addContact("Artak", "Karapetyan", "0123456789");
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
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Karapetyan", "0123456789");
        });
    }

    @Test
    @DisplayName("Should not create a contact when the last name is null.")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Artak", null, "0123456789");
        });
    }

    @Test
    @DisplayName("Should not create a contact when the phone number is null.")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Artak", "Karapetyan", null);
        });
    }

    @Test
    @DisplayName("Should not create a contact when the phone number doesn't consist of digits.")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsntDigits() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Artak", "Karapetyan", "abcdefghij");
        });
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Executed after each test.");
    }

    @AfterAll
    public void tearDownAll() {
        System.out.println("Executed at the end of the test.");
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