/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import formation.Locaux;
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
public class AjoutLocal extends JPanel {

    Connection dbConnect;

    public AjoutLocal() {
        this.setBackground(new Color(4, 14, 63));
        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Ajouter un local");
        label.setForeground(Color.white);
        b1.add(label);
        b1.setBackground(new Color(4, 14, 63));

        JPanel b3 = new JPanel();

        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        JLabel sigle = new JLabel("Sigle du local : ");
        sigle.setForeground(Color.white);
        JTextField sigleTx = new JTextField();
        sigleTx.setColumns(15);

        b3.add(sigle);
        b3.add(sigleTx);
        b3.setBackground(new Color(4, 14, 63));

        JPanel b4 = new JPanel();

        b4.setLayout(new BoxLayout(b4, BoxLayout.LINE_AXIS));
        JLabel places = new JLabel("Nombre de place : ");
        places.setForeground(Color.white);
        JTextField placesTx = new JTextField();
        placesTx.setColumns(15);

        b4.add(places);
        b4.add(placesTx);
        b4.setBackground(new Color(4, 14, 63));

        JPanel b5 = new JPanel();

        b5.setLayout(new BoxLayout(b5, BoxLayout.LINE_AXIS));
        JLabel desc = new JLabel("Description : ");
        desc.setForeground(Color.white);
        JTextField descTx = new JTextField();
        descTx.setColumns(15);

        b5.add(desc);
        b5.add(descTx);
        b5.setBackground(new Color(4, 14, 63));

        JButton ajout = new JButton("Ajouter");
        ajout.setBackground(Color.white);

        JPanel b6 = new JPanel();
        b6.setBackground(new Color(4, 14, 63));

        b6.setLayout(new BoxLayout(b6, BoxLayout.PAGE_AXIS));
        b6.add(b1);
        b6.add(Box.createRigidArea(new Dimension(0, 30)));
        b6.add(b3);
        b6.add(Box.createRigidArea(new Dimension(0, 30)));
        b6.add(b4);
        b6.add(Box.createRigidArea(new Dimension(0, 30)));
        b6.add(b5);
        b6.add(Box.createRigidArea(new Dimension(0, 50)));
        b6.add(ajout);

        add(b6);

        ajout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = 0, flag = 1, x = 0, pl = 0;
                JOptionPane jop1 = new JOptionPane();
                String sigleGT = sigleTx.getText();
                String placesGT = placesTx.getText();
                String descGT = descTx.getText();
                if (sigleGT.equals("") || placesGT.equals("") || descGT.equals("")) {
                    jop1.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        pl = Integer.parseInt(placesGT);
                    } catch (NumberFormatException f) {
                        jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                        flag = 0;
                    }
                    if (flag == 1) {
                        Locaux addLocaux = new Locaux(id, sigleGT, pl, descGT, 0);
                        try {
                            x = create(addLocaux);
                            if (x == 1) {
                                jop1.showMessageDialog(null, "Bien ajouté à la base de donnée", "Message", JOptionPane.INFORMATION_MESSAGE);
                            } else if (x == 0) {
                                jop1.showMessageDialog(null, "Une erreur s'est produite", "Message", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                jop1.showMessageDialog(null, "Sigle déjà existant", "Message", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(AjoutLocal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        });
    }

    public int create(Locaux obj) throws SQLException {
        int flag = 1;
        String req1 = "insert into local(sigle, places, description) values(?,?,?)";
        String req2 = "select * from local where sigle = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req2)) {
            pstm1.setString(1, obj.getSigle());
            try (ResultSet rs = pstm1.executeQuery()) {
                if (rs.next()) {
                    flag = 0;
                }
            }
        }
        if (flag == 1) {
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
        } else {
            return -1;
        }

    }

    public void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }

}
