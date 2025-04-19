package gr.aueb.cf.mobilecontacts.dao;



//convention gia classeς που υλοποιουν  interfaces

// H LISTA EINAI STATIK GIATI DE UELOYME KAUE INSTANS THS KLASHS NA EXEI ALLH LISTA MIA EINAI H LISTA

import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.ArrayList;
import java.util.List;

public class MobileContactDAOImpl implements IMobileContactDAO {
   private static final List<MobileContact> contacts = new ArrayList<>();
   // sthn oysia eiani enas construvctoer δημιουργει μια κλαση που μεσα εχει εναν πινακα


    @Override
    public MobileContact insert(MobileContact mobileContact) {
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

    }

    @Override
    public MobileContact getById(Long id) {
        return null;
    }

    @Override
    public List<MobileContact> getAll() {
        return List.of();
    }

    @Override
    public void deleteByphoneNumber(String phoneNumber) {

    }

    @Override
    public MobileContact getbyPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public boolean userIdExists(Long id) {
        return false;
    }

    @Override
    public boolean phoneNumberExists(String phoneNumber) {
        return false;
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

}
