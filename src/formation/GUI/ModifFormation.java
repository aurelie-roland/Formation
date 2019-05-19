package formation.GUI;

import java.sql.Connection;
import javax.swing.JPanel;

/**
 *
 * @author Aurelie Roland
 */
public class ModifFormation extends JPanel{
     Connection dbConnect;
    
    
    public  void setConnection(Connection nouvdbConnect) {
      dbConnect=nouvdbConnect;
   }
}
