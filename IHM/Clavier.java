package IHM;

import Jeu.*;

import java.awt.*;
import java.awt.event.*;

/**
* Classe pour gerer les deplacments de Assam au clavier
*/

public class Clavier implements KeyListener{

  /*
  * 
  */
  Assam pion;

  /*
  *
  */
  Graphique g;

  /**
  * Constructeur
  * @param p le pion
  * @param g
  */
  public Clavier(Assam p, Graphique g){
    this.pion = p;
    this.g = g;
  }


  /**
  * Methode pour gerer les deplacements de Assam avec les fleches dirrectionnelles
  * @param e
  */
  @Override
  public void keyPressed(KeyEvent e){
    if(e.getKeyCode() == KeyEvent.VK_LEFT){
      //fleche gauche
      pion.changePosXPion(false);
      g.repaint();
    }
    else if(e.getKeyCode() == KeyEvent.VK_UP){
      //fleche haut
      pion.changePosYPion(false);
      g.repaint();
    }
    else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
      //fleche droite
      pion.changePosXPion(true);
      g.repaint();
    }
    else if(e.getKeyCode() == KeyEvent.VK_DOWN){
      //fleche bas
      pion.changePosYPion(true);
      g.repaint();
    }

  }
  @Override
  public void keyReleased(KeyEvent e){
  }
  @Override
  public void keyTyped(KeyEvent e){
  }


}
