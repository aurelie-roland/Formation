/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import formation.Cours;
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
public class AjoutCours extends JPanel {

    Connection dbConnect;

    public AjoutCours() {
        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Ajouter un Cours");
        b1.add(label);

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        JLabel nomCours = new JLabel("Nom du cours : ");
        JTextField nomCoursTx = new JTextField();

        b2.add(nomCours);
        b2.add(nomCoursTx);

        JPanel b3 = new JPanel();

        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        JLabel heureCours = new JLabel("Nombres d'heures de cours : ");
        JTextField heureCoursTx = new JTextField();
        heureCoursTx.setColumns(15);

        b3.add(heureCours);
        b3.add(heureCoursTx);

        JButton ajout = new JButton("Ajouter");

        JPanel b4 = new JPanel();

        b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));
        b4.add(b1);
        b4.add(Box.createRigidArea(new Dimension(0, 25)));
        b4.add(b2);
        b4.add(Box.createRigidArea(new Dimension(0, 30)));
        b4.add(b3);
        b4.add(Box.createRigidArea(new Dimension(0, 50)));
        b4.add(ajout);

        add(b4);

        ajout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = 0, flag = 1;
                JOptionPane jop1 = new JOptionPane();
                String nom = nomCoursTx.getText();
                //TODO vérifier que nom soit rempli
                String heure = heureCoursTx.getText();
                try {
                    i = Integer.parseInt(heure);
                } catch (NumberFormatException f) {
                    jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                    flag = 0;
                }
                if(flag == 1){
                    Cours addCours = new Cours(0, nom, i);
                    try {
                        create(addCours);
                        jop1.showMessageDialog(null, "Bien ajouté à la base de donnée", "Message", JOptionPane.INFORMATION_MESSAGE);
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

    public boolean create(Cours obj) throws SQLException {
        String query1 = "insert into COURS(MATIERE,HEURES) VALUES (?,?)";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1)) {
            pstm1.setString(1, obj.getMatiere());
            pstm1.setInt(2, obj.getHeures());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                return false;
            }
            return true;
        }
    }

    public  void setConnection(Connection nouvdbConnect) {
      dbConnect=nouvdbConnect;
   }
}
