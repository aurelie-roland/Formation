package formation.GUI;

import formation.Cours;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Aurelie Roland
 */
public class AffCours extends JPanel {
    
    Connection dbConnect;

    public AffCours() {
        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel cours = new JLabel("Quel ID de cours cherchez vous ? ");
        JTextField idCoursTx = new JTextField();
        idCoursTx.setColumns(15);
        b1.add(cours);
        b1.add(idCoursTx);

        JButton search = new JButton("Rechercher");

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.PAGE_AXIS));
        b2.add(b1);
        b2.add(Box.createRigidArea(new Dimension(0, 25)));
        b2.add(search);

        add(b2);

        search.addActionListener((ActionEvent e) -> {
            int i = 0;
            Cours c = null;
            String idCours = idCoursTx.getText();
            try {
                i = Integer.parseInt(idCours);
            } catch (NumberFormatException f) {
                // n'est pas un nombre, gérer ce cas
            }
            try {
                c = read(i);

                Object[][] data = {
                    {"ID Cours : "+c.getIdCours(),"Matière : "+ c.getMatiere(),"Heures : "+c.getHeures()}
                };
                String title[] = {"ID Cours", "Matière", "Heures"};
                JTable tableau = new JTable(data, title);
                JOptionPane jop1 = new JOptionPane();
                jop1.showMessageDialog(null, data, "Information", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                Logger.getLogger(AffCours.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    public Cours read(int id) throws SQLException {
        String query1 = "select * from cours where idcours = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int idCours = rs.getInt("IDCOURS");
                    String matiere = rs.getString("MATIERE");
                    int heures = rs.getInt("HEURES");
                    return new Cours(idCours, matiere, heures);
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
