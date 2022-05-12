package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Employee;
import models.Manager;
import utils.Utilities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAPI {
    /**
     * An API class that manages all the employees in the Science and Computing school
     *
     * @author Cian Farrell
     * @version 1.0
     */
    List<Employee> employees;

    /**
     * This method creates an EmployeeAPI and initialises an Arraylist
     */
    public EmployeeAPI() {
        this.employees = new ArrayList<>();
    }

    /**
     * Adds an employee into the employee API list
     *
     * @param employee Employee to be added
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    /**
     * Gets the list of employees
     *
     * @return List of employees
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Sets the list of employees to the given list
     *
     * @param employees A List for the employees to be set to
     */
    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Gets the employee at the given index
     *
     * @param index The index of the employee to be gotten
     * @return The employee at the index
     */
    public Employee getEmployee(int index) {
        if (Utilities.validIndex(index, employees)) {
            return employees.get(index);
        } else {
            return null;
        }
    }

    /**
     * Removes an employee with a matching surname as the string passed through
     *
     * @param lastName The surname of the employee to be deleted
     * @return The deleted employee object
     */
    public Employee removeEmployee(String lastName) {
        Employee deletedEmployee = null; //Creates employee variable and sets it to null
        if (searchEmployees(lastName) != -1) {
            deletedEmployee = employees.remove(searchEmployees(lastName));
        }
        return deletedEmployee; //returns the employee variable
    }

    /**
     * Removes employee at the given index
     *
     * @param index Index of employee to be removed
     * @return removed employee
     */
    public Employee removeEmployee(int index) {
        return employees.remove(index);
    }

    /**
     * Calculates the numbers of employees in the system
     *
     * @return Number of employees
     */
    public int numberOfEmployees() {
        return employees.size(); // Returns the amount of employees
    }

    /**
     * Lists All employees in the EmployeeAPI list
     *
     * @return A list of all employees
     */
    public String listEmployees() {
        if (!employees.isEmpty()) {
            String list = "";
            for (int i = 0; i < employees.size(); i++) {
                list += i + ": " + (employees.get(i) + "\n");  // Creates a string including every employee if there are any
            }
            return list;
        } else {
            return "No Employees Registered";   // returns this if the arraylist is empty
        }
    }

    /**
     * Lists all employees names only
     *
     * @return String of employee names
     */
    public String listEmployeeNames() {
        if (!employees.isEmpty()) {
            String list = "";
            for (int i = 0; i < employees.size(); i++) {
                list += i + ": " + employees.get(i).getFirstName() + " " + employees.get(i).getSecondName() + "\n";
                // Creates an arraylist including every employee if there are any
            }
            return list;
        } else {
            return "No Employees Registered";   // returns this if the arraylist is empty
        }
    }

    /**
     * Lists all employees who are not a manager
     *
     * @return non manager employee's names
     */
    public String listNonManagers() {
        if (!employees.isEmpty()) {
            String list = "";
            for (int i = 0; i < employees.size(); i++) {
                if (!(employees.get(i) instanceof Manager)) {
                    list += i + ": " + employees.get(i).getFirstName() + " " + employees.get(i).getSecondName() + "\n";
                }
                // Creates an arraylist including every non manager if there are any
            }
            return list;
        } else {
            return "No Employees Registered";   // returns this if the arraylist is empty
        }
    }

    /**
     * Adds employee to the specified managers department
     *
     * @param employeeIndex The index of the employee
     * @param managerIndex  The index of the manager
     * @return If the Employee has been added or not
     */
    public boolean addEmployeeToDepartment(int employeeIndex, int managerIndex) {
        if ((Utilities.validIndex(managerIndex, employees)) && (Utilities.validIndex(employeeIndex, employees))) {
            if ((employees.get(managerIndex) instanceof Manager)) {
                //If the and manager indices are valid in the arraylist and the manager is a valid manager
                //the given employee is added to the managers arraylist.
                ((Manager) employees.get(managerIndex)).addDeptEmployee(employees.get(employeeIndex));
                return true;
            }
        }
        return false;
    }

    /**
     * Removes an employee from a specified managers department
     * @param employeeIndex Index of employee from the managers dept list
     * @param managerIndex Index of manager from the employee API list
     * @return If the employee has been removed or not
     */

    public boolean removeEmployeeFromDepartment(int employeeIndex, int managerIndex) {
        if (Utilities.validIndex(managerIndex, employees)) {
            if (employees.get(managerIndex) instanceof Manager) {
                Manager manager = ((Manager) employees.get(managerIndex));
                if (Utilities.validIndex(employeeIndex, manager.getDept())) {
                    manager.removeEmployee(employeeIndex);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Lists all employees of a manager if it exists and if the manager has employees
     *
     * @param manager Takes in a manager object
     * @return A list of the managers employees
     */
    public String listManagerEmployees(Manager manager) {
        boolean match = false;
        for (Employee employee : employees) {
            if (employee.equals(manager)) {
                match = true;
            }
        }
        if (match) {
            return manager.listEmployees();
        } else {
            return "No Manager";
        }
    }

    /**
     * Lists all employees that are managers
     *
     * @return String with all managers
     */
    public String listManagerEmployees() {
        String managers = "";
        for (Employee employee : employees) {
            if (employee instanceof Manager) { //If the given employee is a manager, adds their toString to the string
                managers += employee + "\n";
            }
        }
        if (!managers.equals("")) {
            return managers;
        } else {
            return "No Managers";
        }
    }

    /**
     * Lists all managers in the system's names
     *
     * @return String containing managers names
     */
    public String listManagerNames() {
        String managers = "";
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) instanceof Manager) { //If the given employee is a manager, adds their toString to the string
                managers += i +": "+ employees.get(i).getFirstName() + " " + employees.get(i).getSecondName() + "\n";
            }
        }
        if (!managers.equals("")) {
            return managers;
        } else {
            return "No Managers";
        }
    }

    /**
     * Searches for an employee and returns its index if its surname matches the surname passed in
     *
     * @param surname String to be searched
     * @return Index of employee to be found
     */
    public int searchEmployees(String surname) {
        int index = -1; // creates an int with value -1
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getSecondName().equalsIgnoreCase(surname)) {
                index = i; // changes the int index to the value if there is a match
                break; // brings the code out of the for loop since the first match is required
            }
        }
        return index;
    }

    /**
     * Adds up all the employees salaries into an int
     *
     * @return Total salaries of all employees
     */
    public int totalSalariesOwed() {
        int total = 0;
        for (Employee employee : employees) {
            total += employee.calculateSalary(); // Sums up the total of every salary into a int variable
        }
        return total;
    }

    /**
     * Divides the total salaries owed by the number of employees to find the average
     *
     * @return Average salary owed
     */
    public double averageSalaryOwed() {
        return totalSalariesOwed() / (double) numberOfEmployees(); // Divides total by amount, casts denominator to a double to ensure a double is returned
    }

    /**
     * Goes through every employee and finds the employee with the highest pay
     *
     * @return The employee with the highest pay, null if no employees exist
     */
    public Employee employeeWithHighestPay() {
        if (!employees.isEmpty()) {
            Employee testEmployee = new Manager("John", "Smith", "1234567XX", 1);
            for (Employee employee : employees) {
                if (testEmployee.calculateSalary() < employee.calculateSalary()) {
                    testEmployee = employee;
                }
            }
            return testEmployee;
        } else {
            return null;
        }
    }

    /**
     * Checks if an employee is contained in the employees arraylist
     *
     * @param employee An employee to be checked
     * @return True if employee is contained, false if otherwise
     */
    public boolean employeeContained(Employee employee) {
        boolean contains = false;
        for (Employee employee1 : employees) {
            if (employee1.equals(employee)) {
                contains = true;
            }
        }
        return contains;
    }

    /**
     * This method creates a blank arraylist and goes through the employees arraylist
     * it picks out the employee with the lowest second name in the alphabet and adds it to the new
     * arraylist, It repeats this until there are no employees left in the employees arraylist
     * It then replaces the employees arraylist with the sorted Arraylist
     */
    public void sortEmployeeByFirstName() {
        List<Employee> sortedEmployees = new ArrayList<>(); // a blank arraylist is created
        while (!employees.isEmpty()) {
            Employee testEmployee = employees.get(0);
            for (Employee employee : employees) {
                if (testEmployee.getFirstName().compareToIgnoreCase(employee.getFirstName()) > 0) {
                    testEmployee = employee;
                }       //While the employees arraylist still has objects, this for loop, finds the lowest alphabetical first name and moves it to a separate arraylist
            }
            sortedEmployees.add(testEmployee);
            employees.remove(testEmployee);
        }
        employees = sortedEmployees; //The sorted arraylist is now put into the original arraylist
    }

    /**
     * This method creates a blank arraylist and goes through the employees arraylist
     * it picks out the employee with the lowest first name in the alphabet and adds it to the new
     * arraylist, It repeats this until there are no employees left in the employees arraylist
     * It then replaces the employees arraylist with the sorted Arraylist
     */
    public void sortEmployeeBySecondName() {
        List<Employee> sortedEmployees = new ArrayList<>(); // a blank arraylist is created
        while (!employees.isEmpty()) {
            Employee testEmployee = employees.get(0);
            for (Employee employee : employees) {
                if (testEmployee.getSecondName().compareToIgnoreCase(employee.getSecondName()) > 0) {
                    testEmployee = employee;
                }       //While the employees arraylist still has objects, this for loop, finds the lowest alphabetical second name and moves it to a separate arraylist
            }
            sortedEmployees.add(testEmployee);
            employees.remove(testEmployee);
        }
        employees = sortedEmployees; //The sorted arraylist is now put into the original arraylist
    }

    /**
     * Sorts all employees by going through the entire employee arraylist, Finds the employee with the
     * highest firstname and swaps them to the top, it repeats this until the list is sorted
     */
    public void sortEmployeesByFirstNameAscending() {
        for (int i = employees.size() - 1; i >= 0; i--) {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++) {
                if (employees.get(j).getFirstName().compareTo(employees.get(highestIndex).getFirstName()) > 0) {
                    highestIndex = j;
                }
            }
            swapEmployees(employees, i, highestIndex);
        }
    }

    /**
     * Sorts all employees by going through the entire employee arraylist, Finds the employee with the
     * highest surname and swaps them to the top, it repeats this until the list is sorted
     */
    public void sortEmployeesBySecondNameAscending() {
        for (int i = employees.size() - 1; i >= 0; i--) {
            int highestIndex = 0;
            for (int j = 0; j <= i; j++) {
                if (employees.get(j).getSecondName().compareTo(employees.get(highestIndex).getSecondName()) > 0) {
                    highestIndex = j;
                }
            }
            swapEmployees(employees, i, highestIndex);
        }
    }

    /**
     * Takes in a list and swaps the employee at position i for position j
     *
     * @param employees List for items to be swapped in
     * @param i         Smaller item to be swapped
     * @param j         Bigger item to be swapped
     */
    private void swapEmployees(List<Employee> employees, int i, int j) {
        Employee smaller = employees.get(i);
        Employee bigger = employees.get(j);

        employees.set(i, bigger);
        employees.set(j, smaller);
    }

    /**
     * Loads data from an xml which is persistent data
     *
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("employees.xml"));
        employees = (ArrayList<Employee>) is.readObject();
        is.close();
    }

    /**
     * Saves data to an xml file which is persistent storage
     *
     * @throws Exception
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("employees.xml"));
        out.writeObject(employees);
        out.close();
    }


}
