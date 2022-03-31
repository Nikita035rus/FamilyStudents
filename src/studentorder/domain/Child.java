package studentorder.domain;

import java.time.LocalDate;

public class Child extends Person{
    private String certificateNumber;
    private LocalDate issueDate;
    private RegisterOffice registerOffice;

    public Child(String name,
                 String surName,
                 String patronymic,
                 LocalDate dateOfBirthday) {
        super(name, surName, patronymic, dateOfBirthday);
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

    public RegisterOffice getRegisterOffice() {
        return registerOffice;
    }

    public void setRegisterOffice(RegisterOffice registerOffice) {
        this.registerOffice = registerOffice;
    }
}
