package Jeu;

public class Lampe{
  /**
  *	Position en X de Lampe entre 1 et 7
  */
  private static int posXPion;

  /**
  *	Position Y de Lampe entre 1 et 7
  */
  private static int posYPion;

  /**
  *	Constructeur
  */
  public Lampe(){
   this.posXPion = 0;
   this.posYPion = 0;
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
  *Methode pour changer les pos X et Y de la lampe
  * @param posXPion
  * @param posYPion
  */
  public void setPosition(int x, int y){
    this.posXPion = x;
    this.posYPion = y;
  }

  /**
   * Methode pour verifier que les coordonées du joueur sont les même que celle de la lampe
   * @param x position du assam x
   * @param y position de assam y
   * @return true si la lampe et assam sont sur la même case, sinon false
  */
  public boolean verifierJoueur(int x, int y){
    if(this.posXPion == x && this.posYPion == y){
      return true;
    }
    return false;
  }

}
