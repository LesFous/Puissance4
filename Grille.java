import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

/**
* Classe qui represente un grille de puissance 4
*
*/
public class Grille implements Serializable{
  /**
  * attribut qui represente toutes les colonnes de la grille
  */
  private List<Colonne> colonnes;

  /**
  * attibut qui représente les joueurs s'affontrant dans une grille
  */
  private Joueur[] joueurs;

  /**
  * attribut pour savoir qui a gagné (le numéro de joueur)
  * -1 si la partie n'est pas finie
  */
  private int gagnant;

  /**
  * Constructeur qui permet de créer une grille d'une taille donnee
  *
  * @param nb_colonnes le nombre de colonnes
  */
  public Grille(int nb_colonnes) {
    if(nb_colonnes <= 0)
      throw new NullPointerException("Impossible de créer un grille avec "+nb_colonnes);

    joueurs = null;
    gagnant = -1;
    colonnes = new ArrayList<Colonne>(nb_colonnes);
    for(int i=0; i<nb_colonnes; i++) {
      colonnes.add(new Colonne());
    }
  }

  /**
  * Permet d'obtenir la liste des colones triées de la plus remplie a la moins remplie
  *
  * @return la listes des colonnes triées
  */
  public ArrayList<Colonne> getColonnesTriees() {
    ArrayList<Colonne> copie = new ArrayList<Colonne>(colonnes);
    Collections.<Colonne>sort(copie, new ComparateurTaillesColonnes());
    return copie;
  }

  /**
  * Methode pour connaître le nombre de lignes d'une grille en fonction de sa colonne la plus remplie
  *
  * @return le nombre de lignes
  */
  public int getNbLignes() {
    return getColonnesTriees().get(0).size();
  }

  /**
  * Methode qui permet d'afficher la grille du jeu avec ses jetons
  *
  */
  public void afficher() {
    afficherSepLigne();
    Jeton jeton;
    String jeton_str;
    int nb_lignes = Math.max(getNbLignes(), 1);

    // Pour chaque ligne
    for(int j=nb_lignes-1; j>=0; j--) {
      // Pour chaque colonne
      for (int i=0; i<colonnes.size(); i++) {
        // On recupere le jeton s'il y en a un
        if(j >= colonnes.get(i).size())
          jeton = null;
        else {
          jeton = colonnes.get(i).getJeton(j);
        }
        // On recupere la représentation du jeton
        if(jeton == null)
          jeton_str = " ";
        else
          jeton_str = jeton.toString();

        System.out.print("|"+jeton_str);
      }
      System.out.println("|");
      afficherSepLigne();
    }
  }

  /**
  * Methode privee qui permet d'afficher la séparation entre deux lignes
  *
  */
  private void afficherSepLigne() {
    for(int i=0; i < colonnes.size(); i++) {
      System.out.print("+-");
    }
    System.out.println("+");;
  }

  /**
  * Methode privee qui permet d'ajouter un jeton a une colonne
  * Si le nombre de lignes de suffit pas, il est augmneté de un
  *
  * @param j Le jeton à ajouter (non null)
  * @param col Le numero de la colonne a laquelle on ajoute le Jeton (entre 0 et nb_colonnes compris)
  */
  private void ajouterJeton(Jeton j, int col) {
    if (col < 0 || col >= colonnes.size()) {
      throw new IndexOutOfBoundsException("La colonne n'est pas valide :"+col);
    }
    colonnes.get(col).ajouter(j);
  }

