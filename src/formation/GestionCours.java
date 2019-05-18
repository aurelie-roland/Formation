/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation;

import formation.DAO.CoursDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import myconnections.DBConnection;

/**
 *
 * @author Aurelie Roland
 */
public class GestionCours {
    
    public void menu() throws SQLException{
        CoursDAO coursDAO = new CoursDAO();
        int flag = 0, ans = 0;
        int idCours, heures;
        String matiere;
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("1) Créer");
            System.out.println("2) Lire");
            System.out.println("3) Mettre à jour");
            System.out.println("4) Supprimer");
            System.out.println("5) Fin");
            System.out.print("Quel voulez vous faire ? ");
            ans = sc.nextInt();
            sc.skip("\n");
            switch(ans){
                case 1: System.out.print("Entrer l'id du cours : ");
                        idCours = sc.nextInt();
                        sc.skip("\n");
                        System.out.print("Entrer la matiere : ");
                        matiere = sc.nextLine();
                        System.out.print("Entrer l'heure du cours : ");
                        heures = sc.nextInt();
                        sc.skip("\n");
                        Cours coursC = new Cours(idCours, matiere, heures);
                        coursDAO.create(coursC);
                        break;
                case 2: System.out.println("Quel id recherchez vous ? ");
                        int rep = sc.nextInt();
                        coursDAO.read(rep);
                        break;
                case 3: System.out.print("Entrer l'id du cours que vous voulez changer : ");
                        idCours = sc.nextInt();
                        sc.skip("\n");
                        System.out.print("Entrer la matiere : ");
                        matiere = sc.nextLine();
                        System.out.print("Entrer l'heure du cours : ");
                        heures = sc.nextInt();
                        sc.skip("\n");
                        Cours coursUp = new Cours(idCours, matiere, heures);
                        coursDAO.update(coursUp);
                        break;
                case 4: System.out.print("Entrez l'id de ce que vous voulez supprimer : ");
                        idCours = sc.nextInt();
                        Cours coursDel = new Cours(idCours);
                        coursDAO.delete(coursDel);
                        break;
                case 5: flag = 1;
                        break;
                default: System.out.println("Erreur, le nombre entré est incorrect");
            }
        }while(flag == 0);
        DBConnection.closeConnection();
    }
    
}
