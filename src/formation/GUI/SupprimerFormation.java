/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import formation.Formation;
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
public class SupprimerFormation extends JPanel{
    
     Connection dbConnect;
    
     public SupprimerFormation(){
         this.setBackground(new Color(4, 14, 63));

        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel form = new JLabel("Quel ID de formation voulez vous supprimer ? ");
        form.setForeground(Color.white);
        JTextField idFormTx = new JTextField();
        idFormTx.setColumns(15);
        b1.add(form);
        b1.add(idFormTx);
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
            int flag = 1;
            int i = 0;
            int x1 = 0;
            String idFormGT = idFormTx.getText();
            if (idFormGT.equals("")) {
                JOptionPane.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    i = Integer.parseInt(idFormGT);
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                    flag = 0;
                }
               Formation formation = new Formation(i);
                if (flag == 1) {
                    try {
                        x1 = delete(formation);
                        if (x1 == 0) {
                            JOptionPane.showMessageDialog(null, "Aucune ligne supprimée", "Message", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Bien supprimé", "Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }catch (SQLException ex) {
                        Logger.getLogger(AjoutCours.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Une erreur s'est produite", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
     }
     
     public int delete(Formation obj) throws SQLException {
        String query1 = "delete from formation where idform = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)){
            pstm.setInt(1, obj.getIdForm());
            int n = pstm.executeUpdate();
            if (n == 0) {
                return 0;
            }       
            return 1;
        }
    }
    
    public  void setConnection(Connection nouvdbConnect) {
      dbConnect=nouvdbConnect;
   }
    
}
