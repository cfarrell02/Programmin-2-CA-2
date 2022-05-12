package Main;

import controllers.EmployeeAPI;
import models.*;
import utils.ScannerInput;
import utils.Utilities;

public class Driver {
    private final EmployeeAPI empAPI;
    private final School school;

    public static void main(String[] args) {
        Driver app = new Driver();
        app.run();
    }

    private Driver() {
        empAPI = new EmployeeAPI();
        school = new School("Science and Computing"); //setting up school as our own!
    }

    private int mainMenu() {
        System.out.println("College Manager Menu");
        System.out.println("---------");
        System.out.println("  1) Add an employee (Manager)");
        System.out.println("  2) Add an employee (Lecturer)");
        System.out.println("  3) Add an employee (Admin Worker)");
        System.out.println("  4) Add an existing employee to a department");
        System.out.println("  5) Remove an employee from a department");
        System.out.println("  6) Delete an employee");
        System.out.println("  7) Update an employees paygrade");
        System.out.println("---------");
        System.out.println("  8) Add a department to a school");
        System.out.println("  9) Delete a department from a school");
        System.out.println("  10) Update a department's manager");
        System.out.println("  11) Find the name of the manager of a given department");
        System.out.println("  12) List all the employees of a given department");
        System.out.println("---------");
        System.out.println("  13) Find the salary of a given employee");
        System.out.println("  14) Find the total of the salaries owed to all the employees");
        System.out.println("  15) Find the average of the salaries ");
        System.out.println("  16) Print the employee with the highest pay");
        System.out.println("---------");
        System.out.println("  17) List all employees in the company in ascending alphabetical order (first name)");
        System.out.println("  18) List all employees in the company in ascending alphabetical order (second name)");
        System.out.println("  19) List all employees in the company in ascending alphabetical order (first name)(alternative)");
        System.out.println("  20) List all employees in the company in ascending alphabetical order (second name)(alternative)");
        System.out.println("---------");
        System.out.println("  21) Search the system for an employees by second name ");
        System.out.println("  22) Search the system for an employee within a given manager's department ");
        System.out.println("---------");
        System.out.println("  23) Save to XML ");
        System.out.println("  24) Load from XML ");
        System.out.println("---------");
        System.out.println("  0) Exit");
        return ScannerInput.readNextInt("==>>");
    }

    private void addManager() {
        String firstname = ScannerInput.readNextLine("Enter In The First Name Of The Manager: ");
        String surname = ScannerInput.readNextLine("Enter In The Last Name Of The Manager: ");
        String PPS = ScannerInput.readNextLine("Enter In The PPS Number Of The Manager: ");
        if (!Utilities.validPPS(PPS)) {
            System.err.println("\nInvalid PPS Entered, Defaulting to \"0000000XX\"");
        }
        int grade = ScannerInput.readNextInt("Enter In The Grade Of The Manager: ");
        empAPI.addEmployee(new Manager(firstname, surname, PPS, grade));
        System.out.println("Worker Added");
    }

    private void addLecturer() {
        String firstname = ScannerInput.readNextLine("Enter In The First Name Of The Lecturer: ");
        String surname = ScannerInput.readNextLine("Enter In The Last Name Of The Lecturer: ");
        String PPS = ScannerInput.readNextLine("Enter In The PPS Number Of The Lecturer: ");
        if (!Utilities.validPPS(PPS)) {
            System.err.println("\nInvalid PPS Entered, Defaulting to \"0000000XX\"");
        }
        int level = ScannerInput.readNextInt("Enter In The Level Of The Lecturer: ");
        empAPI.addEmployee(new Lecturer(firstname, surname, PPS, level));
        System.out.println("Worker Added");
    }

    private void addAdminWorker() {
        String firstname = ScannerInput.readNextLine("Enter In The First Name Of The Admin Worker: ");
        String surname = ScannerInput.readNextLine("Enter In The Last Name Of The Admin Worker: ");
        String PPS = ScannerInput.readNextLine("Enter In The PPS Number Of The Admin Worker: ");
        if (!Utilities.validPPS(PPS)) {
            System.err.println("\nInvalid PPS Entered, Defaulting to \"0000000XX\"");
        }
        int grade = ScannerInput.readNextInt("Enter In The Grade Of The Admin Worker: ");
        empAPI.addEmployee(new AdminWorker(firstname, surname, PPS, grade));
        System.out.println("Worker Added");
    }

