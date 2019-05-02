/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import static formation.GUI.Fenetre.fen;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Aurelie Roland
 */
public class Menu extends JPanel{
    
    MenuFormateur menuForm = new MenuFormateur();
    MenuLocal menuLoc = new MenuLocal();
    
    public JButton form;
    public JButton loc;
    public JButton cours;
    public JButton sess;
    public JButton quit;
    
    public Menu(){
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            
        form = new JButton("Formateur");
        form.setMaximumSize(new Dimension(200, 47));
        form.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        loc = new JButton("Local");   
        loc.setMaximumSize(new Dimension(200, 47));
        loc.setAlignmentX(Component.CENTER_ALIGNMENT);

        cours = new JButton("Cours");
        cours.setMaximumSize(new Dimension(200, 47));
        cours.setAlignmentX(Component.CENTER_ALIGNMENT);

        sess = new JButton("Session Cours");
        sess.setMaximumSize(new Dimension(200, 47));
        sess.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        quit = new JButton("Quitter");
        quit.setMaximumSize(new Dimension(200, 47));
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);
			

	add(Box.createRigidArea(new Dimension(0,100)));
	add(form);
	add(Box.createRigidArea(new Dimension(0,10)));
        add(loc);
	add(Box.createRigidArea(new Dimension(0,10)));
        add(cours);
	add(Box.createRigidArea(new Dimension(0,10)));
	add(sess);
	add(Box.createRigidArea(new Dimension(0,10)));
	add(quit);
        
        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
		String[] exitOp = { "Oui", "Non" };
		int option = JOptionPane.showOptionDialog(null,"Voulez vous vraiment quitter ? ", "Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, exitOp, exitOp[0]);
		if (option == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
            }
	});
        
        form.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Fenetre.fen.remove(fen.menu);
                Fenetre.fen.add(menuForm);
                Fenetre.fen.repaint();
		Fenetre.fen.revalidate();
            }
        });
    }
}
