import controllers.EmployeeAPI;
import models.AdminWorker;
import models.Lecturer;
import models.Manager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeAPITest {
    private EmployeeAPI empAPI, emptyAPI;
    private Manager alan, orla, empty;
    private AdminWorker fiona, mary;
    private Lecturer mairead, siobhan, claire;

    @Before
    public void setUp() throws Exception {

        alan = new Manager("Alan", "Davy", "1234567aa", 3);    // salary 2168
        orla = new Manager("Orla", "Donovan", "2222222aa", 4);  //salary  2809
        empty = new Manager("Cian", "Farrell", "9999999aa", 4);  //salary  2800
        fiona = new AdminWorker("Fiona", "Power", "3333333aa", 6 );  // salary 900
        mary = new AdminWorker("Mary", "Ryan", "4444444aa", 5);   // salary 900
        mairead = new Lecturer("Mairead", "Meagher", "5555555ss", 2);  //salary 2000
        siobhan = new Lecturer("Siobhan", "Drohan", "1111111xx", 3); // salary 3000
        claire = new Lecturer("Claire", "Keary", "7777777ss", 2);   // salary 2000
        empAPI = new EmployeeAPI();
        emptyAPI = new EmployeeAPI();
        empAPI.addEmployee(alan);
        empAPI.addEmployee(orla);
        empAPI.addEmployee(fiona);
        empAPI.addEmployee(mary);
        empAPI.addEmployee(mairead);
        empAPI.addEmployee(empty);


        alan.addDeptEmployee(fiona);
        alan.addDeptEmployee(mary);
        alan.addDeptEmployee(mairead);
        alan.addDeptEmployee(siobhan);

        orla.addDeptEmployee(fiona);

    }


    @After
    public void tearDown() {
        alan = orla = null;
        fiona = mary = null;
        mairead = siobhan = claire= null;
    }

    @Test
    public void testConstructors() {
        assertEquals(6, empAPI.getEmployees().size());
        assertEquals("Alan", empAPI.getEmployees().get(0).getFirstName());
        assertEquals(4, ((Manager) empAPI.getEmployees().get(0)).getDept().size());
        assertEquals("Orla", empAPI.getEmployees().get(1).getFirstName());
        assertEquals("Fiona", empAPI.getEmployees().get(2).getFirstName());
        assertEquals("Mary", empAPI.getEmployees().get(3).getFirstName());

        assertEquals("Donovan", empAPI.getEmployees().get(1).getSecondName());
        assertEquals("Power", empAPI.getEmployees().get(2).getSecondName());
        assertEquals("Ryan", empAPI.getEmployees().get(3).getSecondName());
    }


    @Test
    public void searchEmployees() {
      assertEquals(0, empAPI.searchEmployees("Davy"));
      assertEquals(1, empAPI.searchEmployees("Donovan"));
      assertEquals(2, empAPI.searchEmployees("Power"));
      assertEquals(3, empAPI.searchEmployees("Ryan"));
      assertEquals(4 ,empAPI.searchEmployees("Meagher"));
      assertEquals(-1 ,empAPI.searchEmployees("Trump"));

    }


    @Test
    public void listEmployees() {
        assertTrue(empAPI.listEmployees().contains("Alan"));
        assertTrue(empAPI.listEmployees().contains("Davy"));
        assertTrue(empAPI.listEmployees().contains("1234567aa"));
        assertTrue(empAPI.listEmployees().contains("Orla"));
        assertTrue(empAPI.listEmployees().contains("Donovan"));
        assertTrue(empAPI.listEmployees().contains("Fiona"));


        assertTrue(empAPI.listEmployees().contains("Mary"));
        assertTrue(empAPI.listEmployees().contains("Mairead"));

        EmployeeAPI emptyList = new EmployeeAPI();
        assertTrue(emptyList.listEmployees().toLowerCase().contains("no employees"));

    }

    @Test
    public void testGetEmployee(){
        assertEquals("Alan", empAPI.getEmployee(0).getFirstName());
        assertEquals("Donovan", empAPI.getEmployee(1).getSecondName());
        assertEquals("Fiona", empAPI.getEmployee(2).getFirstName());
        assertEquals("Ryan", empAPI.getEmployee(3).getSecondName());
        assertNull(emptyAPI.getEmployee(0));
    }

    @Test
    public void testAddEmployees(){
        empAPI.addEmployee(siobhan);
        empAPI.addEmployee(claire);
        assertEquals("Drohan", empAPI.getEmployee(6).getSecondName());
        assertEquals("Keary", empAPI.getEmployee(7).getSecondName());
    }
    @Test
    public void testRemoveEmployee(){
        assertNull(empAPI.removeEmployee("hahaha"));
        assertEquals(alan, empAPI.removeEmployee("Davy"));
        assertEquals(orla, empAPI.removeEmployee("Donovan"));
        assertEquals(fiona, empAPI.getEmployee(0));
        assertEquals(mary, empAPI.getEmployee(1));
    }
    @Test
    public void testNumberOfEmployees(){
        assertEquals(6,empAPI.numberOfEmployees());
        empAPI.removeEmployee("Donovan");
        empAPI.removeEmployee("Davy");
        assertEquals(4,empAPI.numberOfEmployees());
    }
    @Test
    public void testAddEmployeeToDepartment(){
        assertTrue(empAPI.addEmployeeToDepartment(2,0));
        assertTrue(alan.getDept().contains(fiona));
        assertTrue(empAPI.addEmployeeToDepartment(3,1));
        assertTrue(orla.getDept().contains(mary));
        assertFalse(emptyAPI.addEmployeeToDepartment(0,1));
        assertFalse(empAPI.addEmployeeToDepartment(0,3));
    }
    @Test
    public void testRemoveEmployeeFromDepartment(){
        assertTrue(alan.getDept().contains(fiona));
        assertTrue(empAPI.removeEmployeeFromDepartment(0,0));
        assertFalse(alan.getDept().contains(fiona));
        assertFalse(empAPI.removeEmployeeFromDepartment(5,0));
        assertFalse(empAPI.removeEmployeeFromDepartment(3,2));
    }
    @Test
    public void testListManagerEmployees1(){
        empAPI.addEmployeeToDepartment(4,0);
        empAPI.addEmployeeToDepartment(3,0);
        assertTrue(empAPI.listManagerEmployees(((Manager) empAPI.getEmployee(0))).contains("Mairead"));
        assertTrue(empAPI.listManagerEmployees(((Manager) empAPI.getEmployee(0))).contains("Mary"));
        assertTrue(empAPI.listManagerEmployees(((Manager) empAPI.getEmployee(5)))
                .contains("Manager has no employees in their department"));
    }
    @Test
    public void testListManagerEmployees2(){
        assertTrue(empAPI.listManagerEmployees().contains("Alan"));
        assertTrue(empAPI.listManagerEmployees().contains("Orla"));
        assertTrue(empAPI.listManagerEmployees().contains("1234567aa"));
        assertTrue(emptyAPI.listManagerEmployees().contains("No Managers"));
    }
    @Test
    public void testListEmployeeName(){
        assertTrue(empAPI.listEmployeeNames().contains("Alan"));
        assertTrue(empAPI.listEmployeeNames().contains("Mary"));
        assertTrue(empAPI.listEmployeeNames().contains("Fiona"));
        assertTrue(emptyAPI.listEmployeeNames().contains("No Employees Registered"));
        assertFalse(emptyAPI.listEmployeeNames().contains("Alan"));
    }
    @Test
    public void testListManagerNames(){
        assertTrue(empAPI.listManagerNames().contains("Alan"));
        assertTrue(empAPI.listManagerNames().contains("Orla"));
        assertFalse(empAPI.listManagerNames().contains("1234567aa"));
        assertTrue(emptyAPI.listManagerNames().contains("No Managers"));
    }
    @Test
    public void testListNonManagers(){
        assertTrue(empAPI.listNonManagers().contains("Fiona"));
        assertFalse(empAPI.listNonManagers().contains("Alan"));
        assertTrue(empAPI.listNonManagers().contains("Mary"));
        assertFalse(empAPI.listNonManagers().contains("Orla"));
    }
    @Test
    public void testSearchEmployee(){
        assertEquals(0,empAPI.searchEmployees("Davy"));
        assertEquals(4,empAPI.searchEmployees("Meagher"));
        assertEquals(-1,empAPI.searchEmployees("Drohan"));
        assertEquals(-1,emptyAPI.searchEmployees("Hello!"));
    }
    @Test
    public void testTotalSalariesOwed(){
        assertEquals(11577,empAPI.totalSalariesOwed(),.01);
        empAPI.removeEmployee("Meagher");
        assertEquals(9577,empAPI.totalSalariesOwed(),.01);
    }

    @Test
    public void testAverageSalaryOwed(){
        assertEquals(1929.5,empAPI.averageSalaryOwed(),.01);
        empAPI.removeEmployee("Meagher");
        assertEquals(1915.4,empAPI.averageSalaryOwed(),.01);
    }
    @Test
    public void testEmployeeWithHighestPay(){
        assertEquals(orla,empAPI.employeeWithHighestPay());
        empAPI.removeEmployee("Donovan");
        assertEquals(empty,empAPI.employeeWithHighestPay());
        assertNull(emptyAPI.employeeWithHighestPay());
    }
    @Test
    public void testEmployeeContained(){
        assertTrue(empAPI.employeeContained(orla));
        assertFalse(empAPI.employeeContained(siobhan));
        empAPI.removeEmployee("Donovan");
        assertFalse(empAPI.employeeContained(orla));
    }
    @Test
    public void testSortEmployeeByFirstName(){
        assertTrue(empAPI.getEmployee(0).equals(alan));
        assertTrue(empAPI.getEmployee(1).equals(orla));
        assertTrue(empAPI.getEmployee(2).equals(fiona));
        assertTrue(empAPI.getEmployee(3).equals(mary));
        assertTrue(empAPI.getEmployee(4).equals(mairead));
        assertTrue(empAPI.getEmployee(5).equals(empty));
        empAPI.sortEmployeeByFirstName();
        assertEquals(6,empAPI.numberOfEmployees());
        assertTrue(empAPI.getEmployee(0).equals(alan));
        assertTrue(empAPI.getEmployee(1).equals(empty));
        assertTrue(empAPI.getEmployee(2).equals(fiona));
        assertTrue(empAPI.getEmployee(3).equals(mairead));
        assertTrue(empAPI.getEmployee(4).equals(mary));
        assertTrue(empAPI.getEmployee(5).equals(orla));
    }
    @Test
    public void testSortEmployeeBySecondName(){
        assertTrue(empAPI.getEmployee(0).equals(alan));
        assertTrue(empAPI.getEmployee(1).equals(orla));
        assertTrue(empAPI.getEmployee(2).equals(fiona));
        assertTrue(empAPI.getEmployee(3).equals(mary));
        assertTrue(empAPI.getEmployee(4).equals(mairead));
        assertTrue(empAPI.getEmployee(5).equals(empty));
        empAPI.sortEmployeeBySecondName();
        assertEquals(6,empAPI.numberOfEmployees());
        assertTrue(empAPI.getEmployee(0).equals(alan));
        assertTrue(empAPI.getEmployee(1).equals(orla));
        assertTrue(empAPI.getEmployee(2).equals(empty));
        assertTrue(empAPI.getEmployee(3).equals(mairead));
        assertTrue(empAPI.getEmployee(4).equals(fiona));
        assertTrue(empAPI.getEmployee(5).equals(mary));
    }
    @Test
    public void testSortEmployeeByFirstName2(){
        assertTrue(empAPI.getEmployee(0).equals(alan));
        assertTrue(empAPI.getEmployee(1).equals(orla));
        assertTrue(empAPI.getEmployee(2).equals(fiona));
        assertTrue(empAPI.getEmployee(3).equals(mary));
        assertTrue(empAPI.getEmployee(4).equals(mairead));
        assertTrue(empAPI.getEmployee(5).equals(empty));
        empAPI.sortEmployeesByFirstNameAscending();
        assertEquals(6,empAPI.numberOfEmployees());
        assertTrue(empAPI.getEmployee(0).equals(alan));
        assertTrue(empAPI.getEmployee(1).equals(empty));
        assertTrue(empAPI.getEmployee(2).equals(fiona));
        assertTrue(empAPI.getEmployee(3).equals(mairead));
        assertTrue(empAPI.getEmployee(4).equals(mary));
        assertTrue(empAPI.getEmployee(5).equals(orla));
    }
    @Test
    public void testSortEmployeeBySecondName2(){
        assertTrue(empAPI.getEmployee(0).equals(alan));
        assertTrue(empAPI.getEmployee(1).equals(orla));
        assertTrue(empAPI.getEmployee(2).equals(fiona));
        assertTrue(empAPI.getEmployee(3).equals(mary));
        assertTrue(empAPI.getEmployee(4).equals(mairead));
        assertTrue(empAPI.getEmployee(5).equals(empty));
        empAPI.sortEmployeesBySecondNameAscending();
        assertEquals(6,empAPI.numberOfEmployees());
        assertTrue(empAPI.getEmployee(0).equals(alan));
        assertTrue(empAPI.getEmployee(1).equals(orla));
        assertTrue(empAPI.getEmployee(2).equals(empty));
        assertTrue(empAPI.getEmployee(3).equals(mairead));
        assertTrue(empAPI.getEmployee(4).equals(fiona));
        assertTrue(empAPI.getEmployee(5).equals(mary));
    }

}
