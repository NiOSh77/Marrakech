package Jeu;

/**
*Classe Joueur sert a contenir les informations sur les differents joueurs de la partie
*@author Groupe Ranto
*/
public class Joueur{

  /**
  *Numero du joueur
  *De 0 à 3
  */
  private int numJoueur;

  /**
  *L'argent que possede le joueurs
  */
  private int monnaie;

  /**
  *Le nombre de tapis restants
  */
  private int tapisRest;

  /**
  *
  */
  private int couleur;

  /**
  *Le constructeur fait correspondre les variables au dessus
  *@param n Numero du joueur
  *@param m Monaie du joueur
  *@param t Nombre de tapis restant
  *@param c Couleur du joueur 
  * 1 = rouge
  * 2 = bleu
  * 3 = vert
  * 4 = jaune
  */
  public Joueur(int n, int m, int t, int c){
    this.numJoueur=n;
    this.monnaie=m;
    this.tapisRest=t;
    this.couleur=c;
  }

  /**
  *Accesseur en lecture de numJoueur
  *@return numJoueur
  */
  public int getNumJoueur(){
    return this.numJoueur;
  }

  /**
  *Accesseur en ecriture de numJoueur
  *@param numJoueur
  */
  public void setNumJoueur(int n){
    this.numJoueur = n;
  }

  /**
  *Accesseur en lecture de monnaie
  *@return monnaie
  */
  public int getMonnaie(){
    return this.monnaie;
  }

  /**
  *Accesseur en ecriture de monnaie
  *@param monnaie
  */
  public void setMonnaie(int m){
    this.monnaie = m;
  }

  /**
  *Accesseur en lecture de tapisRest
  *@return tapisRest
  */
  public int getTapisRest(){
    return this.tapisRest;
  }

  /**
  *Accesseur en ecriture de tapisRest
  *@param tapisRest
  */
  public void setTapisRest(int t){
   this.tapisRest = t;
  }

  /**
  *
  */
  public int getCouleur(){
    return couleur;
  }

  /**
  *
  */
  public void setCouleur(int c){
    couleur = c;
  }

}
