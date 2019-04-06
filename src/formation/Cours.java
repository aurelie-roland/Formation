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
public class Cours {
    
    int idCours, heures;
    String matiere;
    
    /**
     * Constructeur de la class Cours
     * @param idCours id du cours
     */
    public Cours(int idCours){
        this.idCours = idCours;
    }
    
    /**
     * Constructeur de la class Cours
     * @param idCours id du cours
     * @param matiere matiere
     * @param heures heures
     */
    public Cours(int idCours, String matiere, int heures){
        this.idCours = idCours;
        this.matiere = matiere;
        this.heures = heures;
        System.out.println(toString());
        
    }

    /**
     * On recupere l'id du cours
     * @return id du cours
     */
    public int getIdCours() {
        return idCours;
    }

    /**
     * On change l'id du cours
     * @param idCours 
     */
    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    /**
     * On recupere le nombre d'heures
     * @return nombres heures
     */
    public int getHeures() {
        return heures;
    }

    /**
     * On change le nombre d'heures
     * @param heures 
     */
    public void setHeures(int heures) {
        this.heures = heures;
    }

    /**
     * On recupere le nom de la matiere
     * @return matiere
     */
    public String getMatiere() {
        return matiere;
    }

    /**
     * On change le nom de la matiere
     * @param matiere 
     */
    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }
    
    /**
     * Methode toString de la class cours
     * @return 
     */
    public String toString(){
        return "Cours : "+idCours+" - Matiere : "+matiere+" - Nombres heures : "+heures;
    }
}
