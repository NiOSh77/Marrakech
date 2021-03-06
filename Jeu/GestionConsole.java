package Jeu;

//Ceci importe la classe Scanner du package java.util
import java.util.Scanner;
//Ceci importe toutes les classes du package java.util
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.lang.ArrayIndexOutOfBoundsException;


/**
*	Gere l'affichage des elements dans la console et les demandes
*/
public class GestionConsole{

	private static StockageJeu jeu;

	public GestionConsole(StockageJeu jeu){
		this.jeu = jeu;
	}

	/**
	*	Permet de demander coordonnees 
	*/
	public static int demanderCoordonnees(){
		return 0;
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
	*	Affiche l'etat actuel du jeu
	*/
	public static void afficherJeu(){
		for(int i = 0; i < 7; i++){
				for (int j = 0; j < 7; j++){

					if(jeu.getAssam().getXPion()-1 == j && jeu.getAssam().getYPion()-1 == i){
						System.out.print("A ");
					} else {
						System.out.print(jeu.cases[j][i].getCouleurTapis()+" ");
					}
				}
				System.out.print("\n");
			}
	}

	/**
	*	Demande le nombre de joueur a la ligne de commande
	*	@return nb Le nombre de joueurs
	*/
	public static int demanderNbJoueurs(){
		while(true){
			try{
	 			System.out.println("Choisissez le nombre de joueurs entre 3 et 4");
	 			Scanner sc = new Scanner(System.in);
	 			String tmp = sc.nextLine();
	 			int n = Integer.parseInt(tmp);
	 			if(n>=3 && n<=4){
	 				return n;
	 			}
	 			System.out.println("Mettez un entier entre 3 et 4!");


	 		} catch (NumberFormatException e){
	 			System.out.println("Mettez un nombre!");
	 		}
	 	}

	}

	/**
	*	Affiche dans la console l'etat des joueurs
	*/
	public static void afficherEtatJoueurs(){
		for(int i = 0; i<jeu.getJoueurs().length; i++){
			System.out.println("Le joueur "+ (i+1) +" a "+ jeu.getJoueurs()[i].getMonnaie() + " pieces et " + jeu.getJoueurs()[i].getTapisRest() + " tapis");

		}
	}

	/**
	*	Permet d'obtenir la direction sur la ligne de commande
	*	@return 1 Gauche
	*	@return 2 droite
	*	@return 3 Haut
	*	@return 4 Bas
	*/
	public static int obtenirDirection(){
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
					System.out.println("Tapez g, d, h, b qui correspondent respectivement a gauche, droite, haut, bas");
				}
			}
	}

	/**
	*	Affiche quel joueur joue
	*	@param num Numero du joueur
	*/
	public void quelJoueurJoue(int num){
		System.out.println("C'est le tour du joueur " + num);
	}

	/**
	*	Affiche la direction de Assam
	*/
	public void afficherDirectionAssam(){
		String[] direction = {"Aucune", "Gauche", "Droite", "Haut", "Bas"};
		System.out.println("Direction de Assam: "+direction[jeu.getAssam().getDirection()]);
	}

	/**
	*	Affiche le gagnant
	*	@param num Numero du joueur
	*/
	public void afficherGagnant(int num){
		System.out.println("Bravo au joueur "+ num);
	}

	/**
	*	Affiche la position de Assam
	*/
	public void afficherPosAssam(){
		System.out.println("Assam est en pos " + jeu.getAssam().getXPion() + "  " + jeu.getAssam().getYPion());
	}

	/**
	*	Affiche la direction choisie par l'utilisateur
	*	@param dir Direction choisit par l'utilisateur
	*/
	public void afficherDirectionChoisie(int dir){
		System.out.println("Vous avez choisi: "+ dir +"\nChoisissez une direction sans faire demi tour!");
	}

	/**
	*	Affiche message de bienvenu
	*/
	public static void afficherMessageBienvenu(){
		System.out.println("Bienvenue dans le jeu du Marrakech, pour choisir une direction et vous deplacer, utilisez h pour haut, b pour bas, g pour gauche et d pour droite");
	}

	/**
	*	Affiche qui paye a qui
	*	@param payeur Celui qui paye
	*	@param paye Celui qui est paye
	*	@param dime La somme transferee
	*/
	public void afficherPayeurPaye(Joueur payeur, Joueur paye, int dime){
		System.out.println("Joueur "+ (payeur.getNumJoueur()+1) + " paye "+dime +" au joueur "+(paye.getNumJoueur()+1));
	}

	/**
	*	Affiche sur quel ptais est Assam
	*/
	public void afficherSurQuelTapisEstAssam(){
		System.out.println("Assam est sur un tapis: " + jeu.cases[jeu.getAssam().getXPion()-1][jeu.getAssam().getYPion()-1].getCouleurTapis());
	}

	/**
	*	Affiche un message sur le terminal pour montrer que l'utilisateur ne peut pas poser un tapis 
	*/
	public void afficherImpossiblePoserTapis(){
		System.out.println("Vous ne pouvez pas poser un tapis en dehors du jeu!");
	}

	/**
	*	Affiche que l'utilisateur ne peut pas choisir cette direction
	*/
	public void afficherImpossibleChoisirDirection(){
		System.out.println("Vous ne pouvez pas choisir cette direction!");
	}
}