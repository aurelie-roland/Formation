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
public class MenuSessCours extends JPanel {

    Connection dbConnect;

    AjoutSessionCours ajoutSess = new AjoutSessionCours();
    AffSessionCours affSess = new AffSessionCours();
    ModifSessionCours modifSess = new ModifSessionCours();
    SupprimerSessionCours suppSess = new SupprimerSessionCours();
    AffichageCoursSession affCoursSess = new AffichageCoursSession();

    public JButton add;
    public JButton up;
    public JButton read;
    public JButton del;
    public JButton affCours;

    public MenuSessCours() {

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
        
        affCours= new JButton("Session avec ID cours");
        affCours.setMaximumSize(new Dimension(200, 47));
        affCours.setAlignmentX(Component.CENTER_ALIGNMENT);
        affCours.setBackground(Color.white);

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(add);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(up);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(read);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(del);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(affCours);

        add.addActionListener((ActionEvent e) -> {
            ajoutSess.setConnection(dbConnect);
            Window.fen.setContentPane(ajoutSess);
            Window.fen.repaint();
            Window.fen.revalidate();
        });

        up.addActionListener((ActionEvent e) -> {
            modifSess.setConnection(dbConnect);
            Window.fen.setContentPane(modifSess);
            Window.fen.repaint();
            Window.fen.revalidate();
        });

        read.addActionListener((ActionEvent e) -> {
            affSess.setConnection(dbConnect);
            Window.fen.setContentPane(affSess);
            Window.fen.repaint();
            Window.fen.revalidate();
        });

        del.addActionListener((ActionEvent e) -> {
            suppSess.setConnection(dbConnect);
            Window.fen.setContentPane(suppSess);
            Window.fen.repaint();
            Window.fen.revalidate();
        });
        
        affCours.addActionListener((ActionEvent e) -> {
            affCoursSess.setConnection(dbConnect);
            Window.fen.setContentPane(affCoursSess);
            Window.fen.repaint();
            Window.fen.revalidate();
        });
    }

    public void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }
}
