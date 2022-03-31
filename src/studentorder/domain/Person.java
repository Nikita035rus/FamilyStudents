package studentorder.domain;

import java.time.LocalDate;

public abstract class Person {
    private String name;
    private String surName;
    private String patronymic;
    private LocalDate dateOfBirthday;
    private Address address;

    public Person() {
    }

    public Person(String name, String surName, String patronymic, LocalDate dateOfBirthday) {
        this.name = name;
        this.surName = surName;
        this.patronymic = patronymic;
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
