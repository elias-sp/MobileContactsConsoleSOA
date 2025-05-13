package gr.aueb.cf.mobilecontacts.mapper;

import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;
import gr.aueb.cf.mobilecontacts.model.MobileContact;

    public class Mapper {
    /**
     * no instances of this class should be available
     */

    private Mapper() {
    }

    //Insert mapper - map το dto σε model entity
    public static MobileContact mapInsertDTOToContact (MobileContactInsertDTO dto) {
        return new MobileContact(null, dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
    }

    //update mapper - map dto σε model entity
    public static MobileContact mapUpdateDTOToContact (MobileContactUpdateDTO dto) {
        return new MobileContact(dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getPhoneNumber());
    }

}
