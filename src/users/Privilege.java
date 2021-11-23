package users;

/**
 * An enumeration useful for restricting user access/options. It currently has 3 modes (Customer/Broker/Admin) and provides
 * a method for translating a string to a privilege.
 */
public enum Privilege {
    /**
     * A customer Privilege,useful for applications where handling customers and customer input is essential
     */
    CUSTOMER("customer"),
    /**
     * A broker Privilege,useful for applications where Broker handling is involved e.g. booking,stock market,real estate
     */
    BROKER("broker"),
    /**
     * An admin Privilege,useful for any application that needs to be monitored by privileged staff
     */
    ADMIN("admin");

    public final String value;

    Privilege(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * A method useful for getting a Privilege instance based on a String value. It is used usually in the context of
     * creating User objects
     *
     * @param value A string value representing a privilege(Broker/Admin/Customer)
     * @return A Privilege instance based on the value given
     * @see User
     */
    public static Privilege getPrivileges(String value) {
        if ("broker".equals(value)) return BROKER;
        if ("customer".equals(value)) return CUSTOMER;
        if ("admin".equals(value)) return ADMIN;
        return null;
    }
}
