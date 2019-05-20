package formation.GUI;

import formation.SessionCours;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class SupprimerSessionCours extends JPanel {

    Connection dbConnect;

    public SupprimerSessionCours() {
        this.setBackground(new Color(4, 14, 63));

        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel session = new JLabel("Quel ID de session voulez vous supprimer ? ");
        session.setForeground(Color.white);
        JTextField idSessionTx = new JTextField();
        idSessionTx.setColumns(15);
        b1.add(session);
        b1.add(idSessionTx);
        b1.setBackground(new Color(4, 14, 63));

        JButton supp = new JButton("Supprimer");
        supp.setBackground(Color.white);

        JPanel b2 = new JPanel();

        b2.setBackground(new Color(4, 14, 63));

        b2.setLayout(new BoxLayout(b2, BoxLayout.PAGE_AXIS));
        b2.add(b1);
        b2.add(Box.createRigidArea(new Dimension(0, 25)));
        b2.add(supp);

        add(b2);

        supp.addActionListener((ActionEvent e) -> {
            JOptionPane jop1 = new JOptionPane();
            int flag = 1;
            int i = 0;
            int x1 = 0;
            String idCours = idSessionTx.getText();
            if (idCours.equals("")) {
                jop1.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    i = Integer.parseInt(idCours);
                } catch (NumberFormatException f) {
                    jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                    flag = 0;
                }
                SessionCours suppSessCours = new SessionCours(i);
                if (flag == 1) {
                    try {
                        x1 = delete(suppSessCours);
                        if (x1 == 0) {
                            jop1.showMessageDialog(null, "Aucune ligne supprimée", "Message", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            jop1.showMessageDialog(null, "Bien supprimé", "Message", JOptionPane.INFORMATION_MESSAGE);
                            Window.fen.setContentPane(new Menu());
                            Window.fen.repaint();
                            Window.fen.revalidate();
                        }
                    }catch (SQLException ex) {
                        Logger.getLogger(AjoutCours.class.getName()).log(Level.SEVERE, null, ex);
                        jop1.showMessageDialog(null, "Une erreur s'est produite", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    public int delete(SessionCours obj) throws SQLException {
        String query1 = "delete from SESSIONCOURS where idSessCours = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setInt(1, obj.getIdSess());
            int n = pstm.executeUpdate();
            if (n == 0) {
                return 0;
            }
            return 1;
        }
    }

    public void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }

}
