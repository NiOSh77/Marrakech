JC = javac
JVM = java
JCLASSDIR= jbin
CD = cd

.SUFFIXES: .java .class

.java.class:
	$(JC) -d $(JCLASSDIR) $*.java

MAIN = Test

### REGLES ESSENTIELLES ###

Test.class : Test.java Jeu/StockageJeu.class
Jeu/Assam.class : Jeu/Assam.java 
Jeu/Case.class : Jeu/Case.java 
Jeu/Joueur.class : Jeu/Joueur.java 
Jeu/Partie.class : Jeu/Partie.java 
Jeu/StockageJeu.class : Jeu/StockageJeu.java Jeu/Assam.class Jeu/Case.class Jeu/Joueur.class 


run:
	$(CD) $(JCLASSDIR); $(JVM) $(MAIN)

clean:
	$(CD) $(JCLASSDIR);$(RM) *.class; $(CD) Jeu; $(RM) *.class