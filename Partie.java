import java.util.ArrayList;
import java.io.*;

    /**
    * Classe qui reprensente la partie de puissance 4
    */
public class Partie implements Serializable{

  /**
  * attribut qui represente la grille de puissance 4
  */
  private Grille g;

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
  * attribut qui enregistre tous les coups (colonne) de chaque joueur dans une liste d'entiers
  */
  private ArrayList<Integer> historique;

  /**
  * Constructeur permettant de creer une partie avec une grille pasée en parametre
  * @param grille represente la grille de puissance 4
  */
  public Partie(Grille grille, Joueur[] p_joueurs) throws ArgumentInvalideException {
    if(grille == null)
      throw new ArgumentInvalideException("Impossible de faire une partie avec une grille 'null'");
    if(p_joueurs == null)
      throw new ArgumentInvalideException("Impossible de faire une partie avec joueurs 'null'");
    for(Joueur j: p_joueurs)
      if(j == null)
        throw new ArgumentInvalideException("Impossible de faire une partie avec un joueur 'null'");

    g= grille;
    joueurs= p_joueurs;
    gagnant = -1;
    historique = new ArrayList<Integer>();
  }

  /**
  * Getter de l'attibut gagnant
  *
  * @return le gagnant (-1 si aucun)
  */
  public int getGagnant() {
    return gagnant;
  }

  /**
  * Getter du nombrede colonnes de la grille
  *
  * @return le nb de colonnes
  */
  public int getNbColonnesGrille() {
    return g.getColonnes().size();
  }

  /**
  * Methode qui permet de lancer une partie entre plusieurs joueurs
  *
  * @param joueurs Liste des joueurs qui jouent
  */
  public void faireJouerJoueurs() throws ActionPartieException, PartieFinieException {
    if(gagnant != -1)
      throw new PartieFinieException("Impossible de faire jouer les joueurs sur une partie déjà finie");
    if(joueurs == null || joueurs.length == 0)
      throw new NullPointerException("Impossible de faire une partie sans joueurs");
    for(Joueur j : joueurs) {
      if(j==null)
        throw new NullPointerException("Impossible de faire joueur un joueur 'null'");
    }

    int col= 0;

    System.out.println("\n\n---- Etat de la partie ----");
    g.afficher();
    int i =0;
    boolean j_joue= true;
    while(i< joueurs.length) {
      Joueur j = joueurs[i];
      // On fait jouer le joueur
      System.out.println(j);
      try {
      col = j.jouer(g.getColonnes().size());
      } catch (ActionPartieException e){
        if(e.getAction()== ActionPartieException.TYPE_RETOUR_ARRIERE) {
          if(historique.size() > 0) {
            Colonne c= g.getColonnes().get(historique.get(historique.size()-1));
            c.remove(c.size()-1);
            i--;
          } else {
            System.out.println("Vous ne pouvez pas annuler un mouvement s'il n'y en a aucun");
          }
          j_joue=false;
        } else {
          throw e;
        }
      }
      if(j_joue){
        try {
          g.ajouterJeton(new Jeton(j.getTeamId()), col);
        } catch (ArgumentInvalideException e) {
          e.printStackTrace();
        }
        historique.add(col);
        // System.out.println(historique.get(historique.size()-1));
        // On affiche le résultat de son coup
        System.out.println("\n\n---- Etat de la partie ----");
        g.afficher();
        // On verifie le coup joué
        try {
          if(verifierCoup(col)) {
            gagnant=j.getTeamId();
            j.victoire();
            break;
          }
        } catch (ArgumentInvalideException e) {
          System.out.println("verifierCoup(...) ne devrait pas générer d'Execption");
          e.printStackTrace();
          return;
        }
        i++;
      }
      g.afficher();
      j_joue=true;
    }
  }

  /**
  * Methode permettant de réinitialiser une partie pour en faire une nouvelle
  * @param nb_col le nouveau nombre de colonnes de la grille
  */
  public void rejouer(int nb_col) throws ArgumentInvalideException {
    g.vider();
    g.setNbColonnes(nb_col);
    gagnant = -1;
    historique = new ArrayList<Integer>();
  }

  /**
  * Méthode qui permet de verifier si le dernier jeton d'une colonne est un coup
  * qui a permis a une equipe de gagner
  *
  * @param col la colonne ou il faut verifier
  */
  public boolean verifierCoup(int col) throws ArgumentInvalideException{
    if(col < 0 || col >= g.getColonnes().size()) {
      throw new ArgumentInvalideException("Impossible de verifier le coup pour la colonne "+col);
    }
    int j = g.getColonnes().get(col).size()-1;
    int equipe = g.getColonnes().get(col).getJeton(j).getTeamId();
    boolean gagnant = false;
    // On verifie verticalement
    if(j >= 3) { // Si la place le permet
      gagnant=true;
      for(int j2 = j-1; j2>=j-3; j2--) { // On regarde les 3 derniers jetons de la colonne apres celui qui vient d'être joué
        if(g.getColonnes().get(col).getJeton(j2).getTeamId() != equipe) {
          gagnant = false;
        }
      }
    }

    // le nombre de max jetons qu'on peut regarder dans un direction
    int ne, no, se, so; // En diagonale
    int haut, droite, bas, gauche; // Horizontalement et verticalement
    int nb_lignes = g.getNbLignes();

    // Pour chaque diagonale on regarde le plus petit entre le nombre de jetons possibles
    // verticalement et horizontalement, on major ce nombre par 3 car pas besoin de plus
    ne = Math.min(g.getColonnes().size() - col - 1, Math.min(nb_lignes - j - 1, 3));
    no = Math.min(nb_lignes - j - 1, Math.min(col, 3));
    se = Math.min(g.getColonnes().size() - col -1, Math.min(j, 3));
    so = Math.min(col, Math.min(j, 3));
    // System.out.println("Possibilitées : ne("+ne+") no("+no+") se("+se+") so("+so+")");

    // horizontalement
    if(!gagnant) {
      gauche = Math.max(0, col-3);
      droite = Math.min(g.getColonnes().size()-1, col+3);
      // System.out.println("\nH : gauche("+gauche+") droite("+droite+")");
      if(droite - gauche >= 3) // Si la place le permet
        gagnant = verifierIntervalle(gauche, droite, g.getColonnes().get(col).size()-1, 0, equipe);
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
      if(j >= g.getColonnes().get(i).size() || g.getColonnes().get(i).getJeton(j).getTeamId()!=equipe )
        nb = 0;
      else
        nb ++;

      if(nb == 4) {
        return true;
      }
      i++;
      j+=j_pas;
      if(j < 0) {// Si j décrémente en dessous de 0
        return false;
      }
    }
    return false;
  }
}
