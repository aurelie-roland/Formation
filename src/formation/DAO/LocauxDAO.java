package formation.DAO;

import formation.Locaux;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aurelie Roland
 */
public class LocauxDAO extends DAO<Locaux> {

    /**
     * Recuperation des informations de la classe local sur l'idLocal
     *
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
                    return new Locaux(idLoc, sigle, places, desc, 1);

                } else {
                    throw new SQLException("Code inconnu");
                }

            }
        }
    }

    /**
     * Creation d'un local sur base de donnee entree par l'utilisateur
     *
     * @param obj
     * @return Local
     * @throws SQLException "Erreur de creation"
     */
    @Override
    public Locaux create(Locaux obj) throws SQLException {
        String req1 = "insert into local(sigle, places, description) values(?,?,?)";
        String req2 = "select idLocal from local where sigle=? and places=? and description=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(req1);
                PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm1.setString(1, obj.getSigle());
            pstm1.setInt(2, obj.getPlaces());
            pstm1.setString(3, obj.getDescription());
            int n = pstm1.executeUpdate();
            if (n == 0) {
                throw new SQLException("erreur de creation, aucune ligne créée");
            }
            pstm2.setString(1, obj.getSigle());
            pstm2.setInt(2, obj.getPlaces());
            pstm2.setString(3, obj.getDescription());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idLocal = rs.getInt(1);
                    obj.setIdLocal(idLocal);
                    return read(idLocal);
                } else {
                    throw new SQLException("erreur de création, record introuvable");
                }
            }
        }
    }

    /**
     * Om met a jour une ligne de la base de donnee Local
     *
     * @param obj
     * @return Local
     * @throws SQLException
     */
    @Override
    public Locaux update(Locaux obj) throws SQLException {
        String query1 = "update local set places = ?,description = ?, sigle = ? where idlocal = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setString(3, obj.getSigle());
            pstm.setInt(1, obj.getPlaces());
            pstm.setString(2, obj.getDescription());
            pstm.setInt(4, obj.getIdLocal());
            pstm.executeUpdate();
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne locaux mise à jour");
            }
            return read(obj.getIdLocal());
        }
    }

    /**
     * On supprime une ligne de la table sur base de l'idLocal
     *
     * @param obj
     * @throws SQLException
     */
    @Override
    public void delete(Locaux obj) throws SQLException {
        String query1 = "delete from local where idlocal = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query1)) {
            pstm.setInt(1, obj.getIdLocal());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne effacée");
            }
        }
    }

    /**
     * méthode statique permettant de récupérer tous les locaux portant un
     * certain mot dans la description
     *
     * @param nomrech mot recherché
     * @return liste des locaux
     * @throws Exception mot inconnue
     */
    public List<Locaux> rechNom(String nomrech) throws Exception {
        List<Locaux> plusieurs = new ArrayList<>();
        String req = "select * from local where description = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setString(1, nomrech);
            try (ResultSet rs = pstm.executeQuery()) {
                boolean trouve = false;
                while (rs.next()) {
                    trouve = true;
                    int idLoc = rs.getInt("IDLOCAL");
                    String sigle = rs.getString("SIGLE");
                    int places = rs.getInt("PLACES");
                    String desc = rs.getString("DESCRIPTION");
                    plusieurs.add(new Locaux(idLoc, sigle, places, desc, 0));
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
