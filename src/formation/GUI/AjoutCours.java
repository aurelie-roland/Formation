/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Aurelie Roland
 */
public class AjoutCours extends JPanel{
    
    public AjoutCours(){
        
   
        JLabel label = new JLabel("Ajouter un Cours");
        Box b1 = Box.createHorizontalBox();
	b1.add(label);
 
        Box b2 = Box.createHorizontalBox();
        JLabel nomCours = new JLabel("Nom du cours : ");
	JTextField nomCoursTx = new JTextField();
	nomCoursTx.setColumns(20);
 
	b2.add(nomCours);
        b2.add(nomCoursTx);
        
        
        JLabel heureCours = new JLabel("Nombres d'heures de cours : ");
	JTextField heureCoursTx = new JTextField();
	heureCoursTx.setColumns(15);
 
        Box b3 = Box.createHorizontalBox();
        b3.add(heureCours);
	b3.add(heureCoursTx);
        
        Component rigidArea1 = Box.createRigidArea(new Dimension(0,70));
        Component rigidArea2 = Box.createRigidArea(new Dimension(0,30));
        Box b4 = Box.createVerticalBox();
        b4.add(b1);
        b4.add(rigidArea1);
        b4.add(b2);
        b4.add(rigidArea2);
        b4.add(b3);
        this.add(b4);
    }    
}
