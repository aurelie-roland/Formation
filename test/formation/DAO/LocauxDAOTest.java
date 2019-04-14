/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.DAO;

import formation.Locaux;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
        
    static Connection dbConnect;
    
    public LocauxDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.out.println("connection invalide");
            System.exit(1);
        }

    }
    
    @AfterClass
    public static void tearDownClass() {
        DBConnection.closeConnection();
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
        System.out.println("READ : ");
        int idLocal = 0;
        LocauxDAO locauxDAO = new LocauxDAO();
        locauxDAO.setConnection(dbConnect);
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
        instance.setConnection(dbConnect);
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
        instance.setConnection(dbConnect);
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
        Locaux obj1 = new Locaux(1, "Sigle", 1, "description");
        Locaux obj2 = new Locaux(1, "Sigle 2 ", 1, "description 2");
        String nomrech = "TestNom";
        LocauxDAO instance = new LocauxDAO();
        instance.setConnection(dbConnect);
        obj1=instance.create(obj1);
        obj2=instance.create(obj2);
        List<Locaux> result = instance.rechNom(nomrech);
        if(result.indexOf(obj1)<0) fail("record introuvable "+obj1);
        if(result.indexOf(obj2)<0) fail("record introuvable"+obj2);
        instance.delete(obj1);
        instance.delete(obj2);
    }
    
}