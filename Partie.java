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
    * Constructeur permettant de creer une partie avec une grille pasée en parametre
    * @param grille represente la grille de puissance 4
    */
    public Partie(Grille grille){
      g= grille;
      joueurs= null;
      gagnant = -1;
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
    * Methode privee qui permet d'ajouter un jeton a une colonne
    * Si le nombre de lignes de suffit pas, il est augmneté de un
    *
    * @param j Le jeton à ajouter (non null)
    * @param col Le numero de la colonne a laquelle on ajoute le Jeton (entre 0 et nb_colonnes compris)
    */
    private void ajouterJeton(Jeton j, int col) {
      if (col < 0 || col >= g.getColonnes().size()) {
        throw new IndexOutOfBoundsException("La colonne n'est pas valide :"+col);
      }
      g.getColonnes().get(col).ajouter(j);
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
    g.afficher();
    for(Joueur j : joueurs) {
      // On fait jouer le joueur
      System.out.println(j);
      col = j.jouer(g.getColonnes().size());
      ajouterJeton(new Jeton(j.getTeamId()), col);
      // On affiche le résultat de son coup
      System.out.println("\nEtat du jeu :");
      g.afficher();
      // On verifie le coup joué
      if(verifierCoup(col))
        gagnant=j.getTeamId();
    }

  }


  /**
  * Méthode qui permet de verifier si le dernier jeton d'une colonne est un coup
  * qui a permis a une equipe de gagner
  *
  * @param col la colonne ou il faut verifier
  */
  private boolean verifierCoup(int col) {
    if(col < 0 || col >= g.getColonnes().size()) {
      throw new IndexOutOfBoundsException("Impossible de verifier le coup pour la colonne "+col);
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
}
