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
public class AjoutFormation extends JPanel {

    Connection dbConnect;

    public AjoutFormation() {
        this.setBackground(new Color(4, 14, 63));
        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        JLabel label = new JLabel("Ajouter un formateur");
        label.setForeground(Color.white);
        b1.add(label);
        b1.setBackground(new Color(4, 14, 63));

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        JLabel nom = new JLabel("Nom : ");
        nom.setForeground(Color.white);
        JTextField nomTx = new JTextField();
        b2.setBackground(new Color(4, 14, 63));

        b2.add(nom);
        b2.add(nomTx);

        JPanel b3 = new JPanel();

        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        JLabel prenom = new JLabel("Prénom : ");
        prenom.setForeground(Color.white);
        JTextField prenomTx = new JTextField();
        prenomTx.setColumns(15);

        b3.add(prenom);
        b3.add(prenomTx);
        b3.setBackground(new Color(4, 14, 63));

        JPanel b4 = new JPanel();

        b4.setLayout(new BoxLayout(b4, BoxLayout.LINE_AXIS));
        JLabel mat = new JLabel("Matricule : ");
        mat.setForeground(Color.white);
        JTextField matTx = new JTextField();
        matTx.setColumns(15);

        b4.add(mat);
        b4.add(matTx);
        b4.setBackground(new Color(4, 14, 63));

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
        b10.add(b3);
        b10.add(Box.createRigidArea(new Dimension(0, 30)));
        b10.add(b4);
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
            int iMat = 0, flag = 1, iCp = 0;
            int x1 = 0;
            JOptionPane jop1 = new JOptionPane();
            String nomGT = nomTx.getText();
            String prenomGT = prenomTx.getText();
            String matGT = matTx.getText();
            String numGT = numeroTx.getText();
            String rueGT = rueTx.getText();
            String locaGT = locaTx.getText();
            String cpGT = cpTx.getText();
            String telGT = telTx.getText();
            if (nomGT.equals("") || prenomGT.equals("") || matGT.equals("") || numGT.equals("") || rueGT.equals("") || locaGT.equals("") || cpGT.equals("") || telGT.equals("")) {
                jop1.showMessageDialog(null, "Champ manquant", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    iMat = Integer.parseInt(matGT);
                    iCp = Integer.parseInt(cpGT);
                } catch (NumberFormatException f) {
                    jop1.showMessageDialog(null, "Vous n'avez pas entré un nombre", "Message", JOptionPane.INFORMATION_MESSAGE);
                    flag = 0;
                }
                if (flag == 1) {
                    Formation addForm = new Formation(0, iMat, nomGT, prenomGT, numGT, rueGT, locaGT, iCp, telGT);
                    try {
                        x1 = create(addForm);
                        if (x1 == 1) {
                            jop1.showMessageDialog(null, "Bien ajouté à la base de donnée", "Message", JOptionPane.INFORMATION_MESSAGE);
                        } else if (x1 == -1) {
                            jop1.showMessageDialog(null, "Le matricule existe déjà", "Message", JOptionPane.INFORMATION_MESSAGE);
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

    public int create(Formation obj) throws SQLException {
        int flag = 1;
        String que = "select * from formation where matricule = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(que)) {
            pstm1.setInt(1, obj.getMat());
            try (ResultSet rs = pstm1.executeQuery()) {
                if (rs.next()) {
                    flag = 0;
                }
            }
        }
        if (flag == 1) {
            String query1 = "insert into formation(matricule, nom, prenom, numero, rue, localite, cp, tel) values (?,?,?,?,?,?,?,?)";
            try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
                pstm.setInt(1, obj.getMat());
                pstm.setString(2, obj.getNom());
                pstm.setString(3, obj.getPrenom());
                pstm.setString(4, obj.getNum());
                pstm.setString(5, obj.getRue());
                pstm.setString(6, obj.getLoc());
                pstm.setInt(7, obj.getCp());
                pstm.setString(8, obj.getTel());
                int n = pstm.executeUpdate();
                if (n == 0) {
                    return 0;
                }
                return 1;
            }
        }
        return -1;
    }

    public void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }

}
