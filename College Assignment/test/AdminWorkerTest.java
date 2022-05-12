import static org.junit.Assert.*;

import models.AdminWorker;
import org.junit.Before;
import org.junit.Test;



public class AdminWorkerTest {
    private AdminWorker adminWorkerValid,adminWorkerInvalid;

    @Before
    public void setUp() throws Exception {
        adminWorkerValid = new AdminWorker("Selly", "Selly", "1234567xx", 2);
        adminWorkerInvalid = new AdminWorker("Mad", "Men", "xx34567qq", 8);
    }

    @Test
    public void testConstructor() {
        assertEquals(2, adminWorkerValid.getGrade());
        assertEquals(1, adminWorkerInvalid.getGrade());
    }

    @Test
    public void setGrade() {
        assertEquals(2, adminWorkerValid.getGrade());
        adminWorkerValid.setGrade(1);
        assertEquals(1, adminWorkerValid.getGrade());
        adminWorkerValid.setGrade(0);
        assertEquals(1, adminWorkerValid.getGrade());
        adminWorkerValid.setGrade(3);
        assertEquals(3, adminWorkerValid.getGrade());
        adminWorkerValid.setGrade(4);
        assertEquals(4, adminWorkerValid.getGrade());
        adminWorkerValid.setGrade(8);
        assertEquals(4, adminWorkerValid.getGrade(), 0.01);
    }

    @Test
    public void calculateSalary() {
        assertEquals(1600, adminWorkerValid.calculateSalary(), 0.01);
        assertEquals(900, adminWorkerInvalid.calculateSalary(), 0.01);
    }

    @Test
    public void testEquals() {
        assertTrue(adminWorkerValid.equals(adminWorkerValid));
        AdminWorker otherAdminWorker = new AdminWorker("Selly", "Selly", "1234567xx", 2);
        assertTrue(adminWorkerValid.equals(otherAdminWorker));
        otherAdminWorker.setGrade(1);
        assertFalse(adminWorkerValid.equals(otherAdminWorker));
    }

    @Test
    public void testToString() {
        assertTrue(adminWorkerValid.toString().contains("Selly"));
        assertTrue(adminWorkerValid.toString().contains("1234567xx"));
        assertTrue(adminWorkerValid.toString().contains("2"));

    }
}

