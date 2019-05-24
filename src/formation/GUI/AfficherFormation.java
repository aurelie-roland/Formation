/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import formation.Formation;
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
public class AfficherFormation extends JPanel{
    
     Connection dbConnect;
     
     public AfficherFormation(){
         JPanel b1 = new JPanel();

        this.setBackground(new Color(4, 14, 63));

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel form = new JLabel("Quel ID de formation cherchez vous ? ");
        JTextField idFormTx = new JTextField();
        idFormTx.setColumns(15);
        form.setForeground(Color.WHITE);
        b1.add(form);
        b1.add(idFormTx);
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
            String idFormGT = idFormTx.getText();
            if (idFormGT.equals("")) {
                jop1.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    i = Integer.parseInt(idFormGT);
                } catch (NumberFormatException f) {
                    jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                    flag = 0;
                }
                if (flag == 1) {
                    try {
                        Formation c = read(i);
                        if (c == null) {
                            jop1.showMessageDialog(null, "L'id entré n'existe pas", "Message", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            Object[][] data = {
                                {"ID formation : " + c.getIdForm(), "Matricule : " + c.getMat(),
                                    "Nom : " + c.getNom(), "Prénom : " + c.getPrenom(),
                                    "Numéro : " + c.getNum(), "Rue : " + c.getRue(),
                                    "Localité : " + c.getLoc(),"Code Postal : " + c.getCp(),"Téléphone : " + c.getTel() }
                            };
                            String title[] = {"ID formation", "Matricule", "Nom", "Prenom", "numero", "Rue", "Localite","Code postal","Telephone"};
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
    
     
     public Formation read(int id) throws SQLException {
        String query1 = "select * from formation where idform = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int idForm = rs.getInt("IDFORM");
                    int mat = rs.getInt("MATRICULE");
                    String nom = rs.getString("NOM");
                    String prenom = rs.getString("PRENOM");
                    String num = rs.getString("NUMERO");
                    String rue = rs.getString("RUE");
                    String loc = rs.getString("LOCALITE");
                    int cp = rs.getInt("CP");
                    String tel = rs.getString("TEL");
                    return new Formation(idForm, mat, nom, prenom, num, rue, loc, cp, tel);
                } else {
                    return null;
                }

            }
        }
    }
    
    public  void setConnection(Connection nouvdbConnect) {
      dbConnect=nouvdbConnect;
   }
    
}
