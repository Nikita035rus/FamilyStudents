package studentorder.domain;

public class Address {
    private Street street;
    private String building;
    private String extension;
    private String flat;
    private String postCode;

    public Address(Street street, String building, String extension, String flat, String postCode) {
        this.street = street;
        this.building = building;
        this.extension = extension;
        this.flat = flat;
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }
}
