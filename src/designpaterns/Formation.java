/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpaterns;

/**
 *
 * @author Aurelie Roland
 */
public class Formation {

    protected int idForm, cp, mat;
    protected String nom, prenom, rue, num, loc, tel;

    /**
     * Constructeur
     *
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
    private Formation(FormationBuilder fb) {
        this.mat = fb.mat;
        this.nom = fb.nom;
        this.prenom = fb.prenom;
        this.num = fb.num;
        this.rue = fb.rue;
        this.loc = fb.loc;
        this.cp = fb.cp;
        this.tel = fb.tel;
    }

    public int getIdForm() {
        return idForm;
    }

    public int getCp() {
        return cp;
    }

    public int getMat() {
        return mat;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getRue() {
        return rue;
    }

    public String getNum() {
        return num;
    }

    public String getLoc() {
        return loc;
    }

    public String getTel() {
        return tel;
    }

    public static class FormationBuilder {

        protected int cp, mat;
        protected String nom, prenom, rue, num, loc, tel;

        public FormationBuilder setCp(int cp) {
            this.cp = cp;
            return this;
        }

        public FormationBuilder setMat(int mat) {
            this.mat = mat;
            return this;
        }

        public FormationBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public FormationBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public FormationBuilder setRue(String rue) {
            this.rue = rue;
            return this;
        }

        public FormationBuilder setNum(String num) {
            this.num = num;
            return this;
        }

        public FormationBuilder setLoc(String loc) {
            this.loc = loc;
            return this;
        }

        public FormationBuilder setTel(String tel) {
            this.tel = tel;
            return this;
        }

        public Formation build() throws Exception {
            if (cp <= 0 || mat <= 0 || nom == null || prenom == null || rue == null || num == null || loc == null || tel == null) {
                throw new Exception("informations de construction incomplètes");
            }
            return new Formation(this);
        }

    }

}
