package studentorder.domain;

public class Street {
    private long street_code;
    private String name;

    public Street() {
    }

    public Street(long street_code, String name) {
        this.street_code = street_code;
        this.name = name;
    }

    public long getStreet_code() {
        return street_code;
    }

    public void setStreet_code(long street_code) {
        this.street_code = street_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
