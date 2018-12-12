package Jeu;

//Ceci importe la classe Scanner du package java.util
import java.util.Scanner;
//Ceci importe toutes les classes du package java.util
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.lang.ArrayIndexOutOfBoundsException;

import IHM.*;



public class JeuVConsole{


	private static StockageJeu jeu;
	private static Assam pion;
	private static int tour;
	private static Joueur[] joueurs;
	private static int joueurElimine;


	public JeuVConsole(){
		jeu = StockageJeu.initialize(3);
		pion = jeu.getAssam();
		tour = 0;
		joueurs = jeu.getJoueurs();
		joueurElimine=0;
		fonctionABen();


	}

	public static void passerTour(){
		if(tour<joueurs.length){
			tour++;
		}
		else {
			tour = 1;
		}
		for(int i=0;i<joueurElimine;i++){
			if(joueurs[tour].getMonnaie()==0){
				if(tour<joueurs.length){
					tour++;
				}
				else {
					tour = 1;
				}
			}
		}
	}

	private static void fonctionABen(){

		while(true){
			passerTour();
			System.out.println("Joueur "+tour+" joue");

			afficherJeu();

			String[] direction = {"Aucune", "G", "D", "H", "B"};
			System.out.println("Direction de assam: "+direction[pion.getDirection()]);
			int n;
			while ((n = demanderNbDeplacement()) == -1 );
			System.out.println("On lance le de, vous obtenez: "+ n+"\nChoisissez une direction sans faire demi tour!");
			deplacerAssam(n);
			

			if(joueurElimine == 3){
				passerTour();
				System.out.println("gg joueur" +tour);
				return;
			}

		}
	}

	private static void verifPayement(){
		int x,y, dime;
		x=pion.getXPion()-1;
		y=pion.getYPion()-1;
		int caseInfoTapis = jeu.cases[x][y].getCouleurTapis();
		if(caseInfoTapis == tour || caseInfoTapis == 0){
			return;
		}
		else{
			dime = jeu.payerDime(caseInfoTapis,x,y,0,new boolean[7][7]);
			Joueur payeur = joueurs[tour-1];
			Joueur paye = joueurs[caseInfoTapis-1];
			jeu.payerVraimentDime(payeur,paye,dime);
			System.out.println("Joueur "+ (payeur.getNumJoueur()+1) + " paye "+dime +" au joueur "+(paye.getNumJoueur()+1));
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
				verifPayement();
				while(!poserTapis());

				break;
			} else {
				System.out.println("Vous ne pouvez pas choisir cette direction!");
			}




		}
	}


	/**
	*	Permet d'obtenir la direction sur la ligne de commande
	*	@return 1 Gauche
	*	@return 2 droite
	*	@return 3 Haut
	*	@return 4 Bas
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

	/**
	*	Affiche l'etat actuel du jeu
	*/
	private static void afficherJeu(){
		for(int i = 0; i < 7; i++){
				for (int j = 0; j < 7; j++){

					if(pion.getXPion()-1 == j && pion.getYPion()-1 == i){
						System.out.print("A ");
					} else {
						System.out.print(jeu.cases[j][i].getCouleurTapis()+" ");
					}
				}
				System.out.print("\n");
			}
	}


	/**
	*	Permet d'obtenir a la ligne de commande le nombre de deplacement
	*	@return n Nombre choisit sur la ligne de commande
	*	@return -1 Si ce n'est pas un nombre qui a ete ecrit sur la ligne de commande
	*/
	public static int demanderNbDeplacement(){
		try{
	 		while(true){
	 			System.out.println("Choisissez le nombre de deplacement entre 1 et 4");
	 			Scanner sc = new Scanner(System.in);
	 			String tmp = sc.nextLine();
	 			int n = Integer.parseInt(tmp);
	 			if(n>=1 && n<=4){
	 				return n;
	 			}
	 			System.out.println("Mettez un entier entre 1 et 4!");
	 		}


	 	} catch (NumberFormatException e){
	 		System.out.println("Mettez un nombre!");
	 		return -1;
	 	}



	}


	/**
	*	Permet de poser les tapis
	*	@return true 
	*	@return false si le joueur met un tapis en dehors du jeu
	*/
	private  static boolean poserTapis(){
		System.out.println("Choisisser une premiere case pour poser tapis");
	  	int premiereCaseTapis = obtenirDirection();
	  	int direction = premiereCaseTapis;
	  	int posXTapis = 0, posYTapis = 0;
	  	System.out.println(pion.getXPion());

	  	try{
		  	if(premiereCaseTapis == 1){
		  		jeu.cases[pion.getXPion()-2][pion.getYPion()-1].setCouleurTapis(tour);
		  		posXTapis = pion.getXPion()-2;
		  		posYTapis = pion.getYPion()-1;
		  	}

		  	else if(premiereCaseTapis == 2){
		  		jeu.cases[pion.getXPion()][pion.getYPion()-1].setCouleurTapis(tour);
		  		posXTapis = pion.getXPion();
		  		posYTapis = pion.getYPion()-1;
		  	}

		  	else if(premiereCaseTapis == 3){
		  		jeu.cases[pion.getXPion()-1][pion.getYPion()-2].setCouleurTapis(tour);
		  		posYTapis = pion.getYPion()-2;
		  		posXTapis = pion.getXPion()-1;
		  	}

		  	else {
		  		jeu.cases[pion.getXPion()-1][pion.getYPion()].setCouleurTapis(tour);
		  		posYTapis = pion.getYPion();
		  		posXTapis = pion.getXPion()-1;
		  	}

		  	System.out.println("X: " +posXTapis + " Y: "+ posYTapis);
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
				  		jeu.cases[posXTapis-1][posYTapis].setCouleurTapis(tour);
				  	}

				  	else if(d == 2){
				  		jeu.cases[posXTapis+1][posYTapis].setCouleurTapis(tour);
				  	}

				  	else if(d == 3){
				  		jeu.cases[posXTapis][posYTapis-1].setCouleurTapis(tour);
				  	}

				  	else {
				  		jeu.cases[posXTapis][posYTapis+1].setCouleurTapis(tour);
			  		}
			  		break;
	  			} catch (ArrayIndexOutOfBoundsException e){
			  		System.out.println("Vous ne pouvez pas placer le second tapis en dehors du jeu");
	  			}

			}

	  	}

	  	return true;


	}




	/*private static void fonctionASo(){
		Fenetre f = new Fenetre();
		Graphique G = new Graphique(jeu);
		f.add(G);
		f.addKeyListener(new Clavier(jeu.getAssam(),G));
	}*/
}






/*class Fenetre extends JFrame{
	public Fenetre(){
		super();
		this.setSize(675,675);
		this.setLocation(0,0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}*/
