/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import formation.Locaux;
import formation.SessionCours;
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
public class ModifLocal extends JPanel {

    Connection dbConnect;

    public ModifLocal() {

        this.setBackground(new Color(4, 14, 63));
        JPanel b = new JPanel();
        b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Modification d'un local");
        label.setForeground(Color.white);
        b.add(label);
        b.setBackground(new Color(4, 14, 63));

        JPanel b0 = new JPanel();
        b0.setLayout(new BoxLayout(b0, BoxLayout.LINE_AXIS));
        JLabel idLoc = new JLabel("Entrez l'id de ce que vous voulez changer : ");
        idLoc.setForeground(Color.white);
        JTextField idLocTx = new JTextField();
        b0.add(idLoc);
        b0.add(idLocTx);
        b0.setBackground(new Color(4, 14, 63));

        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel places = new JLabel("Nombre de places : ");
        places.setForeground(Color.white);
        JTextField placesTx = new JTextField();
        b1.add(places);
        b1.add(placesTx);
        b1.setBackground(new Color(4, 14, 63));

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        JLabel sigle = new JLabel("Sigle : ");
        sigle.setForeground(Color.white);
        JTextField sigleTx = new JTextField();

        b2.add(sigle);
        b2.add(sigleTx);
        b2.setBackground(new Color(4, 14, 63));

        JPanel b3 = new JPanel();

        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        JLabel desc = new JLabel("Description : ");
        desc.setForeground(Color.white);
        JTextField descTx = new JTextField();
        descTx.setColumns(15);

        b3.add(desc);
        b3.add(descTx);
        b3.setBackground(new Color(4, 14, 63)); 

        JButton ajout = new JButton("Modifier");
        ajout.setBackground(Color.white);

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
        b6.add(Box.createRigidArea(new Dimension(0, 50)));
        b6.add(ajout);
        b6.setBackground(new Color(4, 14, 63));

        add(b6);

        ajout.addActionListener((ActionEvent e) -> {
            JOptionPane jop1 = new JOptionPane();
            int intIdLoc = 0, intPlaces = 0;
            int x1 = 0;
            int intId = 0;
            int flag = 1;
            String idLocGT = idLocTx.getText();
            String placesGT = placesTx.getText();
            String descGT = descTx.getText();
            String sigleGT = sigleTx.getText();;
            if (idLocGT.equals("") || placesGT.equals("") || descGT.equals("") || sigleGT.equals("")) {
                jop1.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    intIdLoc = Integer.parseInt(idLocGT);
                    intPlaces = Integer.parseInt(placesGT);
                } catch (NumberFormatException f) {
                    jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                    flag = 0;
                }
                if (flag == 1) {
                    Locaux modifLocaux = new Locaux(intIdLoc, sigleGT, intPlaces, descGT,0);
                    try {
                        x1 = update(modifLocaux);
                    } catch (SQLException ex) {
                        Logger.getLogger(AjoutSessionCours.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (x1 == 0) {
                        jop1.showMessageDialog(null, "Aucune ligne mise a jour", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } else{
                        jop1.showMessageDialog(null, "Bien ajouté à la base de donnée", "Message", JOptionPane.INFORMATION_MESSAGE);
                        Window.fen.setContentPane(new Menu());
                        Window.fen.repaint();
                        Window.fen.revalidate();
                    }
                }
            }
        });
    }

    public int update(Locaux obj) throws SQLException {
        String query1 = "update local set places = ?,description = ?, sigle = ? where idlocal = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setString(3, obj.getSigle());
            pstm.setInt(1, obj.getPlaces());
            pstm.setString(2, obj.getDescription());
            pstm.setInt(4, obj.getIdLocal());
            pstm.executeUpdate();
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
