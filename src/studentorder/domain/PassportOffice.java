package studentorder.domain;

public class PassportOffice {
    private Long officeID;
    private String officeAreaId;
    private String officeName;

    public PassportOffice() {
    }

    public PassportOffice(Long officeID, String officeAreaId, String officeName) {
        this.officeID = officeID;
        this.officeAreaId = officeAreaId;
        this.officeName = officeName;
    }

    public PassportOffice(Long aLong, String street_name) {
    }

    public Long getOfficeId() {
        return officeID;
    }

    public void setOfficeID(Long officeID) {
        this.officeID = officeID;
    }

    public String getOfficeAreaId() {
        return officeAreaId;
    }

    public void setOfficeAreaId(String officeAreaId) {
        this.officeAreaId = officeAreaId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }
}
