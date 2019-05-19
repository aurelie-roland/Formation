/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Aurelie Roland
 */
public class Menu extends JPanel {

    Connection dbConnect;

    MenuFormateur menuForm = new MenuFormateur();
    MenuLocal menuLoc = new MenuLocal();
    AjoutCours ajoutCours = new AjoutCours();
    MenuCours menuCours = new MenuCours();
    MenuSessCours menuSess = new MenuSessCours();

    public JButton form;
    public JButton loc;
    public JButton cours;
    public JButton sess;
    public JButton quit;

    public Menu() {
        
        this.setBackground(new Color(4,14,63));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        form = new JButton("Formateur");
        form.setMaximumSize(new Dimension(200, 40));
        form.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.setBackground(Color.white);

        loc = new JButton("Local");
        loc.setMaximumSize(new Dimension(200, 40));
        loc.setAlignmentX(Component.CENTER_ALIGNMENT);
        loc.setBackground(Color.white);

        cours = new JButton("Cours");
        cours.setMaximumSize(new Dimension(200, 40));
        cours.setAlignmentX(Component.CENTER_ALIGNMENT);
        cours.setBackground(Color.white);

        sess = new JButton("Session Cours");
        sess.setMaximumSize(new Dimension(200, 40));
        sess.setAlignmentX(Component.CENTER_ALIGNMENT);
        sess.setBackground(Color.white);

        quit = new JButton("Quitter");
        quit.setMaximumSize(new Dimension(200, 40));
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        quit.setBackground(Color.white);

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(form);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(loc);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(cours);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(sess);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(quit);

        quit.addActionListener((ActionEvent e) -> {
            String[] exitOp = {"Oui", "Non"};
            int option = JOptionPane.showOptionDialog(null, "Voulez vous vraiment quitter ? ", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, exitOp, exitOp[0]);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        cours.addActionListener((ActionEvent e) -> {
            menuCours.setConnection(dbConnect);
            Window.fen.setContentPane(menuCours);
            Window.fen.repaint();
            Window.fen.revalidate();
        });

        form.addActionListener((ActionEvent e) -> {
            menuForm.setConnection(dbConnect);
            Window.fen.setContentPane(menuForm);
            Window.fen.repaint();
            Window.fen.revalidate();
        });

        loc.addActionListener((ActionEvent e) -> {
            menuLoc.setConnection(dbConnect);
            Window.fen.setContentPane(menuLoc);
            Window.fen.repaint();
            Window.fen.revalidate();
        });

        sess.addActionListener((ActionEvent e) -> {
            menuSess.setConnection(dbConnect);
            Window.fen.setContentPane(menuSess);
            Window.fen.repaint();
            Window.fen.revalidate();
        });
    }

    public void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }

}
