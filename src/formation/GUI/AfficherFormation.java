/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import formation.Formation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;

/**
 *
 * @author Aurelie Roland
 */
public class AfficherFormation extends JPanel{
    
     Connection dbConnect;
    
     
     public Formation read(int id) throws SQLException {
        String query1 = "select * from formation where matricule = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int idForm = rs.getInt("IDFORM");
                    int mat = rs.getInt("MATRICULE");
                    String nom = rs.getString("NOM");
                    String prenom = rs.getString("PRENOM");
                    String num = rs.getString("NUMERO");
                    String rue = rs.getString("RUE");
                    String loc = rs.getString("LOCALITE");
                    int cp = rs.getInt("CP");
                    String tel = rs.getString("TEL");
                    return new Formation(idForm, mat, nom, prenom, num, rue, loc, cp, tel);
                    

                } else {
                    throw new SQLException("Code inconnu");
                }

            }
        }
    }
    
    public  void setConnection(Connection nouvdbConnect) {
      dbConnect=nouvdbConnect;
   }
    
}
