//Ceci importe la classe Scanner du package java.util
import java.util.Scanner;
//Ceci importe toutes les classes du package java.util
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.lang.ArrayIndexOutOfBoundsException;

import Jeu.*;
import IHM.*;

public class Test {


	private static StockageJeu jeu;
	private static Assam pion;
	private static int tour;
	private static Joueur[] joueurs;


	public static void main(String[] args){
		jeu = StockageJeu.initialize(3);
		pion = jeu.getAssam();
		tour = 0;
		joueurs = jeu.getJoueurs();
		fonctionASo();
		

	}


	private static void fonctionABen(){
		while(true){
			if(tour <4){
				tour++;
			}
			else{
				tour =1;
			}

			afficherJeu();

			String[] direction = {"Aucune", "G", "D", "H", "B"};
			System.out.println("Direction de assam: "+direction[pion.getDirection()]);
			int n;
			while ((n = demanderNbDeplacement()) == -1 );
			System.out.println("On lance le de, vous obtenez: "+ n+"\nChoisissez une direction sans faire demi tour!");
			deplacerAssam(n);
			verifPayement();


			
		}
	}

	private static void verifPayement(){
		int x,y;
		x=pion.getXPion();
		y=pion.getYPion();
		int caseInfoTapis = jeu.cases[x][y].getCouleurTapis();
		if(caseInfoTapis == tour || caseInfoTapis == 0){
			return;
		}
		else{
			Joueur payeur = joueurs[tour];
			Joueur paye = joueurs[caseInfoTapis];
		//	pion.payerVraimentDime(payeur,paye,jeu.payerDime(caseInfoTapis,x,y,0,new boolean[7][7]));
		}
	}

	private static void deplacerAssam(int n){
		while(true){
			int d;
			
			d = obtenirDirection();
			if(pion.deplacerAssam(n, d)){

				System.out.println("\n\n");
				System.out.println("Assam est en pos " + pion.getXPion() + "  " + pion.getYPion());
				System.out.println("Assam est sur un tapis :" + jeu.cases[pion.getXPion()-1][pion.getYPion()-1].getCouleurTapis());	
				afficherJeu();
				while(!poserTapis());
	
				break;	
			} else {
				System.out.println("Vous ne pouvez pas choisir cette direction!");
			}




		}
	}


	/**
	*
	*/
	private static int obtenirDirection(){
		while(true){
				Scanner sc = new Scanner(System.in);
	 			String tmp = sc.nextLine();
	 			
				if(tmp.equals("g")){
					return 1;
				} else if(tmp.equals("d")){
					return 2;
				} else if(tmp.equals("h")){
					return 3;
				} else if(tmp.equals("b")){
					return 4;
				} else {
					System.out.println("Tapez g, d, h, b");
				}
			} 
	}

	
	private static void afficherJeu(){
		for(int i = 0; i < 7; i++){
				for (int j = 0; j < 7; j++){

					if(pion.getXPion()-1 == i && pion.getYPion()-1 == j){
						System.out.print("A ");
					} else {
						System.out.print(jeu.cases[i][j].getCouleurTapis()+" ");
					}
				}
				System.out.print("\n");
			}
	}

	private static void fonctionASo(){
		Fenetre f = new Fenetre();
		Graphique G = new Graphique(jeu);
		f.add(G);
		f.addKeyListener(new Clavier(jeu.getAssam(),G));
	}

	public static int demanderNbDeplacement(){

		System.out.println("Choisissez le nombre de deplacement entre 1 et 4");
		Scanner sc = new Scanner(System.in);
	 	String tmp = sc.nextLine();
	 	try{
	 		while(true){
	 			int n = Integer.parseInt(tmp);
	 			if(n>=1 && n<=4){
	 				return n;
	 			}
	 			System.out.println("NON, mettez un entier entre 1 et 4");
	 		}
	 		

	 	} catch (NumberFormatException e){
	 		System.out.println("Mettez un nombre!");
	 		return -1;
	 	}



	}

	private  static boolean poserTapis(){
		System.out.println("Choisisser une premiere case pour poser tapis");
	  	int premiereCaseTapis = obtenirDirection();
	  	int direction = premiereCaseTapis;
	  	int posXTapis = 0, posYTapis = 0;

	  	try{
		  	if(premiereCaseTapis == 1){
		  		jeu.cases[pion.getYPion()-1][pion.getXPion()-2].setCouleurTapis(1);
		  		posXTapis = pion.getXPion()-2;
		  		posYTapis = pion.getYPion()-1;
		  	} 

		  	else if(premiereCaseTapis == 2){
		  		jeu.cases[pion.getYPion()-1][pion.getXPion()].setCouleurTapis(1);
		  		posXTapis = pion.getXPion();
		  		posYTapis = pion.getYPion()-1;
		  	}

		  	else if(premiereCaseTapis == 3){
		  		jeu.cases[pion.getYPion()-2][pion.getXPion()-1].setCouleurTapis(1);
		  		posYTapis = pion.getYPion()-2;
		  		posXTapis = pion.getXPion()-1;
		  	} 

		  	else {
		  		jeu.cases[pion.getYPion()][pion.getXPion()-1].setCouleurTapis(1);
		  		posYTapis = pion.getYPion();
		  		posXTapis = pion.getXPion()-1;
		  	}
	  	} catch (ArrayIndexOutOfBoundsException e){
	  		System.out.println("Vous ne pouvez pas placer un tapis en dehors du jeu");
	  		return false;
	  	}

	  	


	  	afficherJeu();
	  	System.out.println("Choisissez la deuxieme case");

	  	while(true){
	  		int d = obtenirDirection();
	  		//pour ne pas faire de demi tour ou erreur de d
			if(direction == 1 &&  d == 2 || direction == 2 && d == 1 || direction == 3 && d == 4 || direction == 4 && d == 3 || d<1 || d>4){
				System.out.println("Impossible de choisir cette case!");
			} else {
				try{
				  	if(d == 1){
				  		jeu.cases[posXTapis][posYTapis-1].setCouleurTapis(1);
				  	} 

				  	else if(d == 2){
				  		jeu.cases[posXTapis][posYTapis+1].setCouleurTapis(1);
				  	}

				  	else if(d == 3){
				  		jeu.cases[posXTapis-1][posYTapis].setCouleurTapis(1);
				  	} 

				  	else {
				  		jeu.cases[posXTapis+1][posYTapis].setCouleurTapis(1);
			  		}
			  		break;
	  			} catch (ArrayIndexOutOfBoundsException e){
			  		System.out.println("Vous ne pouvez pas placer le second tapis en dehors du jeu");
	  			}

			}

	  	}

	  	return true;


	}
}






class Fenetre extends JFrame{
	public Fenetre(){
		super();
		this.setSize(675,675);
		this.setLocation(0,0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
