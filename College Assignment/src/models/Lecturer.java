package models;

import utils.Utilities;

public class Lecturer extends Employee{
    private int level;

    public Lecturer(String firstName, String secondName, String ppsNumber, int level) {
        super(firstName, secondName, ppsNumber);
        if(Utilities.validLecturerLevel(level)){
        this.level = level;}
        else{
            this.level = 1;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(Utilities.validLecturerLevel(level)){
            this.level = level;}
    }

    public boolean equals(Lecturer lecturer) {
        return this.level == lecturer.level && super.equals(lecturer);
    }


    public double calculateSalary(){
    return Utilities.getSalaryForLecturerLevel(level);
    }

    @Override
    public String toString() {
        return "Lecturer: " + "\n" +
                super.toString() +
                "Level = " + level + "\n";
    }
}
