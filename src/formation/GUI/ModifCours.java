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
public class ModifCours extends JPanel {
    
    Connection dbConnect;

    public ModifCours() {
        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Modifier un Cours");
        b1.add(label);

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        JLabel idCours = new JLabel("Entrez l'ID à modifier:   ");
        JTextField idTx = new JTextField();
        b2.add(idCours);
        b2.add(idTx);

        JPanel b3 = new JPanel();

        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        JLabel nomCours = new JLabel("Nouveau nom du cours :   ");
        JTextField nomCoursTx = new JTextField();

        b3.add(nomCours);
        b3.add(nomCoursTx);

        JPanel b4 = new JPanel();

        b4.setLayout(new BoxLayout(b4, BoxLayout.LINE_AXIS));
        JLabel heureCours = new JLabel("Nombres d'heures de cours :   ");
        JTextField heureCoursTx = new JTextField();
        heureCoursTx.setColumns(15);

        b4.add(heureCours);
        b4.add(heureCoursTx);

        JButton modif = new JButton("Modifier");

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

        add(b5);

        modif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = 0, idInt = 0;
                String id = idTx.getText();
                String nom = nomCoursTx.getText();
                String heure = heureCoursTx.getText();
                try {
                    i = Integer.parseInt(heure);
                    idInt = Integer.parseInt(id);
                } catch (NumberFormatException f) {
                    // n'est pas un nombre, gérer ce cas
                }
                Cours addCours = new Cours(idInt, nom, i);
                try {
                    int res = update(addCours);
                    if (res == 1) {
                        JOptionPane jop1 = new JOptionPane();
                        jop1.showMessageDialog(null, "Bien modifié", "Message", JOptionPane.INFORMATION_MESSAGE);
                        Window.fen.setContentPane(new Menu());
                        Window.fen.repaint();
                        Window.fen.revalidate();
                    }
                    else{
                        JOptionPane jop1 = new JOptionPane();
                    jop1.showMessageDialog(null, "Une erreur s'est produite", "Message", JOptionPane.INFORMATION_MESSAGE);
                    } 
                } catch (SQLException ex) {
                    Logger.getLogger(AjoutCours.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public int update(Cours obj) throws SQLException {
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
    
    public  void setConnection(Connection nouvdbConnect) {
      dbConnect=nouvdbConnect;
   }
}
