/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Aurelie Roland
 */
public class AffCours extends JPanel {

    public AffCours() {
        JPanel b1 = new JPanel();

        b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
        b1.add(new JButton("Bouton 1"));

        JPanel b2 = new JPanel();

        b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
        b2.add(new JButton("Bouton 2"));
        b2.add(new JButton("Bouton 3"));

        JPanel b3 = new JPanel();

        b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
        b3.add(new JButton("Bouton 4"));
        b3.add(new JButton("Bouton 5"));
        b3.add(new JButton("Bouton 6"));

        JPanel b4 = new JPanel();

        b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));
        b4.add(b1);
        b4.add(Box.createRigidArea(new Dimension(0, 100)));
        b4.add(b2);
        b4.add(Box.createRigidArea(new Dimension(0, 100)));
        b4.add(b3);

        add(b4);
    }
}
