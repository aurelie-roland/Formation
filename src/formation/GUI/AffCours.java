package formation.GUI;

import formation.Cours;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import myconnections.DBConnection;

/**
 *
 * @author Aurelie Roland
 */
public class AffCours extends JPanel {

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

        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = 0;
                String idCours = idCoursTx.getText();
                try {
                    i = Integer.parseInt(idCours);
                } catch (NumberFormatException f) {
                    // n'est pas un nombre, gérer ce cas
                }
                Cours affCours = new Cours(i);
                Cours c = read(affCours);
                /*Object[][] data = {
                    {c.getIdCours(), c.getMatiere(),c.getHeures()}
                };*/

                String title[] = {"ID Cours", "Matière", "Heures"};
                //JTable tableau = new JTable(data, title);

                //add((tableau));
            }
        });
    }

   public Cours read(int id) throws SQLException {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
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
}
