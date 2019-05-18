/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formation;

import formation.DAO.CoursDAO;
import formation.DAO.FormationDAO;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Aurelie Roland
 */
public class GestionFormation {
    
    Scanner sc = new Scanner(System.in);

    /**
     * Methode main du programme
     * @param args the command line arguments
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException, Exception {
        GestionFormation gesFor = new GestionFormation();
        gesFor.menu();
    }
    
    
    /**
     * Menu principal du programme qui va renvoyer vers d'autres menus
     * @throws SQLException 
    */
    public void menu() throws SQLException, Exception{
        String men[] = {"Locaux","Formations","Cours","Fin"}; 
        int flag = 0;
        int ans = 0;
        do{
            for(int i = 0; i < men.length; i++){
                System.out.println((i+1)+": "+men[i]);
            }
            System.out.print("Que voulez vous faire ? ");
            ans = sc.nextInt();
            switch(ans){
                case 1: GestionLocaux locD = new GestionLocaux();
                        locD.menu();
                        break;
                case 2: FormationDAO forD = new FormationDAO();
                        forD.menu();
                        break;
                case 3: GestionCours coursD = new GestionCours();
                        coursD.menu();
                        break; 
                case 4: flag = 1;
                default: flag = 1;
            }
           
        }while(flag == 0);
    }
}