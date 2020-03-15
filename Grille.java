/**
* Classe qui represente un grille de puissance 4
*
*/
class Grille {
  /**
  * attribut qui représente toutes les colonnes de la grille
  */
  private Colonne colonnes[];

  /**
  * attibut qui représsente le nombre de lignes de la grille
  */
  private int nb_lignes;

  /**
  * Constructeur qui permet de créer une grille d'une taille donnee
  *
  * @param nb_lignes le nombre de nb_lignes
  * @param nb_colonnes le nombre de colonnes
  */
  public Grille(int nb_lignes, int nb_colonnes) {
    colonnes = new Colonne[nb_colonnes];
    for(int i=0; i<nb_colonnes; i++) {
      colonnes[i] = new Colonne(nb_lignes);
    }
    this.nb_lignes=nb_lignes;
  }

  /**
  * Methode qui permet d'afficher la grille du jeu avec ses jetons
  *
  */
  public void afficher() {
    afficherSepLigne();
    Jeton jeton;
    String jeton_str;

    // Pour chaque ligne
    for(int j=nb_lignes-1; j>=0; j--) {
      // Pour chaque colonne
      for (int i=0; i<colonnes.length; i++) {
        // On recupere le jeton s'il y en a un
        if(j >= colonnes[i].size())
          jeton = null;
        else {
          jeton = colonnes[i].getJeton(j);
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
    for(int i=0; i < colonnes.length; i++) {
      System.out.print("+-");
    }
    System.out.println("+");;
  }

  /**
  * Methode priee qui permet d'ajouter un jeton a une colonne
  * Si le nombre de lignes de suffit pas, il est augmneté de un
  *
  * @param j Le jeton à ajouter (non null)
  * @param col Le numero de la colonne a laquelle on ajoute le Jeton (entre 0 et nb_colonnes compris)
  */
  private void ajouterJeton(Jeton j, int col) {
    if (col < 0 || col >= colonnes.length) {
      throw new IndexOutOfBoundsException("La colonne n'est pas valide :"+col);
    }
    if(colonnes[col].size()+1 > nb_lignes) {
      nb_lignes = colonnes[col].size()+1;
    }
    colonnes[col].ajouter(j);
  }

  /**
  * Méthode qui permet de verifier si le dernier jeton d'une colonne est un coup
  * qui a permis a une equipe de gagner
  *
  * @param col la colonne ou il faut verifier
  */
  private void verifierCoup(int col) {
    // TODO Finir ca
  }

  /**
  * Methode qui permet de lancer une partie entre plusieurs joueurs
  *
  * @param joueurs Liste des joueurs qui jouent
  */
  public void jouer(Joueur joueurs[]) {
    for(Joueur j : joueurs) {
      if(j==null)
        throw new NullPointerException("Impossible de faire joeur un joueur 'null'");
    }

    boolean jouer = true;
    int col;
    afficher();
    while (jouer) {
      for(Joueur j : joueurs) {
        // On fait jouer le joueur
        System.out.println(j);
        col = j.jouer();
        ajouterJeton(new Jeton(j.getTeamId()), col);
        // On affiche le résultat de son coup
        System.out.println("\nEtat du jeu :");
        afficher();
        // On verifie le coup joué
      }
    }
  }

  /**
  * Methode principale lancee au moment ou le programme est execute
  *
  * @param args les arguments passes au moment de lancer le programme 
  */
  public static void main(String[] args) {
    Grille g= new Grille(4, 8);
    Joueur[] joueurs = new Joueur[2];
    joueurs[0] = new JoueurReel();
    joueurs[1] = new JoueurReel();
    g.jouer(joueurs);
  //   g.afficher();
  //   Joueur j = new JoueurReel();
  //   g.ajouterJeton(new Jeton(j.getTeamId()), j.jouer());
  //   g.afficher();
  }


}
