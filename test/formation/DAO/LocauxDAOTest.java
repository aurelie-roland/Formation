/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.DAO;

import formation.Locaux;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aurelie Roland
 */
public class LocauxDAOTest {
    
    public LocauxDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of menu method, of class LocauxDAO.
     */
    @Test
    public void testMenu() throws Exception {
        System.out.println("menu");
        LocauxDAO instance = new LocauxDAO();
        instance.menu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class LocauxDAO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        int id = 1;
        LocauxDAO instance = new LocauxDAO();
        LocauxDAO inst =  new LocauxDAO();
        Locaux expResult = inst.read(1);
        Locaux result = instance.read(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of create method, of class LocauxDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Locaux obj = null;
        LocauxDAO instance = new LocauxDAO();
        Locaux expResult = null;
        Locaux result = instance.create(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class LocauxDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Locaux obj = null;
        LocauxDAO instance = new LocauxDAO();
        Locaux expResult = null;
        Locaux result = instance.update(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class LocauxDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Locaux obj = null;
        LocauxDAO instance = new LocauxDAO();
        instance.delete(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rechNom method, of class LocauxDAO.
     */
    @Test
    public void testRechNom() throws Exception {
        System.out.println("rechNom");
        String nomrech = "";
        LocauxDAO instance = new LocauxDAO();
        ArrayList<Locaux> expResult = null;
        ArrayList<Locaux> result = instance.rechNom(nomrech);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
