package models;

import utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee{
    /**
     * Manager Class is a subclass of the Employee class and includes fields
     * and methods specific to the position of manager as well as contains
     * a list of employee objects corresponding to said manager
     * @author Cian Farrell
     * @version 1.0
     */
    private List<Employee> dept;
    private int grade;

    /**
     * This method creates a Manager object with the given characteristics,
     * If the passed in parameters conform to their respective rules.
     * @param firstName First name of the manager
     * @param secondName surname of the manager
     * @param ppsNumber PPS number of the manager
     * @param grade Paygrade of the manager
     */
    public Manager(String firstName, String secondName, String ppsNumber, int grade) {
        super(firstName, secondName, ppsNumber);
        if(Utilities.validManagerGrade(grade)){
        this.grade = grade;}
        else {
            this.grade = 1;
        }
        this.dept = new ArrayList<>();
    }

    /**
     * Gets the list of employees in the manager's department
     * @return List of employees
     */
    public List<Employee> getDept() {
        return dept;
    }

    /**
     * Sets the list of employees in a managers department
     * @param dept List of employees to be set to
     */
    public void setDept(List<Employee> dept) {
        this.dept = dept;
    }

    /**
     * Gets the grade of the manager
     * @return Grade of manager
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Sets the grade of the manager if it is between 1-7
     * @param grade Sets the managers grade
     */
    public void setGrade(int grade) {
        if(Utilities.validManagerGrade(grade)){
            this.grade = grade;}
    }

    /**
     * Checks if the current manager is equal to the manager being passed in as a parameter.
     * @param manager A manager to be checked if equal
     * @return A boolean if the managers are equal or not
     */
    public boolean equals(Manager manager) {
        return this.grade == manager.grade && this.dept.equals(manager.dept) && super.equals(manager);
    }

    /**
     * Adds on the Bonus of the manager to the base salary calculated by a utility
     * @return Salary of the manager
     */
    public double calculateSalary() {
        double bonus = 0;
        for (Employee employee : dept) {
            bonus += employee.calculateSalary() * .01;
        }
        return (Utilities.getSalaryForManagerGrade(grade) + bonus);
    }


    /**
     * Adds an employee to the manager's department
     * @param employee Employee object to be added
     */
    public void addDeptEmployee(Employee employee){ dept.add(employee);
    }

    /**
     * Removes the employee at the given index
     * @param index Position of employee to be removed
     * @return Removed Employee
     */
    public boolean removeEmployee(int index) {
        if(Utilities.validIndex(index,dept)) {
            dept.remove(index);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Calculates the number of employees in a department
      * @return Size of a department
     */
    public int numberInDept(){
        return dept.size();
    }

    /**
     * Goes through every employee in the list until a matching surname is found
     * If a matching surname is found the loop is exited and the index is returned
     * @param surname A surname to be searched for
     * @return The index that the employee is found at
     */
    public int searchEmployees(String surname) {
        int index = -1; // creates an int with value -1
        for (int i = 0; i < dept.size(); i++) {
            if (dept.get(i).getSecondName().equalsIgnoreCase(surname)) {
                index = i; // changes the int index to the value if there is a match
                break; // brings the code out of the for loop since the first match is required
            }
        }
        return index;
    }

    /**
     * Lists all the employees in a managers department
     * @return String with the managers employee
     */
    public String listEmployees(){
        if(!dept.isEmpty()){
            String list = "";
            for(int i = 0; i<dept.size(); i++){
                Employee employee = dept.get(i);
                list += i + ": " + employee.getFirstName() + " " + employee.getSecondName() + "\n";
            }
            return list;
        }
        return "Manager has no employees in their department";
    }
    /**
     * Generates a toString based on the characteristics of the manager
     * Returns "No Employees" in place of the dept if its empty
     * @return A string containing all the parameters of the manager
     */
    @Override
    public String toString() {
        if(!dept.isEmpty()) {
            return "Manager" + "\n" +
                    super.toString() +
                    "Dept = " + dept + "\n" +
                    "Grade = " + grade + "\n";
        }else{
            return "Manager" + "\n" +
                    super.toString() +
                    "Dept = " + "No Employees" + "\n" +
                    "Grade = " + grade + "\n";
        }
    }
}
