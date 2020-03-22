import java.util.Scanner;
import java.io.*;

/**
* Classe qui represente un grille de puissance 4
*
*/
public class Grille implements Serializable{
  /**
  * attribut qui represente toutes les colonnes de la grille
  */
  private Colonne colonnes[];

  /**
  * attibut qui represente le nombre de lignes de la grille
  */
  private int nb_lignes;

  /**
  * attribut qui represente le nombre de colonnes de la grille
  */
  private int nb_colonnes;

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
    this.nb_colonnes= nb_colonnes;
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
  * Methode privee qui permet d'ajouter un jeton a une colonne
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
  private boolean verifierCoup(int col) {
    if(col < 0 || col >= colonnes.length) {
      throw new IndexOutOfBoundsException("Impossible de verifier le coup pour la colonne "+col);
    }
    int j = colonnes[col].size()-1;
    int equipe = colonnes[col].getJeton(j).getTeamId();
    boolean gagnant = false;
    // On verifie verticalement
    if(j >= 3) { // Si la place le permet
      gagnant=true;
      for(int j2 = j-1; j2>=j-3; j2--) { // On regarde les 3 derniers jetons de la colonne apres celui qui vient d'être joué
        if(colonnes[col].getJeton(j2).getTeamId() != equipe) {
          gagnant = false;
        }
      }
    }

    // le nombre de max jetons qu'on peut regarder dans un direction
    int ne, no, se, so; // En diagonale
    int haut, droite, bas, gauche; // Horizontalement et verticalement

    // Pour chaque diagonale on regarde le plus petit entre le nombre de jetons possibles
    // verticalement et horizontalement, on major ce nombre par 3 car pas besoin de plus
    ne = Math.min(colonnes.length - col - 1, Math.min(nb_lignes - j - 1, 3));
    no = Math.min(nb_lignes - j - 1, Math.min(col, 3));
    se = Math.min(colonnes.length - col -1, Math.min(j, 3));
    so = Math.min(col, Math.min(j, 3));

    // horizontalement
    if(!gagnant) {
      gauche = Math.max(0, col-3);
      droite = Math.min(colonnes.length-1, col+3);
      if(droite - gauche >= 4) // Si la place le permet
        gagnant = verifierIntervalle(gauche, droite, colonnes[col].size()-1, 0, equipe);
    }

    // Diagonale de SO à NE
    if(!gagnant) {
      gauche = col - so;
      droite = col + ne;
      haut = j + ne;
      bas = j - so;
      if(haut + bas >= 4 && gauche + droite >= 4) // Si la place le permet
        gagnant = verifierIntervalle(gauche, droite, bas, 1, equipe);
    }

    // Diagonale de NO à SE
    if(!gagnant) {
      gauche = col - no;
      droite = col + se;
      haut = j + no;
      bas = j - se;
      if(haut + bas >= 4 && gauche + droite >= 4) // Si la place le permet
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
    // System.out.println("\n\n");
    while(i+3-nb <= droite && colonnes[i].size() > j) { // TODO a optimiser avec nb
      // System.out.println("i: "+i+" j:"+j);
      if(j < colonnes[i].size()) { // Si on est pas en dehors de la colonne
        if(colonnes[i].getJeton(j).getTeamId()!=equipe)
          nb = 0;
        else
          nb ++;
      }
      if(nb == 4)
        return true;
      i++;
      j+=j_pas;
      if(j < 0) // Si j décrémente en dessous de 0
        return false;

    }
    return false;
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
        col = j.jouer(nb_colonnes);
        ajouterJeton(new Jeton(j.getTeamId()), col);
        // On affiche le résultat de son coup
        System.out.println("\nEtat du jeu :");
        afficher();
        // On verifie le coup joué
        if(verifierCoup(col)) {
          jouer = false;
          System.out.println("Partie finie, l'equipe "+j.getTeamId()+" gagne");
          return ;
        }
      }
    }
    System.out.println("Voulez-vous sauvegarder ou continuer ou arreter ? (A/S/C)"); // A = arreter, S = sauvegarder, C = continuer
    Scanner sc = new Scanner(System.in);
    String rep = sc.nextLine();
    if (rep.equals("S")){
      try{
        Sauvegarde s = new Sauvegarde("Sauvegarde.txt");
        s.sauvegarder(this,joueurs);
        jouer =false;
      }catch (IOException e){
        System.out.println("Probleme lors de l'ecriture");
        e.printStackTrace();
      }catch (Exception e){
        e.printStackTrace();
      }
    }else if(rep.equals("A")){
      jouer=false;
    }
  }

  /**
  * Methode principale lancee au moment ou le programme est execute
  *
  * @param args les arguments passes au moment de lancer le programme
  */
  public static void main(String[] args) {
    Grille g = null;
    Joueur[] joueurs = null;
    System.out.println("Voulez-vous reprendre votre sauvegarde ? (O/N)");
    Scanner scan = new Scanner(System.in);
    String reponse = scan.nextLine();
    if (reponse.equals("O")){
        try {
          g = (Grille)(Sauvegarde.recupererObjet("Sauvegarde.txt"));
          joueurs = (Joueur[])(Sauvegarde.recupererObjet("Sauvegarde.txt"));
    }catch (FileNotFoundException e){
      System.out.println("Le fichier de lecture n'existe pas ");
      e.printStackTrace();
    }catch (IOException e){
      System.out.println("Probleme lors de la lecture");
      e.printStackTrace();
    }catch (Exception e){
      e.printStackTrace();
    }
       g.jouer(joueurs);

    }else{
      g= new Grille(6,8);
      System.out.println("Combien y a-t-il de joueurs? ");
      Scanner sc = new Scanner(System.in);
      int nb_joueurs = sc.nextInt();
      if(nb_joueurs== 0){
        joueurs = new Joueur[2];
        joueurs[0] = new Ordi();
        joueurs[1] = new Ordi();
        g.jouer(joueurs);
      }else if (nb_joueurs== 1){
        joueurs = new Joueur[2];
        joueurs[0] = new JoueurReel();
        joueurs[1] = new Ordi();
        g.jouer(joueurs);
      }else if(nb_joueurs> 1){
        System.out.println("Combien y a-t-il d'ordinateurs ? ");
        Scanner sc2 = new Scanner(System.in);
        int nb_ordi =sc2.nextInt();
        if(nb_ordi>= 0){
          joueurs = new Joueur[nb_joueurs];
          int i= 0;
          while(i!=(nb_joueurs-nb_ordi)){
            joueurs[i]= new JoueurReel();
            i++;
          }
          while(i != nb_joueurs){
            joueurs[i] = new Ordi();
            i++;
          }

          g.jouer(joueurs);
        }
    }

    }

  //   g.afficher();
  //   Joueur j = new JoueurReel();
  //   g.ajouterJeton(new Jeton(j.getTeamId()), j.jouer());
  //   g.afficher();
  }


}
