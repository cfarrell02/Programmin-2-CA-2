package models;

import utils.Utilities;

public class AdminWorker extends Employee {
    private int grade;
    private static final float FIXED_BONUS = 200;

    public AdminWorker(String firstName, String secondName, String ppsNumber, int grade) {
        super(firstName, secondName, ppsNumber);
        if(Utilities.validAdminGrade(grade)){
        this.grade = grade;}
        else{
            this.grade = 1;
        }
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        if(Utilities.validAdminGrade(grade)){
            this.grade = grade;}
    }


    public boolean equals(AdminWorker adminWorker) {
        return this.grade == adminWorker.grade && super.equals(adminWorker);
    }

    @Override
    public String toString() {
        return "AdminWorker:" + "\n" +
                super.toString() +
                "Grade = " + grade + "\n";
    }

    public double calculateSalary(){
        return Utilities.getSalaryForAdminGrade(grade)+FIXED_BONUS;
    }

}
