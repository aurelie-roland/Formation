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
public class ModifFormation extends JPanel {

    Connection dbConnect;

    public ModifFormation() {
        this.setBackground(new Color(4, 14, 63));
        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Modifier un formateur");
        label.setForeground(Color.white);
        b1.add(label);
        b1.setBackground(new Color(4, 14, 63));

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        JLabel id = new JLabel("ID formateur : ");
        id.setForeground(Color.white);
        JTextField idTx = new JTextField();
        b2.setBackground(new Color(4, 14, 63));

        b2.add(id);
        b2.add(idTx);

        JPanel b5 = new JPanel();

        b5.setLayout(new BoxLayout(b5, BoxLayout.LINE_AXIS));
        JLabel numero = new JLabel("Numéro de rue : ");
        numero.setForeground(Color.white);
        JTextField numeroTx = new JTextField();
        numeroTx.setColumns(15);

        b5.add(numero);
        b5.add(numeroTx);
        b5.setBackground(new Color(4, 14, 63));

        JPanel b6 = new JPanel();

        b6.setLayout(new BoxLayout(b6, BoxLayout.LINE_AXIS));
        JLabel rue = new JLabel("Rue : ");
        rue.setForeground(Color.white);
        JTextField rueTx = new JTextField();
        rueTx.setColumns(15);

        b6.add(rue);
        b6.add(rueTx);
        b6.setBackground(new Color(4, 14, 63));

        JPanel b7 = new JPanel();

        b7.setLayout(new BoxLayout(b7, BoxLayout.LINE_AXIS));
        JLabel loca = new JLabel("Localité : ");
        loca.setForeground(Color.white);
        JTextField locaTx = new JTextField();
        locaTx.setColumns(15);

        b7.add(loca);
        b7.add(locaTx);
        b7.setBackground(new Color(4, 14, 63));

        JPanel b8 = new JPanel();

        b8.setLayout(new BoxLayout(b8, BoxLayout.LINE_AXIS));
        JLabel cp = new JLabel("Code postal : ");
        cp.setForeground(Color.white);
        JTextField cpTx = new JTextField();
        cpTx.setColumns(15);

        b8.add(cp);
        b8.add(cpTx);
        b8.setBackground(new Color(4, 14, 63));

        JPanel b9 = new JPanel();

        b9.setLayout(new BoxLayout(b9, BoxLayout.LINE_AXIS));
        JLabel tel = new JLabel("Téléphone : ");
        tel.setForeground(Color.white);
        JTextField telTx = new JTextField();
        telTx.setColumns(15);

        b9.add(tel);
        b9.add(telTx);
        b9.setBackground(new Color(4, 14, 63));

        JButton ajout = new JButton("Ajouter");
        ajout.setBackground(Color.white);

        JPanel b10 = new JPanel();
        b10.setBackground(new Color(4, 14, 63));

        b10.setLayout(new BoxLayout(b10, BoxLayout.PAGE_AXIS));
        b10.add(b1);
        b10.add(Box.createRigidArea(new Dimension(0, 25)));
        b10.add(b2);
        b10.add(Box.createRigidArea(new Dimension(0, 30)));
        b10.add(b5);
        b10.add(Box.createRigidArea(new Dimension(0, 30)));
        b10.add(b6);
        b10.add(Box.createRigidArea(new Dimension(0, 30)));
        b10.add(b7);
        b10.add(Box.createRigidArea(new Dimension(0, 30)));
        b10.add(b8);
        b10.add(Box.createRigidArea(new Dimension(0, 30)));
        b10.add(b9);
        b10.add(Box.createRigidArea(new Dimension(0, 50)));
        b10.add(ajout);

        add(b10);

        ajout.addActionListener((ActionEvent e) -> {
            int  flag = 1, iCp = 0, iId = 0;
            int x1 = 0;
            JOptionPane jop1 = new JOptionPane();
            String idGT = idTx.getText();
            String numGT = numeroTx.getText();
            String rueGT = rueTx.getText();
            String locaGT = locaTx.getText();
            String cpGT = cpTx.getText();
            String telGT = telTx.getText();
            if ( numGT.equals("") || rueGT.equals("") || locaGT.equals("") || cpGT.equals("") || telGT.equals("") || idTx.equals("")) {
                jop1.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    iCp = Integer.parseInt(cpGT);
                    iId = Integer.parseInt(idGT);
                } catch (NumberFormatException f) {
                    jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                    flag = 0;
                }
                if (flag == 1) {
                    Formation upForm = new Formation(iId, 0, "", "", numGT, rueGT, locaGT, iCp, telGT);
                    try {
                        x1 = update(upForm);
                        if (x1 == 1) {
                            jop1.showMessageDialog(null, "Bien modifié", "Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            jop1.showMessageDialog(null, "Une erreur s'est produite", "Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AjoutCours.class.getName()).log(Level.SEVERE, null, ex);
                        jop1.showMessageDialog(null, "Une erreur s'est produite", "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    public int update(Formation obj) throws SQLException {
        String query1 = "update formation set numero = ?, rue = ?, localite = ?, cp = ?, tel = ? where idForm = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setString(1, obj.getNum());
            pstm.setString(2, obj.getRue());
            pstm.setString(3, obj.getLoc());
            pstm.setInt(4, obj.getCp());
            pstm.setString(5, obj.getTel());
            pstm.setInt(6, obj.getIdForm());
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
