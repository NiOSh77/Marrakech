package Jeu;

//Ceci importe la classe Scanner du package java.util
import java.util.Scanner;
//Ceci importe toutes les classes du package java.util
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.lang.ArrayIndexOutOfBoundsException;

/**
*	Gere le jeu dans la console
*	@author
*/

public class JeuVConsole{

	/**
	*	Donnees du jeu
	*/
	private static StockageJeu jeu;

	/**
	*	Pour gerer les affichages et demandes a la console
	*/
	private static GestionConsole console;

	/**
	*	Permet de savoir qui joue
	*	Prend une valeur entre 1 et 4
	*/
	private static int tour;

	/**
	*	Nombre de joueurs elimines
	*/
	private static int joueurElimine;

	/**
	*	Constructeur
	*/
	public JeuVConsole(){
		for(int i = 0; i < 8; i++){
			System.out.println("*");
		}

		console.afficherMessageBienvenu();
		jeu = StockageJeu.initialize(console.demanderNbJoueurs());
		console = new GestionConsole(jeu);
		tour = 0;
		joueurElimine=0;

		tourJeu();

	}

	/**
	*	Change valeur de tour pour savoir qu'elle joueur joue
	*/
	public static void passerTour(){
		if(tour<jeu.getJoueurs().length){
			tour++;
		}
		else {
			tour = 1;
		}
		for(int i=0;i<joueurElimine;i++){
			if(jeu.getJoueurs()[tour].getMonnaie()==0){
				if(tour<jeu.getJoueurs().length){
					tour++;
				}
				else {
					tour = 1;
				}
			}
		}
	}

	/**
	*	Methode principale du jeu qui correspond a un tour de jeu
	*/
	private static void tourJeu(){

		while(true){
			console.afficherEtatJoueurs();
			passerTour();
			console.quelJoueurJoue(tour);
			console.afficherJeu();
			console.afficherDirectionAssam();

			int n;
			while ((n = console.demanderNbDeplacement()) == -1 );

			console.afficherDirectionChoisie(n);
			demanderDeplacerAssam(n);


			if(joueurElimine == jeu.getJoueurs().length-1){
				passerTour();
				console.afficherGagnant(tour);
				return;
			}

			if(verifTapis()){
				statistique();
				return;
			}


		}
	}

	/**
	* Statistique à la fin de la partie
	*/
	private void statistique(){
		for(int i = 0; i<7; i++){
			for(int j = 0; j<7; j++){
				for(int l=0; l<jeu.getJoueurs().length; l++){
					if(jeu.cases[i][j].getCouleurTapis() == jeu.getJoueurs()[l].getNumJoueur()){
						jeu.getJoueurs()[l].setTapis(jeu.getJoueurs()[l].getTapisRest()+1);
					}
				}
			}
		}
		Joueur j;
		System.out.println("// Fin de la partie //");
		for(int i=0; i<jeu.getJoueurs().length; i++) {
			j =jeu.getJoueurs()[i];
			System.out.println("Joueur "+j.getNumJoueur()+" a obtenue "+j.getTapisRest()+j.getMonnaie()+" points" );
		}
	}

	/**
	*	Verifie si les joueurs ont des tapis
	*	@return false Si au moins un joueur a un tapis
	*	@return true Si tous les joueurs n'ont plus de tapis
	*/
	private static boolean verifTapis(){
		int compte = 0;
		for(int i = 0; i<jeu.getJoueurs().length; i++){
			if(jeu.getJoueurs()[i].getTapisRest()==0 || jeu.getJoueurs()[i].getMonnaie()==0){
				compte++;
			}
		}
		if(compte == jeu.getJoueurs().length){
			return true;
		}
		return false;
	}

	/**
	*	Verifie s'il le joueur actuel tombe sur un tapis et paye en consequence
	*/
	private static void verifPayement(){
		int x,y, dime;
		x=jeu.getAssam().getXPion()-1;
		y=jeu.getAssam().getYPion()-1;
		int caseInfoTapis = jeu.cases[x][y].getCouleurTapis();
		if(caseInfoTapis == tour || caseInfoTapis == 0){
			return;
		}
		else{
			dime = jeu.payerDime(caseInfoTapis,x,y,0,new boolean[7][7]);
			Joueur payeur = jeu.getJoueurs()[tour-1];
			Joueur paye = jeu.getJoueurs()[caseInfoTapis-1];
			jeu.payerVraimentDime(payeur,paye,dime);
			console.afficherPayeurPaye(payeur, paye, dime);
		}
	}


	/**
	*	Methode pour demander la direction pour deplacer assam et afficher des messages dans la console
	*	@param n Nombre de deplacements
	*/
	private static void demanderDeplacerAssam(int n){
		while(true){
			int d;

			d = console.obtenirDirection();
			if(jeu.getAssam().deplacerAssam(n, d)){

				console.afficherPosAssam();
				console.afficherSurQuelTapisEstAssam();
				console.afficherJeu();
				verifPayement();
				while(!poserTapis());

				break;
			} else {
				console.afficherImpossibleChoisirDirection();

			}




		}
	}

	/**
	*	Permet de poser les tapis en demandant la direction dans la console
	*	@return true
	*	@return false si le joueur met un tapis en dehors du jeu
	*/
	private  static boolean poserTapis(){
		System.out.println("Choisisser une premiere case pour poser la premiere partie du tapis");
		int premiereCaseTapis = console.obtenirDirection();
		int direction = premiereCaseTapis;
		int posXTapis = 0, posYTapis = 0;

		//Pose du premier carre de tapis
		try{
			if(premiereCaseTapis == 1){
				jeu.cases[jeu.getAssam().getXPion()-2][jeu.getAssam().getYPion()-1].setCouleurTapis(tour);
				posXTapis = jeu.getAssam().getXPion()-2;
				posYTapis = jeu.getAssam().getYPion()-1;
			}

			else if(premiereCaseTapis == 2){
				jeu.cases[jeu.getAssam().getXPion()][jeu.getAssam().getYPion()-1].setCouleurTapis(tour);
				posXTapis = jeu.getAssam().getXPion();
				posYTapis = jeu.getAssam().getYPion()-1;
			}

			else if(premiereCaseTapis == 3){
				jeu.cases[jeu.getAssam().getXPion()-1][jeu.getAssam().getYPion()-2].setCouleurTapis(tour);
				posYTapis = jeu.getAssam().getYPion()-2;
				posXTapis = jeu.getAssam().getXPion()-1;
			}

			else {
				jeu.cases[jeu.getAssam().getXPion()-1][jeu.getAssam().getYPion()].setCouleurTapis(tour);
				posYTapis = jeu.getAssam().getYPion();
				posXTapis = jeu.getAssam().getXPion()-1;
			}

		} catch (ArrayIndexOutOfBoundsException e){
			console.afficherImpossiblePoserTapis();
			return false;
		}




		console.afficherJeu();
		System.out.println("Choisissez la deuxieme case");

		//Pose du second petit carre de tapis
		while(true){
			int d = console.obtenirDirection();
			//pour ne pas faire de demi tour ou erreur de d
			if(direction == 1 &&  d == 2 || direction == 2 && d == 1 || direction == 3 && d == 4 || direction == 4 && d == 3 || d<1 || d>4){
				console.afficherImpossibleChoisirDirection();
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
					console.afficherImpossiblePoserTapis();
				}

			}

		}
		jeu.getJoueurs()[tour-1].useTapis();
		return true;


	}
}
