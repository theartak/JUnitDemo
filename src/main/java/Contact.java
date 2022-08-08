public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void firstNameValidator() {
        if (this.firstName.isBlank())
            throw new RuntimeException("First name cannot be blank.");
    }

    public void lastNameValidator() {
        if (this.lastName.isBlank())
            throw new RuntimeException("Last name cannot be blank.");
    }

    public void phoneNumberValidator() {
        if (this.phoneNumber.isBlank()) {
            throw new RuntimeException("Phone number cannot be blank.");
        }

        if (this.phoneNumber.length() != 10) {
            throw new RuntimeException("Phone number must contain 10 digits.");
        }

        if (!this.phoneNumber.matches("[0-9]+")) {
            throw new RuntimeException("Phone number can only contain digits.");
        }

        if (!this.phoneNumber.startsWith("0")) {
            throw new RuntimeException("Phone number must start with 0.");
        }
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
