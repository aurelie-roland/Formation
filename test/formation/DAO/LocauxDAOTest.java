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
     * Test of read method, of class LocauxDAO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("READ : ");
        int idLocal;
        LocauxDAO locauxDAO = new LocauxDAO();
        locauxDAO.setConnection(dbConnect);
        Locaux obj = new Locaux(1, "Sigle read", 1, "description",0);
        Locaux expResult = locauxDAO.create(obj);
        idLocal = expResult.getIdLocal();
        Locaux result = locauxDAO.read(idLocal);
        assertEquals("Sigle différents",expResult.getSigle(), result.getSigle());
        assertEquals("ID différents",expResult.getIdLocal(), result.getIdLocal());
        assertEquals("Nombres de places différents",expResult.getPlaces(), result.getPlaces());
        assertEquals("Description différents",expResult.getDescription(),result.getDescription());
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
        Locaux obj = new Locaux(10, "Sigle create 1", 1, "description",0);
        LocauxDAO instance = new LocauxDAO();
        instance.setConnection(dbConnect);
        Locaux expResult = new Locaux(10, "Sigle create 2", 1, "description",0);
        Locaux result = instance.create(obj);
        assertEquals("Nombres de places différents",expResult.getPlaces(), result.getPlaces());
        assertEquals("Description différents",expResult.getDescription(),result.getDescription());
        instance.delete(result);
    }

    /**
     * Test of update method, of class LocauxDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("UPDATE");
        Locaux obj = new Locaux(2, "Sigle update", 2, "description 2",0);
        LocauxDAO instance = new LocauxDAO();
        instance.setConnection(dbConnect);
        obj = instance.create(obj);
        obj.setIdLocal(obj.getIdLocal());
        obj.setSigle("Sigle update 2");
        obj.setPlaces(2);
        obj.setDescription("description 2");
        Locaux expResult=obj;
        Locaux result = instance.update(obj);
        assertEquals(expResult.getIdLocal(), result.getIdLocal());
        assertEquals(expResult.getSigle(), result.getSigle());
        assertEquals(expResult.getPlaces(), result.getPlaces());
        assertEquals(expResult.getDescription(), result.getDescription());
        instance.delete(obj);
    }

    /**
     * Test of delete method, of class LocauxDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("DELETE");
        Locaux obj = new Locaux(1, "Sigle delete", 1, "description",0);
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
        Locaux obj1 = new Locaux(1, "Sigle description 1", 1, "description",0);
        Locaux obj2 = new Locaux(1, "Sigle description 2", 1, "description",0);
        LocauxDAO instance = new LocauxDAO();
        instance.setConnection(dbConnect);
        obj1=instance.create(obj1);
        obj2=instance.create(obj2);
        List<Locaux> result = instance.rechDesc("description");
        if(result.size() != 2){
            fail("record introuvable");
        }
        instance.delete(obj1);
        instance.delete(obj2);
    }
    
}