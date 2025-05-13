package gr.aueb.cf.mobilecontacts.validation;

import gr.aueb.cf.mobilecontacts.dto.MobileContactInsertDTO;
import gr.aueb.cf.mobilecontacts.dto.MobileContactUpdateDTO;

public class ValidationUtil {

    /**
     * no instances of this class should be available
     */

    private ValidationUtil(){

    }

    public static String validateDTO(MobileContactInsertDTO insertDTO) {
        String errorResponse = "";
        if (insertDTO.getPhoneNumber().length()<=5)
            errorResponse += "Ο τηλ αριθμος πρεπει να έχει περισσοτερα απο 5 ψηφια.\n";
        if (insertDTO.getFirstname().length()<2)
            errorResponse += "το όνομα πρεπει να έχει 2 ή περισσοτερους χαρακτήρες .\n";
        if (insertDTO.getLastname().length()<2)
            errorResponse += "το επώνυμο πρεπει να έχει 2 ή περισσοτερους χαρακτήρες.\n ";
        return errorResponse;
    }

    public static String validateDTO(MobileContactUpdateDTO updateDTO) {
        String errorResponse = "";
        if (updateDTO.getPhoneNumber().length()<=5)
            errorResponse += "Ο τηλ αριθμος πρεπει να έχει περισσοτερα απο 5 ψηφια.\n";
        if (updateDTO.getFirstname().length()<2)
            errorResponse += "το όνομα πρεπει να έχει 2 ή περισσοτερους χαρακτήρες .\n";
        if (updateDTO.getLastname().length()<2)
            errorResponse += "το επώνυμο πρεπει να έχει 2 ή περισσοτερους χαρακτήρες.\n ";
        return errorResponse;
    }






}
