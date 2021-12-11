package users;

/**
 * A self-explanatory enum for different types of genders in a User application. It provides a methods for constructing
 * a Gender instance based on a string and also overrides the toString() method
 *
 * @author Edward Koulakidis
 */

public enum Gender {
    MALE("male"),
    FEMALE("female");

    public final String value;

    /**
     * Sets the value field of the enum to the String representation given as a parameter "male"/"female"
     *
     * @param value A value with valid types
     */
    Gender(String value) {
        this.value = value;
    }


    /**
     * A method useful for getting a Gender instance based on a String value. It is used usually in the context of
     * creating User objects with the desired Gender attribute
     *
     * @param value A string value representing a Gender (Male/Female)
     * @return A Gender instance based on the value given
     * @see User
     */
    public static Gender getGender(String value) {
        if ("male".equalsIgnoreCase(value)) return MALE;
        if ("female".equalsIgnoreCase(value)) return FEMALE;
        return null;
    }

    /**
     * Basic override of the toString() method which returns the lowercase String value of a Gender instance
     *
     * @return A string representation of the Gender instance
     */
    @Override
    public String toString() {
        return value;
    }
}
