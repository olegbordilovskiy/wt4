package by.bsuir.mycoolstore.entity.enums;

public enum Role {
    ADMIN("admin"),
    CUSTOMER("customer");

    private final String stringValue;

    Role(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * String converter
     *
     * @return String representation of Role
     */
    @Override
    public String toString() {
        return stringValue;
    }

    /**
     * Create enum element from String
     *
     * @param roleString String of Role
     * @return Role element
     */
    public static Role getRoleFromString(String roleString) {
        for (Role role : Role.values()) {
            if (role.toString().equals(roleString)) {
                return role;
            }
        }

        return null;
    }
}
