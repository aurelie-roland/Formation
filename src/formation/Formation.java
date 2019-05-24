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
public class Formation {
    
    int idForm = 0, cp, mat;
    String nom,prenom,rue, num, loc, tel;
    
    /**
     * Constructeur
     */
    public Formation(int idForm){
        this.idForm = idForm;
    }
    
    /**
     * Constructeur
     * @param idForm id de la formation
     * @param mat matricule
     * @param nom nom
     * @param prenom prenom
     * @param num numero
     * @param rue rue
     * @param loc localite
     * @param cp code postal
     * @param tel telephone
     */
    public Formation(int idForm, int mat, String nom, String prenom, String num, String rue, String loc, int cp, String tel){
        this.idForm = idForm;
        this.mat = mat;
        this.nom = nom;
        this.prenom = prenom;
        this.num = num;
        this.rue = rue;
        this.loc = loc;
        this.cp = cp;
        this.tel = tel;
       // System.out.println(toString());
    }

    /**
     * On recupere idForm
     * @return idForm
     */
    public int getIdForm() {
        return idForm;
    }

    /**
     * On change idForm
     * @param idForm 
     */
    public void setIdForm(int idForm) {
        this.idForm = idForm;
    }

    /**
     * On recupere le code postal
     * @return code postal
     */
    public int getCp() {
        return cp;
    }

    /**
     * On change le code postal
     * @param cp 
     */
    public void setCp(int cp) {
        this.cp = cp;
    }

    /**
     * On recupere le matricule
     * @return matricule
     */
    public int getMat() {
        return mat;
    }

    /**
     * On change le matricule
     * @param mat 
     */
    public void setMat(int mat) {
        this.mat = mat;
    }

    /**
     * On recupere le nom
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * On change le nom
     * @param nom 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    /**
     * On recupere le prenom
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * On change le prenom
     * @param prenom 
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * On recupere la rue
     * @return rue
     */
    public String getRue() {
        return rue;
    }

    /**
     * On change la rue
     * @param rue 
     */
    public void setRue(String rue) {
        this.rue = rue;
    }
    /**
     * On recupere le numero de la maison
     * @return numero maison
     */
    public String getNum() {
        return num;
    }

    /**
     * On change le numero de la maison
     * @param num 
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * On recupere la localite
     * @return localite
     */
    public String getLoc() {
        return loc;
    }

    /**
     * On change la localite
     * @param loc 
     */
    public void setLoc(String loc) {
        this.loc = loc;
    }

    /**
     * On recupere le numero de telephone
     * @return telephone
     */
    public String getTel() {
        return tel;
    }

    /**
     * On change le numero de telephone
     * @param tel 
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    /**
     * Methode toString de la classe formation
     * @return String formation
     */
    public String toString(){
        return "id Formation : "+idForm+" - Matricule : "+mat+" - Nom : "+nom+" - Prenom "
                + " : "+prenom+" \n Numero : "+num+" - Rue "+rue+" - Localite : "+loc+" - Code postal : "+cp+" - Téléphone : "+tel;
    }
}
