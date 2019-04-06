package formation.DAO;

import formation.Cours;
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
public class CoursDAO extends DAO<Cours>{
    
    Connection dbConnect;
    
    /**
     * Menu de la methode Cours
     * @throws SQLException 
     */
    public void menu() throws SQLException{
        int flag = 0, ans = 0;
        int idCours, heures;
        String matiere;
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
                        create(coursC);
                        break;
                case 2: System.out.println("Quel id recherchez vous ? ");
                        int rep = sc.nextInt();
                        read(rep);
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
                        update(coursUp);
                        break;
                case 4: System.out.print("Entrez l'id de ce que vous voulez supprimer : ");
                        idCours = sc.nextInt();
                        Cours coursDel = new Cours(idCours);
                        delete(coursDel);
                        break;
                case 5: flag = 1;
                        break;
                default: System.out.println("Erreur, le nombre entré est incorrect");
            }
        }while(flag == 0);
        DBConnection.closeConnection();
    }

    /**
     * On lit une ligne de la table Cours sur l'id du cours
     * @param id
     * @return
     * @throws SQLException 
     */
    @Override
    public Cours read(int id) throws SQLException {
        String query1 = "select * from cours where idcours = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int idCours = rs.getInt("IDCOURS");
                    String matiere = rs.getString("MATIERE");
                    int heures = rs.getInt("HEURES");
                    return new Cours(idCours, matiere, heures);
                }
                else {
                    throw new SQLException("Code inconnu");
                }
            }
        }
    }

    /**
     * On ajoute une ligne a la table Cours
     * @param obj
     * @return
     * @throws SQLException 
     */
    @Override
    public Cours create(Cours obj) throws SQLException {
        String query1 = "insert into COURS(IDCOURS,MATIERE,HEURES) VALUES (?,?,?)";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1)){
             System.out.println("mgfhjk");
            pstm1.setInt(1, obj.getIdCours());
            pstm1.setString(2,obj.getMatiere());
            pstm1.setInt(3,obj.getHeures());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                throw new SQLException("Erreur de création foramtion, aucune ligne créée");
            }
            return read(obj.getIdCours());
        }
    }

    /**
     * On modifie une ligne de la table sur l'id du cours
     * @param obj
     * @return
     * @throws SQLException 
     */
    @Override
    public Cours update(Cours obj) throws SQLException {
        String query1 = "update cours set matiere = ?, heures = ? where idCours = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)){
            pstm.setInt(3, obj.getIdCours());
            pstm.setString(1, obj.getMatiere());
            pstm.setInt(2,obj.getHeures());
            int n = pstm.executeUpdate();
            return read(obj.getIdCours());
        }
    }

    /**
     * On supprime une ligne de la table sur l'id du cours
     * @param obj
     * @throws SQLException 
     */
    @Override
    public void delete(Cours obj) throws SQLException {
        String query1 = "delete from cours where idCours = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)){
            pstm.setInt(1, obj.getIdCours());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne client effacée");
            }       
        }
    }
    
}
