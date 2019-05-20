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
public class AjoutSessionCours extends JPanel {

    Connection dbConnect;

    public AjoutSessionCours() {
        
        this.setBackground(new Color(4, 14, 63));
        JPanel b0 = new JPanel();
        b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Ajout d'une session d'un cours");
        label.setForeground(Color.white);
        b0.add(label);
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

        b4.add(idLoc);
        b4.add(idLocTx);
        b4.setBackground(new Color(4, 14, 63));

        JPanel b5 = new JPanel();

        b5.setLayout(new BoxLayout(b5, BoxLayout.LINE_AXIS));
        JLabel idCours = new JLabel("ID du cours : ");
        idCours.setForeground(Color.white);
        JTextField idCoursTx = new JTextField();
        inscritTx.setColumns(15);

        b5.add(idCours);
        b5.add(idCoursTx);
        b5.setBackground(new Color(4, 14, 63));

        JButton ajout = new JButton("Ajouter");
        ajout.setBackground(Color.white);

        JPanel b6 = new JPanel();

        b6.setLayout(new BoxLayout(b6, BoxLayout.PAGE_AXIS));
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

        ajout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop1 = new JOptionPane();
                int intIdLoc = 0, intIdCours = 0, intInscrit = 0, x = 0, flag = 1;
                String dateDebGT = dateDebTx.getText();
                String dateFinGT = dateFinTx.getText();
                String inscritGT = inscritTx.getText();
                String idLocGT = idLocTx.getText();
                String idCoursGT = idCoursTx.getText();
                if (dateDebGT.equals("") || dateFinGT.equals("")|| inscritGT.equals("") || idLocGT.equals("") || idCoursGT.equals("")) {
                    jop1.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        intIdLoc = Integer.parseInt(idLocGT);
                        intIdCours = Integer.parseInt(idCoursGT);
                        intInscrit = Integer.parseInt(inscritGT);

                    } catch (NumberFormatException f) {
                        jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                        flag = 0;
                    }
                    if (flag == 1) {
                        SessionCours addSessCours = new SessionCours(0, dateDebGT, dateFinGT, intInscrit, intIdLoc, intIdCours);
                        try {
                            x = create(addSessCours);
                        } catch (SQLException ex) {
                            Logger.getLogger(AjoutSessionCours.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (x == 0) {
                            jop1.showMessageDialog(null, "Une erreur s'est produite", "Message", JOptionPane.INFORMATION_MESSAGE);
                        } else if (x == 1) {
                            jop1.showMessageDialog(null, "Bien ajouté à la base de donnée", "Message", JOptionPane.INFORMATION_MESSAGE);
                            Window.fen.setContentPane(new Menu());
                            Window.fen.repaint();
                            Window.fen.revalidate();
                        } else {
                            jop1.showMessageDialog(null, "IDCours ou IDLocal n'existe pas", "Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }

            }
        });
    }

    public int create(SessionCours obj) throws SQLException {
        int flag = 1;
        String queryCours = "select * from cours where idcours = ?";
        String queryLocal = "select * from local where idlocal = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(queryCours);
                PreparedStatement pstm2 = dbConnect.prepareStatement(queryLocal)) {
            pstm1.setInt(1, obj.getIdCours());
            pstm2.setInt(1, obj.getIdLocal());
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
            String query1 = "insert into SESSIONCOURS(DATEDEBUT,DATEFIN,NBRINSCRIT,IDLOCAL,IDCOURS) VALUES (?,?,?,?,?)";
            try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1)) {
                pstm1.setString(1, obj.getDateDeb());
                pstm1.setString(2, obj.getDateFin());
                pstm1.setInt(3, obj.getNbrInscrit());
                pstm1.setInt(4, obj.getIdLocal());
                pstm1.setInt(5, obj.getIdCours());
                int n = pstm1.executeUpdate();
                if (n == 0) {
                    return 0;
                }
                return 1;
            }
        }
        return -1;
    }

    public void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }
}
