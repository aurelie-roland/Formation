/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
