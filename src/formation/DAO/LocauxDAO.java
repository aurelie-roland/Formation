package formation.DAO;

import formation.Locaux;
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
public class LocauxDAO extends DAO<Locaux>{
    
    Connection dbConnect;
    
    
    /**
     * Menu de la table Local
     * @throws SQLException 
     */
    public void menu() throws SQLException{
        int flag = 0, ans = 0, id, places;
        String sigle,des;
        dbConnect = DBConnection.getConnection();
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
            int drap = 0;
            switch(ans){
                case 1: System.out.print("Entrez l'id du local : ");
                        id = sc.nextInt();
                        sc.skip("\n");
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
                        Locaux loc = new Locaux(id,sigle,places,des);
                        create(loc);
                        DBConnection.closeConnection();
                        break;
                case 2: System.out.println("Quel id du local recherchez vous ? ");
                        int rep = sc.nextInt();
                        read(rep);
                        DBConnection.closeConnection();
                        break;
                case 3: System.out.print("Entrez le sigle de ce que vous voulez changer : ");
                        sigle = sc.nextLine();
                        System.out.print("Entrez l'id : ");
                        id = sc.nextInt();
                        sc.skip("\n");
                        System.out.print("Entrez le nombre de places : ");
                        places = sc.nextInt();
                        sc.skip("\n");
                        System.out.print("Entrez la description : ");
                        des = sc.nextLine();
                        Locaux locUp = new Locaux(id, sigle, places, des);
                        update(locUp);
                        DBConnection.closeConnection();
                        break;
                case 4: System.out.print("Entrez le sigle de ce que vous voulez supprimer : ");
                        sigle = sc.nextLine();
                        Locaux locDel = new Locaux(1,sigle,1," ");
                        delete(locDel);
                        DBConnection.closeConnection();
                        break;
                case 5: flag = 1;
                        break;
                default: System.out.println("Erreur, le nombre entré est incorrect");
            }
        }while(flag == 0);
    }
    

    /**
     * Recuperation des informations de la classe local sur l'idLocal
     * @param id
     * @return Local
     * @throws SQLException "Code inconnu"
     */
    @Override
    public Locaux read(int id) throws SQLException {
        String query1 = "select * from local where idLocal = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {

            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int idLoc = rs.getInt("IDLOCAL");
                    String sigle = rs.getString("SIGLE");
                    int places = rs.getInt("PLACES");
                    String desc = rs.getString("DESCRIPTION");
                    return new Locaux(idLoc, sigle, places, desc);

                } else {
                    throw new SQLException("Code inconnu");
                }

            }
        }
    }
    
    /**
     * Creation d'un local sur base de donnee entree par l'utilisateur
     * @param obj
     * @return Local
     * @throws SQLException "Erreur de creation"
     */

    @Override
    public Locaux create(Locaux obj) throws SQLException {
        String query1 = "insert into local(idlocal,sigle,places,description) values (?,?,?,?)";
        String query2 = "select idlocal from local where sigle = ? and places = ? and description = ?";
        try(PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
                    PreparedStatement pstm2 = dbConnect.prepareStatement(query2)){
            pstm1.setInt(1, obj.getIdLocal());
            pstm1.setString(2, obj.getSigle());
            pstm1.setInt(3, obj.getPlaces());
            pstm1.setString(4, obj.getDescritpion());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                throw new SQLException("Erreur de création local");
            }
            pstm2.setString(1, obj.getSigle());
            pstm2.setInt(2, obj.getPlaces());
            pstm2.setString(3, obj.getDescritpion());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idlocal = rs.getInt(1);
                    obj.setIdLocal(idlocal);
                    return read(idlocal);
                } else {
                    throw new SQLException("erreur de création");
                }
            }
         }
    }

    /**
     * Om met a jour une ligne de la base de donnee Local
     * @param obj
     * @return Local
     * @throws SQLException 
     */
    @Override
    public Locaux update(Locaux obj) throws SQLException {
        String query1 = "update local set idlocal = ?, places = ?,description = ? where sigle = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)){
            pstm.setString(4, obj.getSigle());
            pstm.setInt(1, obj.getIdLocal());
            pstm.setInt(2,obj.getPlaces());
            pstm.setString(3, obj.getDescritpion());
            pstm.executeUpdate();
            return read(obj.getIdLocal());
        }
    }

    /**
     * On supprime une ligne de la table sur base de l'idLocal
     * @param obj
     * @throws SQLException 
     */
    @Override
    public void delete(Locaux obj) throws SQLException {
        String query1 = "delete from local where sigle = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)){
            pstm.setString(1, obj.getSigle());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne client effacée");
            }       
        }
    }
}
