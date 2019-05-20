package formation.GUI;

import formation.SessionCours;
import java.awt.Color;
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
public class ModifSessionCours extends JPanel {

    Connection dbConnect;

    public ModifSessionCours() {
        this.setBackground(new Color(4, 14, 63));
        JPanel b = new JPanel();
        b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Modification d'une session d'un cours");
        label.setForeground(Color.white);
        b.add(label);
        b.setBackground(new Color(4, 14, 63));

        JPanel b0 = new JPanel();
        b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
        JLabel idSess = new JLabel("Entrez l'id de ce que vous voulez changer : ");
        idSess.setForeground(Color.white);
        JTextField idSessTx = new JTextField();
        b0.add(idSess);
        b0.add(idSessTx);
        b0.setBackground(new Color(4, 14, 63));

        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel dateDeb = new JLabel("Date début du cours : ");
        dateDeb.setForeground(Color.white);
        JTextField dateDebTx = new JTextField();
        b1.add(dateDeb);
        b1.add(dateDebTx);
        b1.setBackground(new Color(4, 14, 63));

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        JLabel dateFin = new JLabel("Date de fin du cours : ");
        dateFin.setForeground(Color.white);
        JTextField dateFinTx = new JTextField();

        b2.add(dateFin);
        b2.add(dateFinTx);
        b2.setBackground(new Color(4, 14, 63));

        JPanel b3 = new JPanel();

        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        JLabel inscrit = new JLabel("Nombre d'inscrit : ");
        inscrit.setForeground(Color.white);
        JTextField inscritTx = new JTextField();
        inscritTx.setColumns(15);

        b3.add(inscrit);
        b3.add(inscritTx);
        b3.setBackground(new Color(4, 14, 63));

        JPanel b4 = new JPanel();

        b4.setLayout(new BoxLayout(b4, BoxLayout.LINE_AXIS));
        JLabel idLoc = new JLabel("ID du local : ");
        idLoc.setForeground(Color.white);
        JTextField idLocTx = new JTextField();
        inscritTx.setColumns(15);
        b4.setBackground(new Color(4, 14, 63));

        b4.add(idLoc);
        b4.add(idLocTx);

        JPanel b5 = new JPanel();

        b5.setLayout(new BoxLayout(b5, BoxLayout.LINE_AXIS));
        JLabel idCours = new JLabel("ID du cours : ");
        idCours.setForeground(Color.white);setForeground(Color.white);
        JTextField idCoursTx = new JTextField();
        inscritTx.setColumns(15);

        b5.add(idCours);
        b5.add(idCoursTx);
        b5.setBackground(new Color(4, 14, 63));

        JButton ajout = new JButton("Modifier");
        ajout.setBackground(Color.white);

        JPanel b6 = new JPanel();

        b6.setLayout(new BoxLayout(b6, BoxLayout.PAGE_AXIS));
        b6.add(b);
        b6.add(Box.createRigidArea(new Dimension(0, 25)));
        b6.add(b0);
        b6.add(Box.createRigidArea(new Dimension(0, 25)));
        b6.add(b1);
        b6.add(Box.createRigidArea(new Dimension(0, 25)));
        b6.add(b2);
        b6.add(Box.createRigidArea(new Dimension(0, 25)));
        b6.add(b3);
        b6.add(Box.createRigidArea(new Dimension(0, 25)));
        b6.add(b4);
        b6.add(Box.createRigidArea(new Dimension(0, 25)));
        b6.add(b5);
        b6.add(Box.createRigidArea(new Dimension(0, 50)));
        b6.add(ajout);
        b6.setBackground(new Color(4, 14, 63));

        add(b6);

        ajout.addActionListener((ActionEvent e) -> {
            JOptionPane jop1 = new JOptionPane();
            int intIdLoc = 0, intIdCours = 0, intInscrit = 0;
            int x1 = 0;
            int intId = 0;
            int flag = 1;
            String idSessGT = idSessTx.getText();
            String dateDebGT = dateDebTx.getText();
            String dateFinGT = dateFinTx.getText();
            String inscritGT = inscritTx.getText();
            String idLocGT = idLocTx.getText();
            String idCoursGT = idCoursTx.getText();
            if (idSessGT.equals("") || dateDebGT.equals("") || dateFinGT.equals("") || inscritGT.equals("") || idLocGT.equals("") || idCoursGT.equals("")) {
                jop1.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    intIdLoc = Integer.parseInt(idLocGT);
                    intIdCours = Integer.parseInt(idCoursGT);
                    intInscrit = Integer.parseInt(inscritGT);
                    intId = Integer.parseInt(idSessGT);

                } catch (NumberFormatException f) {
                    jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                    flag = 0;
                }
                if (flag == 1) {
                    SessionCours modifSessCours = new SessionCours(intId, dateDebGT, dateFinGT, intInscrit, intIdLoc, intIdCours);
                    try {
                        x1 = update(modifSessCours);
                    } catch (SQLException ex) {
                        Logger.getLogger(AjoutSessionCours.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (x1 == 0) {
                        jop1.showMessageDialog(null, "Une erreur s'est produite", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else if (x1 == 1) {
                        jop1.showMessageDialog(null, "Bien ajouté à la base de donnée", "Message", JOptionPane.INFORMATION_MESSAGE);
                        Window.fen.setContentPane(new Menu());
                        Window.fen.repaint();
                        Window.fen.revalidate();
                    } else {
                        jop1.showMessageDialog(null, "ID, IDCours ou IDLocal n'existe pas", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

    }

    public int update(SessionCours obj) throws SQLException {
        int flag = 1;
        String queryCours = "select * from cours where idcours = ?";
        String queryLocal = "select * from local where idlocal = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(queryCours);
                PreparedStatement pstm2 = dbConnect.prepareStatement(queryLocal)) {
            pstm1.setInt(1,obj.getIdCours());
            pstm2.setInt(1,obj.getIdLocal());
            try (ResultSet rs = pstm1.executeQuery()) {
                if (!rs.next()) {
                    flag = 0;
                }
            }
            try (ResultSet rs = pstm2.executeQuery()) {
                if (!rs.next()) {
                    flag = 0;
                }
            }
        }
        if (flag == 1) {
            String query1 = "update SESSIONCOURS set DATEDEBUT = ?, DATEFIN = ?, NBRINSCRIT = ?, IDLOCAL = ?, IDCOURS = ? where IDSESSCOURS = ?";
            try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
                pstm.setInt(6, obj.getIdSess());
                pstm.setString(1, obj.getDateDeb());
                pstm.setString(2, obj.getDateFin());
                pstm.setInt(3, obj.getNbrInscrit());
                pstm.setInt(4, obj.getIdLocal());
                pstm.setInt(5, obj.getIdCours());
                int n = pstm.executeUpdate();
                if (n == 0) {
                    return 0;
                }
                return 1;
            }

        } else {
            return -1;
        }
    }

    public void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }

}
