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
import javax.swing.JPanel;

/**
 *
 * @author Aurelie Roland
 */
public class MenuLocal extends JPanel{
    
    Connection dbConnect;
    
    AffLocal affLocal = new AffLocal();
    AjoutLocal ajoutLoc = new AjoutLocal();
    ModifLocal modifLoc = new ModifLocal();
    SuppLocal suppLoc = new SuppLocal();
    
    public JButton add;
    public JButton up;
    public JButton read;
    public JButton del;
    
    public MenuLocal(){
        
        this.setBackground(new Color(4, 14, 63));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add = new JButton("Ajouter");
        add.setMaximumSize(new Dimension(200, 47));
        add.setAlignmentX(Component.CENTER_ALIGNMENT);
        add.setBackground(Color.white);

        up = new JButton("Mettre à jour");
        up.setMaximumSize(new Dimension(200, 47));
        up.setAlignmentX(Component.CENTER_ALIGNMENT);
        up.setBackground(Color.white);

        read = new JButton("Lire");
        read.setMaximumSize(new Dimension(200, 47));
        read.setAlignmentX(Component.CENTER_ALIGNMENT);
        read.setBackground(Color.white);

        del = new JButton("Supprimer");
        del.setMaximumSize(new Dimension(200, 47));
        del.setAlignmentX(Component.CENTER_ALIGNMENT);
        del.setBackground(Color.white);

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(add);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(up);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(read);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(del);
        
        add.addActionListener((ActionEvent e) -> {
            ajoutLoc.setConnection(dbConnect);
            Window.fen.setContentPane(ajoutLoc);
            Window.fen.repaint();
            Window.fen.revalidate();
        });

        up.addActionListener((ActionEvent e) -> {
            modifLoc.setConnection(dbConnect);
            Window.fen.setContentPane(modifLoc);
            Window.fen.repaint();
            Window.fen.revalidate();
        });

        read.addActionListener((ActionEvent e) -> {
            affLocal.setConnection(dbConnect);
            Window.fen.setContentPane(affLocal);
            Window.fen.repaint();
            Window.fen.revalidate();
        });

        del.addActionListener((ActionEvent e) -> {
            suppLoc.setConnection(dbConnect);
            Window.fen.setContentPane(suppLoc);
            Window.fen.repaint();
            Window.fen.revalidate();
        });
        
        
    }
    
    public  void setConnection(Connection nouvdbConnect) {
      dbConnect=nouvdbConnect;
   }
    
}
