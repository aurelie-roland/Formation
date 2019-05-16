/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation;

import formation.DAO.LocauxDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import myconnections.DBConnection;

/**
 *
 * @author Aurelie Roland
 */
public class GestionLocaux {
    
    Connection dbConnect;
    
    /**
     * Menu de la table Local
     * @throws SQLException 
     */
    public void menu() throws SQLException, Exception{
        int flag = 0, ans = 0, id = 0, places = 0;
        String sigle = "", des = "";
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        LocauxDAO locDAO = new LocauxDAO();
        locDAO.setConnection(dbConnect);
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("1) Créer");
            System.out.println("2) Lire");
            System.out.println("3) Mettre à jour");
            System.out.println("4) Supprimer");
            System.out.println("5) Recherche sur la description");
            System.out.println("6) Fin");
            System.out.print("Quel voulez vous faire ? ");
            ans = sc.nextInt();
            sc.skip("\n");
            int drap = 0;
            switch(ans){
                case 1: 
                        do{
                            drap = 1;
                            System.out.print("Entrez le sigle : ");
                            sigle = sc.nextLine();
                            String query = "select sigle from local";
                            try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
                                try (ResultSet rs = pstm.executeQuery()) {
                                    while(rs.next()) {
                                        String sigleReq = rs.getString("SIGLE");
                                        if(sigle.compareTo(sigleReq) == 0){
                                            System.out.println("Erreur ! Le sigle existe deja");
                                            drap = 0;
                                        }
                                    }
                                }
                                
                            }
                        }while(drap == 0);
                        System.out.print("Entrez le nombre de places : ");
                        places = sc.nextInt();
                        sc.skip("\n");
                        System.out.print("Entrez la description : ");
                        des = sc.nextLine();
                        Locaux loc = new Locaux(id,sigle,places,des, 0);
                        locDAO.create(loc);
                        break;
                case 2: System.out.println("Quel id du local recherchez vous ? ");
                        int rep = sc.nextInt();
                        locDAO.read(rep);
                        break;
                case 3: System.out.print("Entrez l'id de ce que vous voulez changer : ");
                        id = sc.nextInt();
                        sc.skip("\n");
                        do{
                            drap = 1;
                            System.out.print("Entrez le sigle : ");
                            sigle = sc.nextLine();
                            String query = "select sigle, idlocal from local";
                            try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
                                try (ResultSet rs = pstm.executeQuery()) {
                                    while(rs.next()) {
                                        String sigleReq = rs.getString("SIGLE");
                                        int idTmp = rs.getInt("idlocal");
                                        if(sigle.compareTo(sigleReq) == 0 && idTmp != id){
                                            System.out.println("Erreur ! Le sigle existe deja");
                                            drap = 0;
                                        }
                                    }
                                }
                                
                            }
                        }while(drap == 0);
                        System.out.print("Entrez le nombre de places : ");
                        places = sc.nextInt();
                        sc.skip("\n");
                        System.out.print("Entrez la description : ");
                        des = sc.nextLine();
                        Locaux locUp = new Locaux(id, sigle, places, des, 0);
                        locDAO.update(locUp);
                        break;
                case 4: System.out.print("Entrez l'id de ce que vous voulez supprimer : ");
                        id = sc.nextInt();
                        Locaux locDel = new Locaux(id,"",1," ", 0);
                        locDAO.delete(locDel);
                        break;
                case 5: System.out.print("Quel mot recherchez vous ? ");
                        String mot = sc.nextLine();
                        System.out.println(locDAO.rechDesc(mot));
                        break;
                case 6: flag = 1;
                        break;
                default: System.out.println("Erreur, le nombre entré est incorrect");
            }
        }while(flag == 0);
        DBConnection.closeConnection();
    }
    
}
