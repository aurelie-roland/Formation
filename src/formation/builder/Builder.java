package formation.builder;

import designpaterns.Formation;

/**
 *
 * @author Aurelie Roland
 */
public class Builder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Formation cl1 = new Formation.FormationBuilder().setCp(1).setMat(1500).setNom("Louis").setPrenom("Alan").setRue("Rue de Mons").setNum("14B").setLoc("Mons").setTel("0485/151515").build();
            System.out.println(cl1);
        } catch (Exception e) {
            System.out.println("erreur " + e);
        }
        try {
            Formation cl2 = new Formation.FormationBuilder().setMat(1500).setNom("Louis").setPrenom("Alan").setRue("Rue de Mons").setNum("14B").setLoc("Mons").setTel("0485/151515").build();
            System.out.println(cl2);
        } catch (Exception e) {
            System.out.println("erreur " + e);
        }
    }
}