    private void addWorkerToDept() {
        System.out.println(empAPI.listNonManagers());
        int index1 = ScannerInput.readNextInt("Enter index of the employee you would like to add: ");
        System.out.println(empAPI.listManagerNames());
        int index2 = ScannerInput.readNextInt("Enter the index of the manager whose department you'd like to add to: ");
        if (empAPI.addEmployeeToDepartment(index1, index2)) {
            System.out.println("Worker Added to department");
        } else {
            System.err.println("Error, Worker Not Added");
        }
    }

    private void removeWorkerFromDept() {
        System.out.println(empAPI.listManagerNames());
        int managerIndex = ScannerInput.readNextInt("Enter the index of the manager whose department you'd like to remove from: ");
        System.out.println(((Manager) empAPI.getEmployee(managerIndex)).listEmployees());
        int employeeIndex = ScannerInput.readNextInt("Enter index of the employee you would like to remove: ");
        if (empAPI.removeEmployeeFromDepartment(employeeIndex, managerIndex)) {
            System.out.println("Worker removed from department");
        } else {
            System.err.println("Error, Worker not removed");
        }
    }

    private void updateEmployee() {
        System.out.println(empAPI.listEmployeeNames());
        int index = ScannerInput.readNextInt(
                "Enter index of employee whose salary you would like to increase: ");
        if (Utilities.validIndex(index, empAPI.getEmployees())) {
            if (empAPI.getEmployee(index) instanceof Manager) {
                Manager manager = (Manager) empAPI.getEmployee(index);
                System.out.println("Current grade of this manager: " + manager.getGrade());
                manager.setGrade(ScannerInput.readNextInt(
                        "Enter new grade of manager: "));
            } else if (empAPI.getEmployee(index) instanceof Lecturer) {
                Lecturer lecturer = (Lecturer) empAPI.getEmployee(index);
                System.out.println("Current level of this lecturer: " + lecturer.getLevel());
                lecturer.setLevel(ScannerInput.readNextInt(
                        "Enter new level of lecturer: "));
            } else {
                AdminWorker adminWorker = (AdminWorker) empAPI.getEmployee(index);
                System.out.println("Current level of this Admin Worker: " +
                        adminWorker.getGrade());
                adminWorker.setGrade(ScannerInput.readNextInt(
                        "Enter new grade of Admin Worker: "));

            }
        } else {
            System.err.println("Employee could not be found");
        }
    }

    private void updateDepartment() {
        System.out.println(school.listDepartments());
        String name = ScannerInput.readNextLine("Enter name of department to be updated: ");
        System.out.println(empAPI.listManagerEmployees());
        int index = ScannerInput.readNextInt("Enter the index of the new manager: ");
        Manager manager = (Manager) empAPI.getEmployee(index);
        if (school.replaceManager(name, manager)) {
            System.out.println("Manager Updated");
        } else {
            System.err.println("Manager could not be updated");
        }

    }

    private void deleteEmployee() {
        System.out.println(empAPI.listEmployeeNames());
        int index = ScannerInput.readNextInt(
                "Enter index of employee you would like to delete");
        if (Utilities.validIndex(index, empAPI.getEmployees())) {
            empAPI.removeEmployee(index);
            System.out.println("Employee deleted");
        } else {
            System.err.println("Employee could not be found");
        }

    }

    private void addDepartment() {
        String name = ScannerInput.readNextLine("Enter name of the department: ");
        System.out.println(empAPI.listManagerEmployees());
        int index = ScannerInput.readNextInt("Enter surname of manager to manage the department: ");
        if (school.addDept(name, (Manager) empAPI.getEmployee(index))) {
            System.out.println("Department Added");
        } else {
            System.err.println("Error, Department Not Added");
        }

    }

    private void deleteDepartment() {
        System.out.println(school.listDepartments());
        String name = ScannerInput.readNextLine("Enter name of department to be deleted: ");
        if (school.deleteDept(name) != null) {
            System.out.println("Department Deleted");
        } else {
            System.out.println("Invalid Department");
        }
    }

    private void findManagerName() {
        System.out.println(school.listDepartments());
        String name = ScannerInput.readNextLine("Enter name of department to find its manager: ");
        if (school.getDepartments().containsKey(name)) {
            System.out.println(school.displayManagerName(name));
        } else {
            System.err.println("Department Not Found");
        }
    }

    private void listEmployeesOfDept() {
        System.out.println(school.listDepartments());
        String name = ScannerInput.readNextLine("Enter name of department to see its employees: ");
        if (school.getDepartments().containsKey(name)) {
            System.out.println(school.displayAllEmployeesFromDept(name));
        } else {
            System.err.println("Department Not Found");
        }
    }

