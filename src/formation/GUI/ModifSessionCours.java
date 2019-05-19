/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import formation.SessionCours;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class ModifSessionCours extends JPanel {

    Connection dbConnect;

    public ModifSessionCours() {
        JPanel b = new JPanel();
        b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Modification d'une session d'un cours");
        b.add(label);

        JPanel b0 = new JPanel();
        b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
        JLabel idSess = new JLabel("Entrez l'id de ce que vous voulez changer : ");
        JTextField idSessTx = new JTextField();
        b0.add(idSess);
        b0.add(idSessTx);

        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel dateDeb = new JLabel("Date début du cours : ");
        JTextField dateDebTx = new JTextField();
        b1.add(dateDeb);
        b1.add(dateDebTx);

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        JLabel dateFin = new JLabel("Date de fin du cours : ");
        JTextField dateFinTx = new JTextField();

        b2.add(dateFin);
        b2.add(dateFinTx);

        JPanel b3 = new JPanel();

        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        JLabel inscrit = new JLabel("Nombre d'inscrit : ");
        JTextField inscritTx = new JTextField();
        inscritTx.setColumns(15);

        b3.add(inscrit);
        b3.add(inscritTx);

        JPanel b4 = new JPanel();

        b4.setLayout(new BoxLayout(b4, BoxLayout.LINE_AXIS));
        JLabel idLoc = new JLabel("ID du local : ");
        JTextField idLocTx = new JTextField();
        inscritTx.setColumns(15);

        b4.add(idLoc);
        b4.add(idLocTx);

        JPanel b5 = new JPanel();

        b5.setLayout(new BoxLayout(b5, BoxLayout.LINE_AXIS));
        JLabel idCours = new JLabel("ID du cours : ");
        JTextField idCoursTx = new JTextField();
        inscritTx.setColumns(15);

        b5.add(idCours);
        b5.add(idCoursTx);

        JButton ajout = new JButton("Modifier");

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

        add(b6);

        ajout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane jop1 = new JOptionPane();
                int intIdLoc = 0, intIdCours = 0, intInscrit = 0, x = 0, intId = 0, flag = 1;
                String idSessGT = idSessTx.getText();
                String dateDebGT = dateDebTx.getText();
                String dateFinGT = dateFinTx.getText();
                String inscritGT = inscritTx.getText();
                String idLocGT = idLocTx.getText();
                String idCoursGT = idCoursTx.getText();
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
                        x = update(modifSessCours);
                    } catch (SQLException ex) {
                        Logger.getLogger(AjoutSessionCours.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (x == 0) {
                        jop1.showMessageDialog(null, "Une erreur s'est produite", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        jop1.showMessageDialog(null, "Bien ajouté à la base de donnée", "Message", JOptionPane.INFORMATION_MESSAGE);
                        Window.fen.setContentPane(new Menu());
                        Window.fen.repaint();
                        Window.fen.revalidate();
                    }
                }

            }
        });

    }

    public int update(SessionCours obj) throws SQLException {
        String query1 = "update SESSIONCOURS set DATEDEBUT = ?, DATEFIN = ?, NBRINSCRIT = ?, IDLOCAL = ?, IDCOURS = ? where IDSESSCOURS = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setInt(3, obj.getIdSess());
            pstm.setString(1, obj.getDateDeb());
            pstm.setString(2, obj.getDateFin());
            pstm.setInt(3, obj.getNbrInscrit());
            pstm.setInt(4, obj.getIdLocal());
            pstm.setInt(5, obj.getIdCours());
            //Vérifier que idloc et idcours existe
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
