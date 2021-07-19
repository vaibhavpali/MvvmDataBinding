package com.vaibhav.kardia.model;

public class User {
    private String mFirstName;
    private String mLastName;
    private String mDob;

    public User(String firstName, String lastName, String dob) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mDob = dob;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getDob() {
        return mDob;
    }

    public void setDob(String dob) {
        this.mDob = dob;
    }
}
