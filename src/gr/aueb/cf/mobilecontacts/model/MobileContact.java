package gr.aueb.cf.mobilecontacts.model;

import java.util.Objects;

public class MobileContact extends AbstractEntity{
    private String firstname;
    private String lastname;
    private String phoneNumber;

    public MobileContact() {
    }

    public MobileContact(long id, String firstname, String lastname, String phonenumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phonenumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "firstname" + firstname + "," + "lastname:" + lastname + ","+"phonenumber'" + phoneNumber;
    }

    // ΤΟ ΤΗΑΤ είναι το "ο" είναι ψευδώνυμο
    // κάνεις δήλωση ότι το that εδώ είναι το "ο" (το "ο" το είχαμε μετονομάσει σε "other" και έγινε μύλος
    // δηλαδή και ελέγχει και δηλώνει το 'ο"

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof MobileContact that)) return false;
        //MobileContact that = (MobileContact) other; ελεγχει αν το other ειανι μοβιλεψονταψτ και μετα το κανει τυπεκαστ στο τ
        return Objects.equals(getFirstname(), that.getFirstname())
                && Objects.equals(getLastname(), that.getLastname())
                && Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }





    @Override
    public int hashCode() {
        return Objects.hash(getFirstname(), getLastname(), getPhoneNumber());
    }
}
