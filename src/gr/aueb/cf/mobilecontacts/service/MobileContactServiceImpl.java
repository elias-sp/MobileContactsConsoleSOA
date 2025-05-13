package gr.aueb.cf.mobilecontacts.service;

import gr.aueb.cf.mobilecontacts.dao.IMobileContactDAO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;
import gr.aueb.cf.mobilecontacts.exceptions.ContactNotFoundException;
import gr.aueb.cf.mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.mapper.Mapper;
import gr.aueb.cf.mobilecontacts.model.MobileContact;
import java.util.List;


public class MobileContactServiceImpl implements IMobileContactService {
    //θα χρησιμοποιησει το dao επομενως πρεπει να του δωσουμε ενα ινστανσε του δαο

    //το dependancy injection θελει ως private instances να εχουμε intrerfaces
    private final IMobileContactDAO dao;

        //κανουμε inject μέσω του constructor - "is a"
        public MobileContactServiceImpl(IMobileContactDAO dao) {
        this.dao = dao;
    }

    @Override
    public MobileContact insertMobileContact(MobileContactInsertDTO dto)
            throws PhoneNumberAlreadyExistsException {
        //επιστρέφει ενα Mobile contact άρα πρεπει να το δηλώσουμε
        MobileContact mobileContact;
        try{
            //ελεγχουμε το Insert εαν υπάρχει
            if (dao.phoneNumberExists(dto.getPhoneNumber())) {
                throw new PhoneNumberAlreadyExistsException("Contact number" + dto.getPhoneNumber() + "already exists");
            }
        //αντιστοίχιση dto σε model entity: mapping (και μεθοδος προς το τέλος)
        // το dto γινεται insert και Mapping σε new MobileContact και γινεται assign στο mobilecontact
        mobileContact = Mapper.mapInsertDTOToContact(dto);
            //στις crud πραξεις καλό είναι να καταγραφουμε σε logfile τις μεταβολες
            System.err.printf("MobileContactServiceImpl Logger: %s was insert", mobileContact);
            return dao.insert(mobileContact);
        } catch (PhoneNumberAlreadyExistsException e) {
            System.err.printf("MobileContactServiceImpl Logger: Contact with phone number %s already exists", dto.getPhoneNumber());
            throw e;
        }

    }

    @Override
    public MobileContact updateMobileContact(MobileContactUpdateDTO dto)
            throws PhoneNumberAlreadyExistsException, ContactNotFoundException {

            MobileContact mobileContact;
            MobileContact newContact;
            try {
                //εάν δεν υπάρχει αυτό το user id έχουμε failure:
                if (!dao.userIdExists(dto.getId())) {
                    throw new ContactNotFoundException("Contact with id:" + dto.getId()+"not found for update");
                }
                //αφού τον έχουμε τον αριθμό mobile contact μπορούμε να τον πάρουμε και να δουμε με id
                //εάν ο αριθμός είναι ο 'δικός' μας- (που θέλουμε να ανανεώσουμε με τον ίδιο αριθμό)
                mobileContact=dao.getById(dto.getId());
                boolean isPhoneNumberOurOwn = mobileContact.getPhoneNumber().equals(dto.getPhoneNumber());
                boolean isPhoneNumberExists = dao.phoneNumberExists(dto.getPhoneNumber());

                //εάν υπάρχει και είναι δικός μας
                if (isPhoneNumberExists && !isPhoneNumberOurOwn) {
                    throw new PhoneNumberAlreadyExistsException("Contact with phone number" + dto.getPhoneNumber() + "already exists");
                }

                newContact = Mapper.mapUpdateDTOToContact(dto);
                System.err.printf("MobileContactService Logger:contact %s updated with new info: %s", mobileContact, newContact);
                return dao.update(dto.getId(), newContact);
            } catch (ContactNotFoundException | PhoneNumberAlreadyExistsException e) {
                System.err.println(e.getMessage());
                throw e;
            }
    }

    @Override
    public void deleteContactById(Long id) throws ContactNotFoundException {
    // πρεπει να ελέγξει εάν υπάρχει η contact για την διαγραφή
            try {
                if (!dao.userIdExists(id)){
                    throw new ContactNotFoundException("Contact id" +id+ "not found");
                   }
                System.err.printf("MobileContactService Logger: Contact id %s deleted\n", id);
                dao.deleteById(id);

         } catch (ContactNotFoundException e) {
                System.err.printf("MobileContactService Logger: %s\n", e.getMessage());

        }

    }

    @Override
    public MobileContact getContactById(Long id) throws ContactNotFoundException {
        MobileContact mobileContact;
        try {
            mobileContact = dao.getById(id);
            if (mobileContact==null) {
                throw new ContactNotFoundException("Contact id" +id+ "not found");
            }
            return mobileContact;
        } catch (ContactNotFoundException e) {
            System.err.printf("MobileContactService Logger: Contact with id %d was not found\n", id);
            throw e;
        }
    }

    @Override
    public List<MobileContact> getAllContacts() {
        return dao.getAll();
    }

    @Override
    public MobileContact getContactByPhoneNumber(String phoneNumber) throws ContactNotFoundException {
        MobileContact mobileContact;
        try {
            mobileContact = dao.getbyPhoneNumber(phoneNumber);
            if (mobileContact==null) {
                throw new ContactNotFoundException("Contact phonenumber" +phoneNumber+ "not found");
            }
            return mobileContact;
        } catch (ContactNotFoundException e) {
            System.err.printf("MobileContactService Logger: Contact with id %d was not found\n", phoneNumber);
            throw e;
        }
    }

    @Override
    public void deleteContactByPhoneNumber(String phoneNumber) throws ContactNotFoundException {
        try {
            if (!dao.phoneNumberExists(phoneNumber)){
                throw new ContactNotFoundException("Contact phone number" +phoneNumber+ "not found");
            }
            System.err.printf("MobileContactService Logger: Contact phone number %s deleted\n", phoneNumber);
            dao.deleteByphoneNumber(phoneNumber);

        } catch (ContactNotFoundException e) {
            System.err.printf("MobileContactService Logger: %s\n", e.getMessage());
            throw e;
        }
    }


//    τισ σβησαμε και τισ πηγαμε στο μαππερ //Insert mapper - map το dto σε model entity
//    private MobileContact mapInsertDTOToContact (MobileContactInsertDTO dto) {
//            return new MobileContact(null, dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
//    }
//
//    //update mapper - map dto σε model entity
//    private MobileContact mapUpdateDTOToContact (MobileContactUpdateDTO dto) {
//        return new MobileContact(dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
//    }

}
