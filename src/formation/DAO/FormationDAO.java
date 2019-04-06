/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.DAO;

import formation.Formation;
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
public class FormationDAO extends DAO<Formation>{
    
    Connection dbConnect;
    
    
    /**
     * Menu de la class Formation
     * @throws SQLException 
     */
    public void menu() throws SQLException{
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(0);
        }
        int flag = 0, ans = 0, drap = 0, mat, cp;
        String nom,prenom,rue, num, loc, tel;
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
                case 1: System.out.print("Entrer l'id du formulaire : ");
                        int idForm = sc.nextInt();
                        sc.skip("\n");
                        do{
                            drap = 1;
                            System.out.print("Entrez le matricule :");
                            mat = sc.nextInt();
                            sc.skip("\n");
                            String query = "select matricule from formation";
                            try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
                                try (ResultSet rs = pstm.executeQuery()) {
                                    while(rs.next()) {
                                        int forReq = rs.getInt("MATRICULE");
                                        if(mat == forReq){
                                            System.out.println("Erreur ! Ce matricule existe deja");
                                            drap = 0;
                                        }
                                    }
                                }
                            }
                        }while(drap == 0);                        
                        System.out.print("Entrez le nom : ");
                        nom = sc.nextLine();
                        System.out.print("Entrez le prenom : ");
                        prenom = sc.nextLine();
                        System.out.print("Entrez le numéro : ");
                        num = sc.nextLine();
                        System.out.print("Entrez la rue : ");
                        rue = sc.nextLine();
                        System.out.print("Entrez la localisation : ");
                        loc = sc.nextLine();
                        System.out.print("Entrez le code postal : ");
                        cp = sc.nextInt();
                        sc.skip("\n");
                        System.out.print("Entrez le numéro de téléphone : ");
                        tel = sc.nextLine();
                        Formation forC = new Formation(idForm, mat, nom, prenom, num, rue, loc, cp, tel);
                        create(forC);
                        break;
                case 2: System.out.println("Quel matricule recherchez vous ? ");
                        int rep = sc.nextInt();
                        read(rep);
                        break;
                case 3: System.out.print("Entrez le matricule de ce que vous voulez changer : ");
                        mat = sc.nextInt();
                        System.out.print("Entrez l'id : ");
                        idForm = sc.nextInt();
                        sc.skip("\n");
                        System.out.print("Entrez le nom : ");
                        nom = sc.nextLine();
                        System.out.print("Entrez le prenom : ");
                        prenom = sc.nextLine();
                        System.out.print("Entrez le numéro : ");
                        num = sc.nextLine();
                        System.out.print("Entrez la rue : ");
                        rue = sc.nextLine();
                        System.out.print("Entrez la localisation : ");
                        loc = sc.nextLine();
                        System.out.print("Entrez le code postal : ");
                        cp = sc.nextInt();
                        sc.skip("\n");
                        System.out.print("Entrez le numéro de téléphone : ");
                        tel = sc.nextLine();
                        Formation forUp = new Formation(idForm, mat, nom, prenom, num, rue, loc, cp, tel);
                        update(forUp);
                        break;
                case 4: System.out.print("Entrez le matricule de ce que vous voulez supprimer : ");
                        mat = sc.nextInt();
                        Formation forDel = new Formation(mat);
                        delete(forDel);
                        break;
                case 5: flag = 1;
                        break;
                default: System.out.println("Erreur, le nombre entré est incorrect");
            }
        }while(flag == 0);
        DBConnection.closeConnection();
    }

    /**
     * On lit une ligne de la table sur le matricule
     * @param id
     * @return 
     * @throws SQLException 
     */
    @Override
    public Formation read(int id) throws SQLException {
        String query1 = "select * from formation where matricule = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int idForm = rs.getInt("IDFORM");
                    int mat = rs.getInt("MATRICULE");
                    String nom = rs.getString("NOM");
                    String prenom = rs.getString("PRENOM");
                    String num = rs.getString("NUMERO");
                    String rue = rs.getString("RUE");
                    String loc = rs.getString("LOCALITE");
                    int cp = rs.getInt("CP");
                    String tel = rs.getString("TEL");
                    return new Formation(idForm, mat, nom, prenom, num, rue, loc, cp, tel);
                    

                } else {
                    throw new SQLException("Code inconnu");
                }

            }
        }
    }

    /**
     * On ajoute une ligne a la table Formation
     * @param obj
     * @return
     * @throws SQLException 
     */
    @Override
    public Formation create(Formation obj) throws SQLException {
        String query1 = "insert into formation(idForm, matricule, nom, prenom, numero, rue, localite, cp, tel) values (?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query1)){
            pstm.setInt(1, obj.getIdForm());
            pstm.setInt(2, obj.getMat());
            pstm.setString(3, obj.getNom());
            pstm.setString(4, obj.getPrenom());
            pstm.setString(5, obj.getNum());
            pstm.setString(6, obj.getRue());
            pstm.setString(7, obj.getLoc());
            pstm.setInt(8, obj.getCp());
            pstm.setString(9, obj.getTel());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("Erreur de création foramtion, aucune ligne créée");
            }
            return read(obj.getMat());
        }
    }
   

    /**
     * On change une ligne de la table Formation sur le matricule
     * @param obj
     * @return
     * @throws SQLException 
     */
    @Override
    public Formation update(Formation obj) throws SQLException {
        String query1 = "update formation set idform = ?, nom = ?, prenom = ?, numero = ?, rue = ?, localite = ?, cp = ?, tel = ? where matricule = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)){
            pstm.setInt(9, obj.getMat());
            pstm.setInt(1, obj.getIdForm());
            pstm.setString(2,obj.getNom());
            pstm.setString(3, obj.getPrenom());
            pstm.setString(4,obj.getNum());
            pstm.setString(5, obj.getRue());
            pstm.setString(6,obj.getLoc());
            pstm.setInt(7, obj.getCp());
            pstm.setString(8,obj.getTel());
            int n = pstm.executeUpdate();
            return read(obj.getMat());
        }
    }

    /**
     * On supprime une ligne de la table Formation sur le matricule
     * @param obj
     * @throws SQLException 
     */
    @Override
    public void delete(Formation obj) throws SQLException {
        String query1 = "delete from formation where matricule = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)){
            pstm.setInt(1, obj.getMat());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne client effacée");
            }       
        }
    }
    
    
}
