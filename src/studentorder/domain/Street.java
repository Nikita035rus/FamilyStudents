package studentorder.domain;

public class Street {
    private long street_code;
    private String streetName;

    public Street() {
    }

    public Street(long street_code, String name) {
        this.street_code = street_code;
        this.streetName = name;
    }

    public long getStreet_code() {
        return street_code;
    }

    public void setStreet_code(long street_code) {
        this.street_code = street_code;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setName(String name) {
        this.streetName = name;
    }
}