  /**
  * Méthode qui permet de verifier si le dernier jeton d'une colonne est un coup
  * qui a permis a une equipe de gagner
  *
  * @param col la colonne ou il faut verifier
  */
  private boolean verifierCoup(int col) {
    if(col < 0 || col >= colonnes.size()) {
      throw new IndexOutOfBoundsException("Impossible de verifier le coup pour la colonne "+col);
    }
    int j = colonnes.get(col).size()-1;
    int equipe = colonnes.get(col).getJeton(j).getTeamId();
    boolean gagnant = false;
    // On verifie verticalement
    if(j >= 3) { // Si la place le permet
      gagnant=true;
      for(int j2 = j-1; j2>=j-3; j2--) { // On regarde les 3 derniers jetons de la colonne apres celui qui vient d'être joué
        if(colonnes.get(col).getJeton(j2).getTeamId() != equipe) {
          gagnant = false;
        }
      }
    }

    // le nombre de max jetons qu'on peut regarder dans un direction
    int ne, no, se, so; // En diagonale
    int haut, droite, bas, gauche; // Horizontalement et verticalement
    int nb_lignes = getNbLignes();

    // Pour chaque diagonale on regarde le plus petit entre le nombre de jetons possibles
    // verticalement et horizontalement, on major ce nombre par 3 car pas besoin de plus
    ne = Math.min(colonnes.size() - col - 1, Math.min(nb_lignes - j - 1, 3));
    no = Math.min(nb_lignes - j - 1, Math.min(col, 3));
    se = Math.min(colonnes.size() - col -1, Math.min(j, 3));
    so = Math.min(col, Math.min(j, 3));
    // System.out.println("Possibilitées : ne("+ne+") no("+no+") se("+se+") so("+so+")");

    // horizontalement
    if(!gagnant) {
      gauche = Math.max(0, col-3);
      droite = Math.min(colonnes.size()-1, col+3);
      // System.out.println("\nH : gauche("+gauche+") droite("+droite+")");
      if(droite - gauche >= 3) // Si la place le permet
        gagnant = verifierIntervalle(gauche, droite, colonnes.get(col).size()-1, 0, equipe);
    }

    // Diagonale de SO à NE
    if(!gagnant) {
      gauche = col - so;
      droite = col + ne;
      haut = j + ne;
      bas = j - so;
      // System.out.println("\ndiag SO NE : gauche("+gauche+") droite("+droite+") haut("+haut+") bas("+bas+")");
      if(haut + bas >= 3 && droite - gauche >= 3) // Si la place le permet
        gagnant = verifierIntervalle(gauche, droite, bas, 1, equipe);
    }

    // Diagonale de NO à SE
    if(!gagnant) {
      gauche = col - no;
      droite = col + se;
      haut = j + no;
      bas = j - se;
      // System.out.println("\ndiag NO SE : gauche("+gauche+") droite("+droite+") haut("+haut+") bas("+bas+")");
      if(haut + bas >= 3 && droite - gauche >= 3) // Si la place le permet
        gagnant = verifierIntervalle(gauche, droite, haut, -1, equipe);
    }
    return gagnant;
  }


  /**
  * Fonction qui permet de verifier si un coup est gagnant sur un intervalle donné (bornes incluses)
  * En itérant de gauche à droite avec la possibilité de changer j à chaque itération
  *
  * @param gauche la colonne par laquelle on commence
  * @param droite la colonne jusqu'ou on peut regarder
  * @param j la valeur initiale de j
  * @param j_pas la valeur à ajouter à j à chaque itération
  * @param equipe qu'on s'interesse si elle a gagné
  *
  * @return si le coup est gagnant
  */
  private boolean verifierIntervalle(int gauche, int droite, int j, int j_pas, int equipe) {
    int nb = 0; // Le nombrede jetons de l'equipe consécutifs
    int i = gauche;
    // Tant qu'il est possible que 4 jetons soient alignés
    while(i+3-nb <= droite) {
      // System.out.print("nb("+nb+") i("+i+") j("+j+")   ");
      if(j >= colonnes.get(i).size() || colonnes.get(i).getJeton(j).getTeamId()!=equipe )
        nb = 0;
      else
        nb ++;

      if(nb == 4) {
        System.out.println("4 !");
        return true;
      }
      i++;
      j+=j_pas;
      if(j < 0) {// Si j décrémente en dessous de 0
        System.out.println("j -1");
        return false;
      }
    }
    return false;
  }

  /**
  * Methode pour definir les joueurs d'une partie
  */
  public void setJoueurs(Joueur[] joueurs) {
    this.joueurs = joueurs;
  }

  /**
  * Getter de l'attibut gagnant
  */
  public int getGagnant() {
    return gagnant;
  }


  /**
  * Methode qui permet de lancer une partie entre plusieurs joueurs
  *
  * @param joueurs Liste des joueurs qui jouent
  */
  public void faireJouerJoueurs() {
    if(gagnant != -1)
      throw new NullPointerException("Impossible de faire jouer les joueurs sur une partie déjà finie");
    if(joueurs == null || joueurs.length == 0)
      throw new NullPointerException("Impossible de faire une partie sans joueurs");
    for(Joueur j : joueurs) {
      if(j==null)
        throw new NullPointerException("Impossible de faire joueur un joueur 'null'");
    }

    int col;
    System.out.println("\n\n---- Etat de la partie ----");
    afficher();
    for(Joueur j : joueurs) {
      // On fait jouer le joueur
      System.out.println(j);
      col = j.jouer(colonnes.size());
      ajouterJeton(new Jeton(j.getTeamId()), col);
      // On affiche le résultat de son coup
      System.out.println("\nEtat du jeu :");
      afficher();
      // On verifie le coup joué
      if(verifierCoup(col))
        gagnant=j.getTeamId();
    }
  }
}
