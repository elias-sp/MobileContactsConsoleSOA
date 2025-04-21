package gr.aueb.cf.mobilecontacts.dao;



//convention gia classeς που υλοποιουν  interfaces

// H LISTA EINAI STATIK GIATI DE UELOYME KAUE INSTANS THS KLASHS NA EXEI ALLH LISTA MIA EINAI H LISTA

import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.ArrayList;
import java.util.List;

public class MobileContactDAOImpl implements IMobileContactDAO {
   private static final List<MobileContact> contacts = new ArrayList<>();   // sthn oysia eiani enas construvctoer δημιουργει μια κλαση που μεσα εχει εναν πινακα
   private static Long id=1L;

    @Override
    public MobileContact insert(MobileContact mobileContact) {
        mobileContact.setId(id++);
        contacts.add(mobileContact);
        return mobileContact;
    }

    @Override
    public MobileContact update(Long id, MobileContact mobileContact) {
        contacts.set(getIndexById(id),mobileContact);
        return mobileContact;
    }

    @Override
    public void deleteById(Long id) {
        //contacts.remove(getIndexById(id);
        contacts.removeIf(contact -> contact.getId().equals(id));
    }

    @Override
    public MobileContact getById(Long id) {
        int positionToReturn = getIndexById(id);
        return (positionToReturn !=-1)?contacts.get(positionToReturn):null;
        //τριαδικος αντι να γραφουμε if auto return ekeino else
    }

    @Override
    public List<MobileContact> getAll() {
        return new ArrayList<>(contacts);
    }

    @Override
    public void deleteByphoneNumber(String phoneNumber) {
        contacts.removeIf(contact -> contact.getPhoneNumber().equals(phoneNumber));
    }

    @Override
    public MobileContact getbyPhoneNumber(String phoneNumber) {
        int positionToReturn = getIndexByPhoneNumber(phoneNumber);
        return (positionToReturn !=-1)?contacts.get(positionToReturn):null;

    }

    @Override
    public boolean userIdExists(Long id) {
        int position = getIndexById(id);
        return position !=-1;    // προσοχή στην έκφραση true false
    }

    @Override
    public boolean phoneNumberExists(String phoneNumber) {
        int position = getIndexByPhoneNumber(phoneNumber);
        return position !=-1;
    }

    private int getIndexById(Long id) {
        int positionToReturn=-1;

        for (int i=0; i<contacts.size(); i++) {
            if (contacts.get(i).getId().equals(id)) {
                positionToReturn=i;
                break;
            }
        }
        return positionToReturn;
    }

    private int getIndexByPhoneNumber(String phoneNumber) {
        int positionToReturn=-1;

        for (int i=0; i<contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phoneNumber)) {
                positionToReturn=i;
                break;
            }
        }
        return positionToReturn;
    }



}
