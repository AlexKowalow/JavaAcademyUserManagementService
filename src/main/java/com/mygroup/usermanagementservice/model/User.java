package com.mygroup.usermanagementservice.model;

public class User {
    private Long pesel;
    private String firstName;
    private String lastName;
    private String postCode;
    private String city;
    private String email;

    public User() {
    }

    public User(Long pesel, String firstName, String lastName, String postCode, String city, String email) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
        this.city = city;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "pesel=" + pesel +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {
        this.pesel = pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
