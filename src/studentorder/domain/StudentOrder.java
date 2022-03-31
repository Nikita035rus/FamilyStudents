package studentorder.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentOrder {
   private long studentOrderId;
   private Adult husband;
   private Adult wife;
   private List<Child> children;
   private String marriageCertification;
   private String marriageOffice;
   private LocalDate marriageDate;

public void addChild(Child child){
    if(children == null){
        children = new ArrayList<>(5);
    }
    children.add(child);
}

    public long getStudentOrderId() {
        return studentOrderId;
    }

    public void setStudentOrderId(long studentOrderId) {
        this.studentOrderId = studentOrderId;
    }

    public Adult getHusband() {
        return husband;
    }

    public void setHusband(Adult husband) {
        this.husband = husband;
    }

    public Adult getWife() {
        return wife;
    }

    public void setWife(Adult wife) {
        this.wife = wife;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public String getMarriageCertification() {
        return marriageCertification;
    }

    public void setMarriageCertification(String marriageCertification) {
        this.marriageCertification = marriageCertification;
    }

    public String getMarriageOffice() {
        return marriageOffice;
    }

    public void setMarriageOffice(String marriageOffice) {
        this.marriageOffice = marriageOffice;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }
}
