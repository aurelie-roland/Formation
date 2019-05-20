package formation.GUI;

import formation.Cours;
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
public class ModifCours extends JPanel {

    Connection dbConnect;

    public ModifCours() {

        this.setBackground(new Color(4, 14, 63));
        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Modifier un Cours");
        label.setForeground(Color.white);
        b1.add(label);
        b1.setBackground(new Color(4, 14, 63));

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        JLabel idCours = new JLabel("Entrez l'ID à modifier:   ");
        idCours.setForeground(Color.white);
        JTextField idTx = new JTextField();
        b2.add(idCours);
        b2.add(idTx);
        b2.setBackground(new Color(4, 14, 63));

        JPanel b3 = new JPanel();

        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        JLabel nomCours = new JLabel("Nouveau nom du cours :   ");
        nomCours.setForeground(Color.white);
        JTextField nomCoursTx = new JTextField();

        b3.add(nomCours);
        b3.add(nomCoursTx);
        b3.setBackground(new Color(4, 14, 63));

        JPanel b4 = new JPanel();

        b4.setLayout(new BoxLayout(b4, BoxLayout.LINE_AXIS));
        JLabel heureCours = new JLabel("Nombres d'heures de cours :   ");
        heureCours.setForeground(Color.white);
        JTextField heureCoursTx = new JTextField();
        heureCoursTx.setColumns(15);

        b4.add(heureCours);
        b4.add(heureCoursTx);
        b4.setBackground(new Color(4, 14, 63));

        JButton modif = new JButton("Modifier");
        modif.setBackground(Color.white);

        JPanel b5 = new JPanel();

        b5.setLayout(new BoxLayout(b5, BoxLayout.PAGE_AXIS));
        b5.add(b1);
        b5.add(Box.createRigidArea(new Dimension(0, 25)));
        b5.add(b2);
        b5.add(Box.createRigidArea(new Dimension(0, 25)));
        b5.add(b3);
        b5.add(Box.createRigidArea(new Dimension(0, 25)));
        b5.add(b4);
        b5.add(Box.createRigidArea(new Dimension(0, 35)));
        b5.add(modif);
        b5.setBackground(new Color(4, 14, 63));

        add(b5);

        modif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = 0, idInt = 0, flag = 1;
                JOptionPane jop1 = new JOptionPane();
                String id = idTx.getText();
                String nom = nomCoursTx.getText();
                String heure = heureCoursTx.getText();
                if (nom.equals("") || heure.equals("") || id.equals("")) {
                    jop1.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        i = Integer.parseInt(heure);
                        idInt = Integer.parseInt(id);
                    } catch (NumberFormatException f) {
                        jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                        flag = 0;
                    }
                    if (flag == 1) {
                        Cours addCours = new Cours(idInt, nom, i);
                        try {
                            int res = update(addCours);
                            if (res == 1) {

                                jop1.showMessageDialog(null, "Bien modifié", "Message", JOptionPane.INFORMATION_MESSAGE);
                                Window.fen.setContentPane(new Menu());
                                Window.fen.repaint();
                                Window.fen.revalidate();
                            } else if( res == 0){
                                jop1.showMessageDialog(null, "Une erreur s'est produite", "Message", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                jop1.showMessageDialog(null, "l'id entré n'existe pas", "Message", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(AjoutCours.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        });
    }

    public int update(Cours obj) throws SQLException {
        int flag = 1;
        String query = "select * from cours where idcours = ?";
        try (PreparedStatement pstm2 = dbConnect.prepareStatement(query)) {
            pstm2.setInt(1, obj.getIdCours());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    flag = 0;
                }
            }
        }
        if (flag == 0) {
            String query1 = "update cours set matiere = ?, heures = ? where idCours = ?";
            try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
                pstm.setInt(3, obj.getIdCours());
                pstm.setString(1, obj.getMatiere());
                pstm.setInt(2, obj.getHeures());
                int n = pstm.executeUpdate();
                if (n == 0) {
                    return 0;
                }
                return 1;
            }
        }
        return - 1;
    }

    public void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }
}
