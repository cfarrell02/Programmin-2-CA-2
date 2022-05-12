package models;

import utils.Utilities;

public abstract class Employee {
    private String firstName;
    private String secondName;
    private String ppsNumber;

    public Employee(String firstName, String secondName, String ppsNumber) {
        if (Utilities.max10Chars(firstName)) {
            this.firstName = firstName;
        } else {
            this.firstName = firstName.substring(0, 10);
        }
        if (Utilities.max10Chars(secondName)) {
            this.secondName = secondName;
        } else {
            this.secondName = secondName.substring(0, 10);
        }
        if (Utilities.validPPS(ppsNumber)) {
            this.ppsNumber = ppsNumber;
        } else {
            this.ppsNumber = "0000000XX";
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (Utilities.max10Chars(firstName)) {
            this.firstName = firstName;
        }
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        if (Utilities.max10Chars(secondName)) {
            this.secondName = secondName;
        }
    }

    public String getPpsNumber() {
        return ppsNumber;
    }

    public void setPpsNumber(String ppsNumber) {
        if (Utilities.validPPS(ppsNumber)) {
            this.ppsNumber = ppsNumber;
        }
    }
    public abstract double calculateSalary();


    public boolean equals(Employee employee) {
        return this.firstName.equals( employee.firstName) && this.secondName.equals( employee.secondName)
                && this.ppsNumber.equals(employee.ppsNumber);
    }


    @Override
    public String toString() {
        return
                "FirstName = " + firstName + '\n' +
                "SecondName = " + secondName + '\n' +
                "PPSNumber = " + ppsNumber + "\n";
    }
}
