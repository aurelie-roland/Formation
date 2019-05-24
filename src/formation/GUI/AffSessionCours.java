/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import formation.SessionCours;
import java.awt.Color;
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
public class AffSessionCours extends JPanel {

    Connection dbConnect;

    public AffSessionCours() {
        JPanel b1 = new JPanel();

        this.setBackground(new Color(4, 14, 63));

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel cours = new JLabel("Quel ID de session cherchez vous ? ");
        JTextField idCoursTx = new JTextField();
        idCoursTx.setColumns(15);
        cours.setForeground(Color.WHITE);
        b1.add(cours);
        b1.add(idCoursTx);
        b1.setBackground(new Color(4, 14, 63));

        JButton search = new JButton("Rechercher");
        search.setBackground(Color.white);

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.PAGE_AXIS));
        b2.add(b1);
        b2.add(Box.createRigidArea(new Dimension(0, 25)));
        b2.add(search);

        add(b2);
        b2.setBackground(new Color(4, 14, 63));

        search.addActionListener((ActionEvent e) -> {
            JOptionPane jop1 = new JOptionPane();
            int i = 0, flag = 1;
            SessionCours c = null;
            String idCours = idCoursTx.getText();
            if (idCours.equals("")) {
                jop1.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    i = Integer.parseInt(idCours);
                } catch (NumberFormatException f) {
                    jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                    flag = 0;
                }
                if (flag == 1) {
                    try {
                        c = read(i);
                        if (c == null) {
                            jop1.showMessageDialog(null, "L'id entré n'existe pas", "Message", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            Object[][] data = {
                                {"ID Session cours : " + c.getIdSess(), "Date début : " + c.getDateDeb(),
                                    "Date Fin : " + c.getDateFin(), "Nombres d'inscrit : " + c.getNbrInscrit(),
                                    "ID du local : " + c.getIdLocal(), "ID du cours : " + c.getIdCours()}
                            };
                            String title[] = {"ID Session cours", "Date début", "Date fin", "Nombres d'inscrits", "ID du local", "ID du cours"};
                            JTable tableau = new JTable(data, title);

                            jop1.showMessageDialog(null, data, "Information", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(AffCours.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
    }

    public SessionCours read(int id) throws SQLException {
        String query1 = "select * from sessioncours where idsesscours = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int idSessCours = rs.getInt("IDSESSCOURS");
                    String dateDeb = rs.getString("DATEDEBUT");
                    String dateFin = rs.getString("DATEFIN");
                    int nbIns = rs.getInt("NBRINSCRIT");
                    int idLoc = rs.getInt("IDLOCAL");
                    int idCours = rs.getInt("IDCOURS");
                    return new SessionCours(idSessCours, dateDeb, dateFin, nbIns, idLoc, idCours);
                } else {
                    return null;
                }
            }
        }
    }

    public void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }

}
