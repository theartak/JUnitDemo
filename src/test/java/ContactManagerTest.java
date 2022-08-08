import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Random;

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

    @Test
    @DisplayName("Should be visible on MAC.")
    @EnabledOnOs(value = OS.MAC)
    public void shouldBeVisibleOnMAC() {
        System.out.println("Visible on MAC.");
    }

    @Test
    @DisplayName("Shouldn't be visible on Windows.")
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Disabled on Windows OS.")
    public void shouldntBeVisibleOnWindows() {
        System.out.println("MAC is cool.");
    }

    @Test
    @DisplayName("Should create contact on DEV.")
    public void shouldCreateContactOnDEV() {
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        contactManager.addContact("Artak", "Karapetyan", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Shouldn't create contact on TEST.")
    public void shouldntCreateContactOnTEST() {
        Assumptions.assumeTrue("TEST".equals(System.getProperty("ENV")));
        contactManager.addContact("Artak", "Karapetyan", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("Repeat generating random numbers.")
    @RepeatedTest(value = 5, name = "Repeatedly creating random numbers {currentRepetition} of {totalRepetitions}")
    public void repeatedNumGeneration() {
        Random random = new Random();
        int randomNum = random.nextInt(1, 6);
        System.out.println(randomNum);
    }

    @DisplayName("CSV source case - phone number should match the required format.")
    @ParameterizedTest
    @CsvSource({"0123456789", "0123456798", "0123456897"})
    public void shouldTestPhoneNumberUsingCSVSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("CSV file source case - phone number should match the required format.")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void shouldTestPhoneNumberUsingCSVFileSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }
}