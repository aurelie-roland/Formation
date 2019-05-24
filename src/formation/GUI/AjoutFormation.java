/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import formation.Formation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JPanel;

/**
 *
 * @author Aurelie Roland
 */
public class AjoutFormation extends JPanel{
    
     Connection dbConnect;
     
     
    
     
     public int create(Formation obj) throws SQLException {
        String query1 = "insert into formation(idForm, matricule, nom, prenom, numero, rue, localite, cp, tel) values (?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query1)){
            pstm.setInt(1, obj.getIdForm());
            pstm.setInt(2, obj.getMat());
            pstm.setString(3, obj.getNom());
            pstm.setString(4, obj.getPrenom());
            pstm.setString(5, obj.getNum());
            pstm.setString(6, obj.getRue());
            pstm.setString(7, obj.getLoc());
            pstm.setInt(8, obj.getCp());
            pstm.setString(9, obj.getTel());
            int n = pstm.executeUpdate();
            if (n == 0) {
                return 0;
            }
            return 1;
        }
    }
    
    public  void setConnection(Connection nouvdbConnect) {
      dbConnect=nouvdbConnect;
   }
    
}
