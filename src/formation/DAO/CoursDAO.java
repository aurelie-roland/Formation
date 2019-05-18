package formation.DAO;

import formation.Cours;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aurelie Roland
 */
public class CoursDAO extends DAO<Cours> {

    Connection dbConnect;

    /**
     * On lit une ligne de la table Cours sur l'id du cours
     *
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
                } else {
                    throw new SQLException("Code inconnu");
                }
            }
        }
    }

    /**
     * On ajoute une ligne a la table Cours
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    @Override
    public Cours create(Cours obj) throws SQLException {
        String query1 = "insert into COURS(IDCOURS,MATIERE,HEURES) VALUES (?,?,?)";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1)) {
            System.out.println("mgfhjk");
            pstm1.setInt(1, obj.getIdCours());
            pstm1.setString(2, obj.getMatiere());
            pstm1.setInt(3, obj.getHeures());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                throw new SQLException("Erreur de création foramtion, aucune ligne créée");
            }
            return read(obj.getIdCours());
        }
    }

    /**
     * On modifie une ligne de la table sur l'id du cours
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    @Override
    public Cours update(Cours obj) throws SQLException {
        String query1 = "update cours set matiere = ?, heures = ? where idCours = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setInt(3, obj.getIdCours());
            pstm.setString(1, obj.getMatiere());
            pstm.setInt(2, obj.getHeures());
            int n = pstm.executeUpdate();
            return read(obj.getIdCours());
        }
    }

    /**
     * On supprime une ligne de la table sur l'id du cours
     *
     * @param obj
     * @throws SQLException
     */
    @Override
    public void delete(Cours obj) throws SQLException {
        String query1 = "delete from cours where idCours = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setInt(1, obj.getIdCours());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne client effacée");
            }
        }
    }

    public List<Cours> rechDesc(String nomdesc) throws SQLException {
        List<Cours> plusieurs = new ArrayList<>();
        String req2 = "SELECT * FROM cours WHERE matiere LIKE ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req2)) {
            pstm.setString(1, "%" + nomdesc + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                boolean trouve = false;
                while (rs.next()) {
                    trouve = true;
                    int idCours = rs.getInt("IDCOURS");
                    String matiere = rs.getString("MATIERE");
                    int heures = rs.getInt("HEURES");
                    plusieurs.add(new Cours(idCours, matiere, heures));
                }
                if (!trouve) {
                    throw new SQLException("nom inconnu");
                } else {
                    return plusieurs;
                }
            }
        }
    }

}
