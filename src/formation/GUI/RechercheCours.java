/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import formation.Cours;
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
public class RechercheCours extends JPanel {

    Connection dbConnect;

    public RechercheCours() {
        this.setBackground(new Color(4, 14, 63));
        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel cours = new JLabel("Quel mot cherchez vous ? ");
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
            JOptionPane jop1 = new JOptionPane();
            String desc = coursTx.getText();
            if(desc.equals("")){
                jop1.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                List<Cours> rCours = null;
                try {
                    rCours = rechDesc(desc);
                } catch (SQLException ex) {
                    Logger.getLogger(RechercheCours.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rCours == null) {
                    jop1.showMessageDialog(null, "Aucun résultat", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String[][] data = new String[rCours.size()][3];
                    for (int i = 0; i < rCours.size(); i++) {
                        String id = Integer.toString(rCours.get(i).getIdCours());
                        String matiere = rCours.get(i).getMatiere();
                        String heures = Integer.toString(rCours.get(i).getHeures());
                        data[i][0] = id;
                        data[i][1] = matiere;
                        data[i][2] = heures;
                    }
                    String title[] = {"ID Cours", "Matière", "Heures"};
                    JTable tableau = new JTable(data, title);

                    jop1.showMessageDialog(null, tableau, "Information", JOptionPane.INFORMATION_MESSAGE);
                }
        }
            

        });
    }

    public List<Cours> rechDesc(String nomdesc) throws SQLException {
        List<Cours> plusieurs = new ArrayList<>();
        String req2 = "SELECT * FROM cours WHERE matiere LIKE ? order by IDCOURS";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req2)) {
            pstm.setString(1, "%" + nomdesc + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                boolean trouve = false;
                while (rs.next()) {
                    trouve = true;
                    int idCours = rs.getInt("IDCOURS");
                    String matiere = rs.getString("MATIERE");
                    int heures = rs.getInt("HEURES");
                    plusieurs.add(new Cours(idCours, matiere, heures));
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
