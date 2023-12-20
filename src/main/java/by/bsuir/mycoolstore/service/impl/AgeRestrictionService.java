package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.entity.enums.AgeRestriction;

import java.util.ArrayList;
import java.util.List;

/**
 * The AgeRestrictionService class provides methods for retrieving age restrictions.
 */
public class AgeRestrictionService {
    /**
     * Retrieves a list of age restrictions.
     *
     * @return A list of age restrictions.
     */
    public static List<String> getAgeRestrictions() {
        List<String> ageRestrictions = new ArrayList<>();

        // Iterate over all AgeRestriction values and add them to the list
        for (AgeRestriction ageRestriction : AgeRestriction.values()) {
            ageRestrictions.add(ageRestriction.toString());
        }

        // Remove the EMPTY age restriction from the list
        ageRestrictions.remove(AgeRestriction.EMPTY.toString());

        return ageRestrictions;
    }
}
