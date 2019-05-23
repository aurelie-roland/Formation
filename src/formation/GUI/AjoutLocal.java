/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import formation.Cours;
import formation.Locaux;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class AjoutLocal extends JPanel {

    Connection dbConnect;

    public AjoutLocal() {
        this.setBackground(new Color(4, 14, 63));
        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Ajouter un Cours");
        label.setForeground(Color.white);
        b1.add(label);
        b1.setBackground(new Color(4, 14, 63));

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        JLabel nomCours = new JLabel("Nom du cours : ");
        nomCours.setForeground(Color.white);
        JTextField nomCoursTx = new JTextField();
        b2.setBackground(new Color(4, 14, 63));

        b2.add(nomCours);
        b2.add(nomCoursTx);

        JPanel b3 = new JPanel();

        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        JLabel heureCours = new JLabel("Nombres d'heures de cours : ");
        heureCours.setForeground(Color.white);
        JTextField heureCoursTx = new JTextField();
        heureCoursTx.setColumns(15);

        b3.add(heureCours);
        b3.add(heureCoursTx);
        b3.setBackground(new Color(4, 14, 63));

        JButton ajout = new JButton("Ajouter");
        ajout.setBackground(Color.white);

        JPanel b4 = new JPanel();
        b4.setBackground(new Color(4, 14, 63));

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
                int i = 0, flag = 1, x = 0;
                JOptionPane jop1 = new JOptionPane();
                String nom = nomCoursTx.getText();
                String heure = heureCoursTx.getText();
                if (nom.equals("") || heure.equals("")) {
                    jop1.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        i = Integer.parseInt(heure);
                    } catch (NumberFormatException f) {
                        jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                        flag = 0;
                    }
                    if (flag == 1) {
                        Cours addCours = new Cours(0, nom, i);
                        x = create(addCours);
                        if (x == 1) {
                            jop1.showMessageDialog(null, "Bien ajouté à la base de donnée", "Message", JOptionPane.INFORMATION_MESSAGE);
                            Window.fen.setContentPane(new Menu());
                            Window.fen.repaint();
                            Window.fen.revalidate();
                        } else {
                            jop1.showMessageDialog(null, "Une erreur s'est produite", "Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }

            }
        });
    }

    public int create(Locaux obj) throws SQLException {
        String req1 = "insert into local(sigle, places, description) values(?,?,?)";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req1)) {
            pstm1.setString(1, obj.getSigle());
            pstm1.setInt(2, obj.getPlaces());
            pstm1.setString(3, obj.getDescription());
            int n = pstm1.executeUpdate();
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
