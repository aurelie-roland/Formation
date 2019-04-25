package formation;


/**
 *
 * @author Aurelie Roland
 */
public class Locaux {
    
    int idLocal = 0;
    String sigle = "";
    int places = 0;
    String description = "";
    
    /**
     * constructeur de la class Locaux
     * @param idLocal ID du local
     * @param sigle Sigle du local
     * @param places Nombre de places
     * @param description Description du local
     */
    public Locaux(int idLocal, String sigle, int places, String description, int read){
        this.idLocal = idLocal;
        this.sigle = sigle;
        this.places = places;
        this.description = description;
        if(read == 1){
            System.out.println(toString());
        }
    }

    /**
     * Recupere l'ID du local
     * @return IDLocal
     */
    public int getIdLocal() {
        return idLocal;
    }

    /**
     * Change l'id du local
     * @param idLocal 
     */
    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    /**
     * Recupere le sigle du local
     * @return sigle
     */
    public String getSigle() {
        return sigle;
    }

    /**
     * Change le sigle du local
     * @param sigle 
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * Recupere le nombre de places
     * @return places
     */
    public int getPlaces() {
        return places;
    }

    /**
     * Change le nombres de places
     * @param places 
     */
    public void setPlaces(int places) {
        this.places = places;
    }

    /**
     * recupere a description
     * @return description description du local
     */
    public String getDescription() {
        return description;
    }

    /**
     * Change la description du local
     * @param description description du local
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Methode toString de la class Locaux
     * @return String de Local
     */
    public String toString(){
        return "Local : "+ idLocal + " sigle : "+ sigle +" places : "+ places + " description : "+ description;
    }

    
    
    
    
}
