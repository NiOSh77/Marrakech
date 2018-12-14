JC = javac
JVM = java
JCLASSDIR= jbin
CD = cd

.SUFFIXES: .java .class

.java.class:
	$(JC) -d $(JCLASSDIR) $*.java

MAIN = Main

### REGLES ESSENTIELLES ###

Main.class : Main.java Jeu/JeuVConsole.class
Jeu/Assam.class : Jeu/Assam.java
Jeu/Lampe.class : Jeu/Lampe.java
Jeu/GestionConsole.class : Jeu/GestionConsole.java Jeu/StockageJeu.class
Jeu/Case.class : Jeu/Case.java
Jeu/Joueur.class : Jeu/Joueur.java
Jeu/Partie.class : Jeu/Partie.java
Jeu/StockageJeu.class : Jeu/StockageJeu.java Jeu/Assam.class Jeu/Case.class Jeu/Joueur.class
Jeu/JeuVConsole.class : Jeu/JeuVConsole.java Jeu/StockageJeu.class IHM/Graphique.class IHM/Clavier.class
IHM/Graphique.class: IHM/Graphique.java
IHM/Clavier.class: IHM/Clavier.java


run:
	$(CD) $(JCLASSDIR); $(JVM) $(MAIN)

clean:
	$(CD) $(JCLASSDIR);$(RM) *.class; $(CD) Jeu; $(RM) *.class