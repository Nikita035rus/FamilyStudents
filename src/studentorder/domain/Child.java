package studentorder.domain;

import java.time.LocalDate;

public class Child extends Person{
    private String certificateNumber;
    private LocalDate issueDate;
    private String issueDepartment;

    public Child(String name,
                 String surName,
                 String patronymic,
                 LocalDate dateOfBirthday,
                 String certificateNumber,
                 LocalDate issueDate,
                 String issueDepartment) {
        super(name, surName, patronymic, dateOfBirthday);
        this.certificateNumber = certificateNumber;
        this.issueDate = issueDate;
        this.issueDepartment = issueDepartment;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }
}
