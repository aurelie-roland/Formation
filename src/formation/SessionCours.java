/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation;


/**
 *
 * @author Aurelie Roland
 */
public class SessionCours {
    
    int IdCours = 0;
    int idLocal = 0;
    int idSess = 0;
    String dateDeb;
    String dateFin;
    int nbrInscrit = 0;
    
    public SessionCours(int idSess,String dateDeb, String dateFin, int nbrInscrit, int idLoc, int idCours){
        this.idSess = idSess;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.nbrInscrit = nbrInscrit;
        this.idLocal = idLoc;
        this.IdCours = idCours;
    }

    public int getIdCours() {
        return IdCours;
    }

    public void setIdCours(int IdCours) {
        this.IdCours = IdCours;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdSess() {
        return idSess;
    }

    public void setIdSess(int idSess) {
        this.idSess = idSess;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }


    public int getNbrInscrit() {
        return nbrInscrit;
    }

    public void setNbrInscrit(int nbrInscrit) {
        this.nbrInscrit = nbrInscrit;
    }
    
}
