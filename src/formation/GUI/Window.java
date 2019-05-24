package formation.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import myconnections.DBConnection;

/**
 *
 * @author Aurelie Roland
 */
public class Window extends JFrame {

    Connection dbConnect;

    Menu menu = new Menu();
    MenuFormateur menuForm = new MenuFormateur();
    MenuLocal menuLoc = new MenuLocal();
    MenuCours menuCours = new MenuCours();
    MenuSessCours menuSess = new MenuSessCours();
    AjoutCours ajoutCours = new AjoutCours();
    public static Window fen = new Window();
    AffCours affCours = new AffCours();
    DelCours delCours = new DelCours();
    ModifCours modifCours = new ModifCours();
    RechercheCours rechCours = new RechercheCours();
    AffLocal affLocal = new AffLocal();
    AjoutLocal ajoutLoc = new AjoutLocal();
    ModifLocal modifLoc = new ModifLocal();
    SuppLocal suppLoc = new SuppLocal();
    AjoutSessionCours ajoutSess = new AjoutSessionCours();
    AffSessionCours affSess = new AffSessionCours();
    ModifSessionCours modifSess = new ModifSessionCours();
    SupprimerSessionCours suppSess = new SupprimerSessionCours();
    AfficherFormation affFor = new AfficherFormation();
    AjoutFormation ajoutFor = new AjoutFormation();
    ModifFormation modifFor = new ModifFormation();
    SupprimerFormation suppFor = new SupprimerFormation();
    AffichageCoursSession affCoursSess = new AffichageCoursSession();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }

    public Window() {

        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }

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
        JMenuItem itemRechCours = new JMenuItem("Recherche partielle");

        JMenu sessCours = new JMenu("Session cours");
        JMenuItem itemCreateSess = new JMenuItem("Ajouter");
        JMenuItem itemUpSess = new JMenuItem("Mettre à jour");
        JMenuItem itemReadSess = new JMenuItem("Lire");
        JMenuItem itemDelSess = new JMenuItem("Supprimer");
        JMenuItem itemCoursSession = new JMenuItem("Session avec ID cours");

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
        cours.add(itemRechCours);

        sessCours.add(itemCreateSess);
        sessCours.add(itemUpSess);
        sessCours.add(itemReadSess);
        sessCours.add(itemDelSess);
        sessCours.add(itemCoursSession);
        
        bar.add(fichier);
        bar.add(form);
        bar.add(loc);
        bar.add(cours);
        bar.add(sessCours);

        this.setJMenuBar(bar);

        itemMenu.addActionListener((ActionEvent e) -> {
            menu.setConnection(dbConnect);
            setContentPane(menu);
            repaint();
            revalidate();
        });

        itemCreateCours.addActionListener((ActionEvent e) -> {
            ajoutCours.setConnection(dbConnect);
            setContentPane(ajoutCours);
            repaint();
            revalidate();
        });

        itemQuit.addActionListener((ActionEvent e) -> {
            String[] exitOp = {"Oui", "Non"};
            int option = JOptionPane.showOptionDialog(null, "Voulez vous vraiment quitter ? ", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, exitOp, exitOp[0]);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        itemUpCours.addActionListener((ActionEvent e) -> {
            modifCours.setConnection(dbConnect);
            setContentPane(modifCours);
            repaint();
            revalidate();
        });

        itemReadCours.addActionListener((ActionEvent e) -> {
            affCours.setConnection(dbConnect);
            setContentPane(affCours);
            repaint();
            revalidate();
        });

        itemDelCours.addActionListener((ActionEvent e) -> {
            delCours.setConnection(dbConnect);
            setContentPane(delCours);
            repaint();
            revalidate();
        });

        itemRechCours.addActionListener((ActionEvent e) -> {
            rechCours.setConnection(dbConnect);
            setContentPane(rechCours);
            repaint();
            revalidate();
        });

        itemCreateFor.addActionListener((ActionEvent e) -> {
            ajoutFor.setConnection(dbConnect);
            setContentPane(ajoutFor);
            repaint();
            revalidate();
        });

        itemUpFor.addActionListener((ActionEvent e) -> {
            modifFor.setConnection(dbConnect);
            setContentPane(modifFor);
            repaint();
            revalidate();
        });

        itemReadFor.addActionListener((ActionEvent e) -> {
            affFor.setConnection(dbConnect);
            setContentPane(affFor);
            repaint();
            revalidate();
        });

        itemDelFor.addActionListener((ActionEvent e) -> {
            suppFor.setConnection(dbConnect);
            setContentPane(suppFor);
            repaint();
            revalidate();
        });

        itemCreateLoc.addActionListener((ActionEvent e) -> {
            ajoutLoc.setConnection(dbConnect);
            setContentPane(ajoutLoc);
            repaint();
            revalidate();
        });
        itemUpLoc.addActionListener((ActionEvent e) -> {
            modifLoc.setConnection(dbConnect);
            setContentPane(modifLoc);
            repaint();
            revalidate();
        });
        itemReadLoc.addActionListener((ActionEvent e) -> {
            affLocal.setConnection(dbConnect);
            setContentPane(affLocal);
            repaint();
            revalidate();
        });
        itemDelLoc.addActionListener((ActionEvent e) -> {
            suppLoc.setConnection(dbConnect);
            setContentPane(suppLoc);
            repaint();
            revalidate();
        });

        itemCreateSess.addActionListener((ActionEvent e) -> {
            ajoutSess.setConnection(dbConnect);
            setContentPane(ajoutSess);
            repaint();
            revalidate();
        });
        itemUpSess.addActionListener((ActionEvent e) -> {
            modifSess.setConnection(dbConnect);
            setContentPane(modifSess);
            repaint();
            revalidate();
        });
        itemReadSess.addActionListener((ActionEvent e) -> {
            affSess.setConnection(dbConnect);
            setContentPane(affSess);
            repaint();
            revalidate();
        });
        itemDelSess.addActionListener((ActionEvent e) -> {
            suppSess.setConnection(dbConnect);
            setContentPane(suppSess);
            repaint();
            revalidate();
        });
        
        itemCoursSession.addActionListener((ActionEvent e) -> {
            affCoursSess.setConnection(dbConnect);
            setContentPane(affCoursSess);
            repaint();
            revalidate();
        });
        
        this.setTitle("Formation");
        this.setSize(700, 550);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setConnection(dbConnect);
        this.setContentPane(menu);
        this.setResizable(false);
        this.setVisible(true);
    }
}
