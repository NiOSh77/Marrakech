package Jeu;
import java.util.Random;

public class Assam{

	/**
	*	Position en X de Assam entre 1 et 7
	*/
	private int posXPion;

	/**
	*	Direction de Assam
	*	0 = aucune direction
	*	1 = gauche
	*	2 = droite
	*	3 = haut
	*	4 = bas
	*/
	private int direction;

	/**
	*	Position Y de Assam entre 1 et 7
	*/
	private int posYPion;

	/**
	*	Lampe du jeu
	*/
	private Lampe lampe;

	/**
	*	Constructeur
	*	@param lampe
	*/
	public Assam(Lampe lampe){
		posXPion = 4;
		posYPion = 4;
		direction = 0;
		this.lampe = lampe;
	}


	/**
	*	Methode pour deplacer en X le pion
	*	@param b 	true on incremente de 1 posXPion
	*				/false on decremente de 1 posXPion
	*/
	public void changePosXPion(boolean b){
		if(b){
			posXPion++;
			direction=2;
		} else {
			posXPion--;
			direction=1;
		}

		if(posXPion > 7 || posXPion < 1){
			changement();
		}

	}

	/**
	*	Methode pour deplacer en Y le pion
	*	@param b 	true on incremente de 1 posYPion
	*				/false on le decremente
	*/
	public void changePosYPion(boolean b){
		if(b){
			posYPion++;
			direction=4;
		} else {
			posYPion--;
			direction=3;
		}

		if(posYPion > 7 || posYPion < 1){
			changement();
		}
	}

	/**
	*	Methode pour changer la position de Assam sur les bords
	*/
	private void changement(){
		if(posYPion == 0){
			if(posXPion < 7){
				if(posXPion%2 == 1){
					posXPion++;
					//
				} else {
					posXPion--;
					//
				}
				direction=4;
			}
			else {
				direction=1;
			}
			posYPion++;
		}
		else if(posYPion == 8){
			if(posXPion > 1){
				if(posXPion%2 == 0){
					posXPion++;
					//
				} else{
					posXPion--;
					//
				}
				direction=3;
			}
			else {
				direction=2;
			}
			posYPion--;
		}
		else if(posXPion == 0){
			if(posYPion < 7){
				if(posYPion%2 == 1){
					posYPion++;
					//
				} else {
					posYPion--;
					//
				}
				direction=2;
			}
			else {
				direction=3;
			}
			posXPion++;
		}
		else if(posXPion == 8){
			if(posYPion > 1){
				if(posYPion%2 == 0){
					posYPion++;
					//
				} else{
					posYPion--;
					//
				}
				direction=1;
			}
			else {
				direction=4;
			}
			posXPion--;
		}
	}

	/**
	*	Methode pour obtenir pos X du pion
	*	@return posXPion
	*/
	public int getXPion(){
		return posXPion;
	}

	/**
	*	Methode pour obtenir pos Y du pion
	*	@return posYPion
	*/
	public int getYPion(){
		return posYPion;
	}

	/**
	* Methode pour obtener Direction
	*@return direction
	*/
	public int getDirection(){
		return this.direction;
	}

	/**
	*	Methode pour deplacer Assam de n case
	*	@param n Nombre de deplacement
	*	@param d Direction de Assam
	*	@return false quand Assam ne peut pas se deplacer
	*	@return true quand assam peut se deplacer
	*/
	public boolean deplacerAssam(int n, int d){

		//pour ne pas faire de demi tour ou erreur de d
		if(direction == 1 &&  d == 2 || direction == 2 && d == 1 || direction == 3 && d == 4 || direction == 4 && d == 3 || d<1 || d>4){
			return false;
		}

		direction = d;

		for(int i = 0; i<n; i++){
			if(direction == 1){
				changePosXPion(false);
			} else if(direction == 2){
				changePosXPion(true);
			} else if(direction == 3){
				changePosYPion(false);
			} else {
				changePosYPion(true);
			}
		}

		return true;
	}


}
