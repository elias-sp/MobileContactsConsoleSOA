package gr.aueb.cf.mobilecontacts.dao;


import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.List;

// οι βασικές λειτουργίες του DAO
public interface IMobileContactDAO {
    MobileContact insert(MobileContact mobileContact);
    MobileContact update(Long id, MobileContact mobileContact);
    void deleteById(Long id);
    MobileContact getById(Long id);
    List<MobileContact> getAll();

// Long - long στις σημειωσεις

    //ΤΑ QUERIES
    void deleteByphoneNumber(String phoneNumber);

    MobileContact getbyPhoneNumber(String phoneNumber);
    boolean userIdExists(Long id);
    boolean phoneNumberExists(String phoneNumber);

}


