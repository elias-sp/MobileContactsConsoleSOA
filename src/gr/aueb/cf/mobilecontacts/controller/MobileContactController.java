package gr.aueb.cf.mobilecontacts.controller;

import gr.aueb.cf.mobilecontacts.dao.IMobileContactDAO;
import gr.aueb.cf.mobilecontacts.dao.MobileContactDAOImpl;
import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactReadOnlyDTO;
import gr.aueb.cf.mobilecontacts.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.cf.mobilecontacts.model.MobileContact;
import gr.aueb.cf.mobilecontacts.service.IMobileContactService;
import gr.aueb.cf.mobilecontacts.service.MobileContactServiceImpl;
import gr.aueb.cf.mobilecontacts.validation.ValidationUtil;

public class MobileContactController {

    // ΕΔΩ ο controller κανει wiring
    private final IMobileContactDAO dao = new MobileContactDAOImpl();
    private final IMobileContactService service = new MobileContactServiceImpl(dao);
    // τα κανουμε final για να δειξουμε οτι δεν μπορει να αλλαξει αυτη η αναφορα


    public String insertContact(MobileContactInsertDTO insertDTO) {
        MobileContact mobileContact;
        MobileContactReadOnlyDTO readOnlyDTO;
        try {
            // validate input data
            String errorVector = ValidationUtil.validateDTO(insertDTO);
            if (!errorVector.isEmpty()) {
                return "Error." + "validation error\n" + errorVector;
            }

            // if validation is ok insert contact
            mobileContact = service.insertMobileContact(insertDTO);
            readOnlyDTO = mapMobileContactDTO(mobileContact);
            return "OK\n" + serializeDTO(readOnlyDTO);
        } catch (PhoneNumberAlreadyExistsException e) {
            return "Error\n" + e.getMessage() + "\n";
    }

}

// to αντιθετο απο το service το service έπαιρνε dto αι τα έκανε model. εδω το
private MobileContactReadOnlyDTO mapMobileContactDTO(MobileContact mobileContact) {
    return new MobileContactReadOnlyDTO(mobileContact.getId(), mobileContact.getFirstname(),
            mobileContact.getLastname(), mobileContact.getPhoneNumber());
    }
private String serializeDTO(MobileContactReadOnlyDTO readOnlyDTO) {
        return "ID:" + readOnlyDTO.getId() + "onoma" + readOnlyDTO.getFirstname()
                + "lastname" + readOnlyDTO.getLastname() + "phone number" + readOnlyDTO.getPhoneNumber();
}


}
