/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Aurelie Roland
 */
public class DelCours extends JPanel{
    
    public DelCours(){
        
        setLayout(new GridLayout(2,3,20,20));
        JLabel del = new JLabel("Quel id du cours voulez vous supprimer ? ");
        JTextField delID = new JTextField();
       
        
        JLabel del1 = new JLabel("Quel id du cours voulez vous supprimer ? ");
        JTextField delID1 = new JTextField();
        
        JLabel del2 = new JLabel("Quel id ");
        JTextField delID2 = new JTextField();
        
        
        add(del);
        add(delID);
        
        add(del1);
        add(delID1);
        
        add(del2);
        add(delID2);
    } 
}
