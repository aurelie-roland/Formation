package formation.GUI;

import java.sql.Connection;
import javax.swing.JPanel;

/**
 *
 * @author Aurelie Roland
 */
public class SupprimerSessionCours extends JPanel{
    
    Connection dbConnect;
    
    
    public  void setConnection(Connection nouvdbConnect) {
      dbConnect=nouvdbConnect;
   }
    
}
