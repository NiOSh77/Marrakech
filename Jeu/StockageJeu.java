package Jeu;

/**
*	Classe represenant l'etat du jeu'
*	@author Sofian
*/

public class StockageJeu{

	/**
	*	Classe du jeu
	*/
	private static StockageJeu jeu = null;

	/**
	*	Represente les joueurs
	*/
	private static Joueur[] joueurs;

	/**
	*	Represente Assam le pion
	*/
	private static Assam pion;

	/**
	*	Represente la lampe
	*/
	private Lampe lampe;

	/**
	*	Represente la grille du jeu
	*/
	public static Case[][] cases;

	/**
	*	Constructeur
	*/
	private StockageJeu(int nbJoueur){

		//initialise la grille
		cases = new Case[7][7];
		initialiseCases();
		lampe = new Lampe();

		//initialise les joueurs
		joueurs = new Joueur[nbJoueur];
		initialiseJoueurs(nbJoueur);

		//initialise Assam
		pion = new Assam(lampe);

	}

	/**
	*	Methode pour initialiser la grille
	*/
	private void initialiseCases(){
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				cases[i][j] = new Case();
			}
		}
	}

	/**
	*	Methode pour initialiser les joueurs
	*/
	private void initialiseJoueurs(int nbJoueurs){
		for(int i = 0; i < nbJoueurs; i++){
			joueurs[i] = new Joueur(i, 2, 3, i+1);
		}
	}


	/**
	*	Singleton
	*	@param nb nombre de joueur
	*	@return jeu qui est unique
	*/
	public static StockageJeu initialize(int nb){
		if(jeu == null){
			jeu = new StockageJeu(nb);
		}
		return jeu;
	}

	/**
	*Methode pour calculer la dime
	*@param couleur c'est la couleur du tapis sur lequel assam est
	*@param x on met la position x de assam
	*@param y on met la position y de assam
	*@param dime on met la dime a 0;
	*@param visited on cree un tableau de booleen avec new
	*/
	public int payerDime(int couleur, int x, int y, int dime, boolean[][] visited){
		dime++;
		visited[x][y] = true;
		if(x<6){
			if(cases[x+1][y].getCouleurTapis()==couleur && !visited[x+1][y]){
				dime = payerDime(couleur,x+1,y,dime,visited);
			}
		}

		if(x>0){
			if(cases[x-1][y].getCouleurTapis()==couleur && !visited[x-1][y]){
				dime = payerDime(couleur,x-1,y,dime,visited);
			}
		}
		if(y<6){
			if(cases[x][y+1].getCouleurTapis()==couleur && !visited[x][y+1]){
				dime = payerDime(couleur,x,y+1,dime,visited);
			}
		}
		if(y>0){
			if(cases[x][y-1].getCouleurTapis()==couleur && !visited[x][y-1]){
				dime = payerDime(couleur,x,y-1,dime,visited);
			}
		}

		return dime;
	}


	/**
	*	Transfert l'argent entre deux joueurs
	*/
	public boolean payerVraimentDime(Joueur payeur, Joueur paye, int dime){
		if(dime>=payeur.getMonnaie()){
			paye.setMonnaie(paye.getMonnaie()+payeur.getMonnaie());
			payeur.setMonnaie(0);
			enleverTapisJoueur(payeur);
			payeur.setTapis(0);
			return true;
		}
		else{
			paye.setMonnaie(paye.getMonnaie()+dime);
			payeur.setMonnaie(payeur.getMonnaie()-dime);
			return false;
		}
	}

	/**
	* Enleve les tapis d'un joueur
	*/
	private void enleverTapisJoueur(Joueur joueur){
		for(int i = 0; i < 7; i++){
			for(int j = 0; j < 7; j++){
				if(cases[i][j].getCouleurTapis() == joueur.getCouleur()){
					cases[i][j].setCouleurTapis(0);
				}
			}
		}
	}

	/**
	*	Methode pour obtenir Assam
	*	@return pion
	*/
	public Assam getAssam(){
		return pion;
	}

	/**
	*	Methode pour obtenir la liste des joueurs
	*	@return joueurs
	*/
	public static Joueur[] getJoueurs(){
		return joueurs;
	}

	/**
	*	Obtenir la lampe
	*/
	public Lampe getLampe(){
		return lampe;
	}

	/**
	*	Permet d'obtenir le nombre de tapis/case d'un joueur
	*	@param joueur
	*	@return compte
	*/
	public int obtenirNombreDeTapis(Joueur joueur){
		int compte = 0;
		for(int i = 0; i<7; i++){
			for(int j = 0; j<7; j++){
				if(cases[i][j].getCouleurTapis() == joueur.getNumJoueur()){
					compte ++;
				}
			}
		}
		return compte;
	}






}
