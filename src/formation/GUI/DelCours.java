package formation.GUI;

import formation.Cours;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;

/**
 *
 * @author Aurelie Roland
 */
public class DelCours extends JPanel {

    Connection dbConnect;

    public DelCours() {

        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel cours = new JLabel("Quel ID de cours voulez vous supprimer ? ");
        JTextField idCoursTx = new JTextField();
        idCoursTx.setColumns(15);
        b1.add(cours);
        b1.add(idCoursTx);

        JButton supp = new JButton("Supprimer");

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.PAGE_AXIS));
        b2.add(b1);
        b2.add(Box.createRigidArea(new Dimension(0, 25)));
        b2.add(supp);

        add(b2);

        supp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop1 = new JOptionPane();
                int flag = 1;
                int i = 0;
                String idCours = idCoursTx.getText();
                try {
                    i = Integer.parseInt(idCours);
                } catch (NumberFormatException f) {
                    jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                    flag = 0;
                }
                Cours suppCours = new Cours(i);
                if (flag == 1) {
                    try {
                        delete(suppCours);
                        jop1.showMessageDialog(null, "Bien supprimé", "Message", JOptionPane.INFORMATION_MESSAGE);
                        Window.fen.setContentPane(new Menu());
                        Window.fen.repaint();
                        Window.fen.revalidate();
                    } catch (SQLException ex) {
                        Logger.getLogger(AjoutCours.class.getName()).log(Level.SEVERE, null, ex);
                        jop1.showMessageDialog(null, "Une erreur s'est produite", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    public void delete(Cours obj) throws SQLException {
        int flag = 0;
        String query1 = "delete from cours where idCours = ?";
        String querySess = "select * from SESSIONCOURS where idcours = ?";
        try (PreparedStatement pstmSess = dbConnect.prepareStatement(querySess)) {
            pstmSess.setInt(1, obj.getIdCours());
            try (ResultSet rs = pstmSess.executeQuery()) {
                if (rs.next()) {
                    flag = 1;
                }
            }
        }
        if (flag == 1) {
            String query2 = "delete from SESSIONCOURS where idCours = ?";
            try (PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
                pstm2.setInt(1, obj.getIdCours());
                int n2 = pstm2.executeUpdate();
            }
        }
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setInt(1, obj.getIdCours());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne client effacée");
            }
        }
    }

    public void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }
}
