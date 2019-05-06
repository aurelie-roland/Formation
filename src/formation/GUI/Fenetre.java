/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 *
 * @author Aurelie Roland
 */
public class Fenetre extends JFrame{

    public static Fenetre fen = new Fenetre();
    Menu menu = new Menu();
    MenuFormateur menuForm = new MenuFormateur();
    MenuLocal menuLoc = new MenuLocal();
    MenuCours menuCours = new MenuCours();
    MenuSessCours menuSess = new MenuSessCours();
    
    public Fenetre(){
        JMenuBar bar = new JMenuBar();
	bar.setBackground(Color.WHITE);
        
        JMenu fichier = new JMenu("Fichier");
        JMenuItem itemMenu = new JMenuItem("Menu");
        JMenuItem itemQuit = new JMenuItem("Quitter");
		
	JMenu form = new JMenu("Formateur");
	JMenuItem itemCreateFor = new JMenuItem("Ajouter");
	JMenuItem itemUpFor = new JMenuItem("Mettre à jour");
	JMenuItem itemReadFor = new JMenuItem("Lire");
        JMenuItem itemDelFor = new JMenuItem("Supprimer");
		
	JMenu loc = new JMenu("Local");
	JMenuItem itemCreateLoc = new JMenuItem("Ajouter");
	JMenuItem itemUpLoc = new JMenuItem("Mettre à jour");
	JMenuItem itemReadLoc = new JMenuItem("Lire");
        JMenuItem itemDelLoc = new JMenuItem("Supprimer");
		
	JMenu cours = new JMenu("Cours");
        JMenuItem itemCreateCours = new JMenuItem("Ajouter");
	JMenuItem itemUpCours = new JMenuItem("Mettre à jour");
	JMenuItem itemReadCours = new JMenuItem("Lire");
        JMenuItem itemDelCours = new JMenuItem("Supprimer");
	
        
        JMenu sessCours = new JMenu("Session cours");
        JMenuItem itemCreateSess = new JMenuItem("Ajouter");
	JMenuItem itemUpSess = new JMenuItem("Mettre à jour");
	JMenuItem itemReadSess = new JMenuItem("Lire");
        JMenuItem itemDelSess = new JMenuItem("Supprimer");
        
        fichier.add(itemMenu);
        fichier.add(itemQuit);
		
	form.add(itemCreateFor);
	form.add(itemUpFor);
	form.add(itemReadFor);
        form.add(itemDelFor);
        
	loc.add(itemCreateLoc);
	loc.add(itemUpLoc);
	loc.add(itemReadLoc);
        loc.add(itemDelLoc);
        
        cours.add(itemCreateCours);
	cours.add(itemUpCours);
	cours.add(itemReadCours);
        cours.add(itemDelCours);
        
        sessCours.add(itemCreateSess);
	sessCours.add(itemUpSess);
	sessCours.add(itemReadSess);
        sessCours.add(itemDelSess);
        bar.add(fichier);
	bar.add(form);
	bar.add(loc);
	bar.add(cours);
        bar.add(sessCours);

	//Ajout de la JMenuBar a la fenêtre
        this.setJMenuBar(bar);
        
        menu.form.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                remove(menu);
                add(menuForm);
                repaint();
		revalidate();
            }
        });
        
        menu.loc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                remove(menu);
                add(menuLoc);
                repaint();
		revalidate();
            }
        });
        
        menu.sess.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                remove(menu);
                add(menuForm);
                repaint();
		revalidate();
            }
        });
        
        menu.cours.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                remove(menu);
                add(menuForm);
                repaint();
		revalidate();
            }
        });
        
        itemMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setContentPane(menuForm);
                repaint();
		revalidate();
            }
        });
        
        this.setTitle("Formation");
	this.setSize(700, 550);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(menu);
	this.setResizable(false);
	this.setVisible(true);
    }
    
}
