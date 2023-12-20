package by.bsuir.mycoolstore.entity.enums;

public enum AgeRestriction {
    EMPTY(""),
    ZERO("0+"),
    THREE("3+"),
    SIX("6+"),
    TWELVE("12+"),
    EIGHTEEN("18+"),
    TWENTY_ONE("21+");

    private final String stringValue;

    AgeRestriction(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * String converter
     *
     * @return String representation of AgeRestriction
     */
    @Override
    public String toString() {
        return stringValue;
    }

    /**
     * Create enum element from String
     *
     * @param ageString String of AgeRestriction
     * @return AgeRestriction element
     */
    public static AgeRestriction getAgeRestrictionFromString(String ageString) {
        for (AgeRestriction ageRestriction : AgeRestriction.values()) {
            if (ageRestriction.toString().equals(ageString)) {
                return ageRestriction;
            }
        }

        return null;
    }
}
