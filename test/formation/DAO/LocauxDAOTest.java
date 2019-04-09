/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.DAO;

import formation.Locaux;
import java.sql.Connection;
import java.util.ArrayList;
import myconnections.DBConnection;
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
    
    Locaux instance;
    static LocauxDAO locauxDAO;
    
    public LocauxDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.out.println("connection invalide");
            System.exit(0);
        }
        
     System.out.println("connexion établie");
      locauxDAO = new LocauxDAO();
      locauxDAO.setConnection(dbConnect);

    }
    
    @AfterClass
    public static void tearDownClass() {
        DBConnection.closeConnection();
    }
    
    @Before
    public void setUp() {
        instance = new Locaux(1, "Sigle", 1, "description");
       try{
         instance= locauxDAO.create(instance);
          
       }
       catch(Exception e){
        
       }
    }
    
    @After
    public void tearDown() {
        try{
        locauxDAO.delete(instance);
        }
        catch(Exception e){}
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
        String nom = instance.getSigle();
        instance = locauxDAO.read((instance.getIdLocal()));
        assertEquals(nom,instance.getSigle());
    }

    /**
     * Test of create method, of class LocauxDAO.
     */
    @Test
    public void testCreate() throws Exception {
        int id=instance.getIdLocal();
        Locaux localLu = locauxDAO.read(id);
        assertEquals(instance.getIdLocal(),localLu.getIdLocal());
       
        try{
           Locaux instance2 = new Locaux(id, "Sigle", 14, "des");
           locauxDAO.create(instance2);
           locauxDAO.delete(instance2);
           fail("création d'un doublon");
        }
        catch(Exception e){}
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