    private void findSalary() {
        System.out.println(empAPI.listEmployeeNames());
        int index = ScannerInput.readNextInt("Enter index of employee whose salary you'd like to see: ");
        if (Utilities.validIndex(index, empAPI.getEmployees())) {
            Employee employee = empAPI.getEmployee(index);
            System.out.println(employee.calculateSalary());
        } else {
            System.err.println("No Employee Found");
        }
    }

    private void findTotalSalaries() {
        System.out.println("Total Salary Owed: " + empAPI.totalSalariesOwed());
    }

    private void findAverageSalaries() {
        System.out.println("Average Salary Owed: " + empAPI.averageSalaryOwed());
    }

    private void highestPaidWorker() {
        Employee highestPaid = empAPI.employeeWithHighestPay();
        System.out.println("Highest paid employee: " + highestPaid.getFirstName()
                + " " + highestPaid.getSecondName());
    }

    private void listEmployeesOrderedByFirstName() {
        empAPI.sortEmployeeByFirstName();
        System.out.println(empAPI.listEmployeeNames());
    }

    private void listEmployeesOrderedByLastName() {
        empAPI.sortEmployeeBySecondName();
        System.out.println(empAPI.listEmployeeNames());
    }

    private void listEmployeesOrderedByFirstNameLegacy() {
        empAPI.sortEmployeesByFirstNameAscending();
        System.out.println(empAPI.listEmployeeNames());
    }

    private void listEmployeesOrderedByLastNameLegacy() {
        empAPI.sortEmployeesBySecondNameAscending();
        System.out.println(empAPI.listEmployeeNames());
    }

    private void searchEmployees() {
        String name = ScannerInput.readNextLine("Enter surname of employee to find: ");
        int index = empAPI.searchEmployees(name);
        System.out.println(empAPI.getEmployee(index));
    }

    private void searchEmployeeWithinADepartment() {
        System.out.println(school.listDepartments());
        String departmentName = ScannerInput.readNextLine("Enter name of a department to search: ");
        System.out.println(school.displayAllEmployeesFromDept(departmentName));
        String employeeName = ScannerInput.readNextLine("Enter surname of " +
                "employee to search for: ");
        int index = school.getManager(departmentName).searchEmployees(employeeName);
        System.out.println(school.getManager(departmentName).getDept().get(index));
    }

    private void save() throws Exception {
        empAPI.save();
        school.save();
        System.out.println("Saving...");
    }

    private void load() throws Exception {
        empAPI.load();
        school.load();
    }

    private void run() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1:
                    addManager();
                    break;
                case 2:
                    addLecturer();
                    break;
                case 3:
                    addAdminWorker();
                    break;
                case 4:
                    addWorkerToDept();
                    break;
                case 5:
                    removeWorkerFromDept();
                    break;
                case 6:
                    deleteEmployee();
                    break;
                case 7:
                    updateEmployee();
                    break;
                case 8:
                    addDepartment();
                    break;
                case 9:
                    deleteDepartment();
                    break;
                case 10:
                    updateDepartment();
                    break;
                case 11:
                    findManagerName();
                    break;
                case 12:
                    listEmployeesOfDept();
                    break;
                case 13:
                    findSalary();
                    break;
                case 14:
                    findTotalSalaries();
                    break;
                case 15:
                    findAverageSalaries();
                    break;
                case 16:
                    highestPaidWorker();
                    break;
                case 17:
                    listEmployeesOrderedByFirstName();
                    break;
                case 18:
                    listEmployeesOrderedByLastName();
                    break;
                case 19:
                    listEmployeesOrderedByFirstNameLegacy();
                    break;
                case 20:
                    listEmployeesOrderedByLastNameLegacy();
                    break;
                case 21:
                    searchEmployees();
                    break;
                case 22:
                    searchEmployeeWithinADepartment();
                    break;
                case 23:
                    try {
                        save();
                    } catch (Exception e) {
                        System.err.println("Error writing to file: " + e);
                    }
                    break;
                case 24:
                    try {
                        load();
                    } catch (Exception e) {
                        System.err.println("Error loading from file: " + e);
                    }
                    break;
                default:
                    System.err.println("Invalid option entered: " + option);
                    break;

            }
            ScannerInput.readNextLine("\nPress any key to continue...");
            option = mainMenu();

        }
        System.out.println("Exiting... Goodbye!");
    }

}
