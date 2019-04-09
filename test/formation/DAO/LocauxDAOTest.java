/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.DAO;

import formation.Locaux;
import java.sql.Connection;
import java.sql.SQLException;
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
        System.out.println("READ : ");
        int idLocal = 0;
        Locaux obj = new Locaux(1, "Sigle", 1, "description");
        Locaux expResult = locauxDAO.create(obj);
        idLocal = expResult.getIdLocal();
        Locaux result = locauxDAO.read(idLocal);
        assertEquals("Sigle différents",expResult.getSigle(), result.getSigle());
        assertEquals("ID différents",expResult.getIdLocal(), result.getIdLocal());
        assertEquals("Nombres de places différents",expResult.getPlaces(), result.getPlaces());
        assertEquals("Description différents",expResult.getDescritpion(),result.getDescritpion());
        try{
            result = locauxDAO.read(0);
            fail("exception d'id inconnu non générée");
        }
        catch(SQLException e){}
        locauxDAO.delete(result);
    }

    /**
     * Test of create method, of class LocauxDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("CREATE : ");
        Locaux obj = new Locaux(1, "Sigle", 1, "description");
        LocauxDAO instance = new LocauxDAO();
        Locaux expResult = new Locaux(1, "Sigle", 1, "description");
        Locaux result = instance.create(obj);
        assertEquals("Sigle différents",expResult.getSigle(), result.getSigle());
        assertEquals("ID différents",expResult.getIdLocal(), result.getIdLocal());
        assertEquals("Nombres de places différents",expResult.getPlaces(), result.getPlaces());
        assertEquals("Description différents",expResult.getDescritpion(),result.getDescritpion());
        int idLocal = result.getIdLocal();
        obj = new Locaux(2, "Sigle 2", 2, "description 2");
        try{
            Locaux result2 = instance.create(obj);
            fail("exception de doublon non déclenchée");
            instance.delete(result2);
        }
        catch(SQLException e){}
        instance.delete(result);
        
          obj= new Locaux(2, "Sigle 2", 2, "description 2");
        try{
            Locaux result3 = instance.create(obj);
            fail("exception de code postal non déclenchée");
            instance.delete(result3);
        }
        catch(SQLException e){}
    }

    /**
     * Test of update method, of class LocauxDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("UPDATE");
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
        System.out.println("DELETE");
        Locaux obj = new Locaux(1, "Sigle", 1, "description");
        LocauxDAO instance = new LocauxDAO();
        obj = instance.create(obj);
        instance.delete(obj);
        try {
            instance.read(obj.getIdLocal());
            fail("exception de record introuvable non générée");
        }
        catch(SQLException e){}
    }

    /**
     * Test of rechNom method, of class LocauxDAO.
     */
    @Test
    public void testRechNom() throws Exception {
        System.out.println("RECHERCHE NOM");
        String nomrech = "";
        LocauxDAO instance = new LocauxDAO();
        ArrayList<Locaux> expResult = null;
        ArrayList<Locaux> result = instance.rechNom(nomrech);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
