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
public class ModifFormation extends JPanel{
     Connection dbConnect;
    
    
     public int update(Formation obj) throws SQLException {
        String query1 = "update formation set idform = ?, nom = ?, prenom = ?, numero = ?, rue = ?, localite = ?, cp = ?, tel = ? where matricule = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)){
            pstm.setInt(9, obj.getMat());
            pstm.setInt(1, obj.getIdForm());
            pstm.setString(2,obj.getNom());
            pstm.setString(3, obj.getPrenom());
            pstm.setString(4,obj.getNum());
            pstm.setString(5, obj.getRue());
            pstm.setString(6,obj.getLoc());
            pstm.setInt(7, obj.getCp());
            pstm.setString(8,obj.getTel());
            int n = pstm.executeUpdate();
            return 1;
        }
    }
     
    public  void setConnection(Connection nouvdbConnect) {
      dbConnect=nouvdbConnect;
   }
}
