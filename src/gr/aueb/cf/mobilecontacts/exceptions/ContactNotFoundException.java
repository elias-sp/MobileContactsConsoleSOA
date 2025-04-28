package gr.aueb.cf.mobilecontacts.exceptions;

public class ContactNotFoundException extends Exception {

    public ContactNotFoundException(String message) {
        super(message); //δεν εχουμε private message οποτε κανουμε extend απο την στιγμη που κανουμε extends καλουμε ενα super
    }

}
