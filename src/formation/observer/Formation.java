/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation.observer;

/**
 *
 * @author Aurelie Roland
 */
public class Formation {
    
    public static void main(String[] args) {
        Session p1 = new Session(1,"22/01/2019","30/03/2019", 75, 10, 5);
        Session p2 = new Session(2,"15/02/2019", "17/02/2019", 25, 17, 7);
        Formateur cl1= new Formateur(1, 17, "Dupont", "Jena", "17A", "Rue de Mons", "Ghlin", 7011, "0484/121212");
        Formateur cl2= new Formateur(2, 18, "Durand", "Annie", "15", "Rue du parc", "Beaumont", 6500, "0485/141414");
        p1.addObserver(cl1);
        p1.addObserver(cl2);
        p2.addObserver(cl1);
        p1.setIdLocal(210);
        p2.setIdLocal(12);
    }
}
    

