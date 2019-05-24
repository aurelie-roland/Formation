/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import formation.Cours;
import formation.SessionCours;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class AffichageCoursSession extends JPanel {

    Connection dbConnect;

    public AffichageCoursSession() {

        this.setBackground(new Color(4, 14, 63));
        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel cours = new JLabel("Quel ID de cours recherchez vous ? ");
        cours.setForeground(Color.white);
        JTextField coursTx = new JTextField();
        coursTx.setColumns(15);
        b1.add(cours);
        b1.add(coursTx);
        b1.setBackground(new Color(4, 14, 63));

        JButton search = new JButton("Rechercher");
        search.setBackground(Color.white);

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.PAGE_AXIS));
        b2.add(b1);
        b2.add(Box.createRigidArea(new Dimension(0, 25)));
        b2.add(search);
        b2.setBackground(new Color(4, 14, 63));

        add(b2);

        search.addActionListener((ActionEvent e) -> {
            int iDesc = 0, flag = 1;
            JOptionPane jop1 = new JOptionPane();
            String desc = coursTx.getText();
            if (desc.equals("")) {
                jop1.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    iDesc = Integer.parseInt(desc);
                } catch (NumberFormatException f) {
                    jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                    flag = 0;
                }
                if (flag == 1) {
                    List<SessionCours> rCours = null;
                    try {
                        rCours = rechDesc(iDesc);
                    } catch (SQLException ex) {
                        Logger.getLogger(RechercheCours.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (rCours == null) {
                        jop1.showMessageDialog(null, "Aucun résultat", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        String[][] data = new String[rCours.size()][6];
                        for (int i = 0; i < rCours.size(); i++) {
                            String idSessCours = Integer.toString(rCours.get(i).getIdSess());
                            String dateDeb = rCours.get(i).getDateDeb();
                            String dateFin = rCours.get(i).getDateFin();
                            String inscrit = Integer.toString(rCours.get(i).getNbrInscrit());
                            String idLocal = Integer.toString(rCours.get(i).getIdLocal());
                            String idCours = Integer.toString(rCours.get(i).getIdCours());
                            data[i][0] = idSessCours;
                            data[i][1] = dateDeb;
                            data[i][2] = dateFin;
                            data[i][3] = inscrit;
                            data[i][4] = idLocal;
                            data[i][5] = idCours;
                        }
                        String title[] = {"ID Session Cours", "Date début", "date fin", "inscrit", "id Local", "Id cours"};
                        JTable tableau = new JTable(data, title);

                        jop1.showMessageDialog(null, tableau, "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            }

        });
    }

    public List<SessionCours> rechDesc(int id) throws SQLException {
        List<SessionCours> plusieurs = new ArrayList<>();
        String req2 = "SELECT * FROM sessioncours WHERE idcours = ? order by IDCOURS";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req2)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                boolean trouve = false;
                while (rs.next()) {
                    trouve = true;
                    int idSessCours = rs.getInt("IDSESSCOURS");
                    String dateDeb = rs.getString("DATEDEBUT");
                    String dateFin = rs.getString("DATEFIN");
                    int nbIns = rs.getInt("NBRINSCRIT");
                    int idLoc = rs.getInt("IDLOCAL");
                    int idCours = rs.getInt("IDCOURS");
                    plusieurs.add(new SessionCours(idSessCours, dateDeb, dateFin, nbIns, idLoc, idCours));
                }
                if (!trouve) {
                    return null;
                } else {
                    return plusieurs;
                }
            }
        }
    }

    public void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }

}
