import java.util.Scanner;
import java.util.InputMismatchException;

  /**
  * classe qui correspond a un joueur reel de puissance4
  */
  public class JoueurReel extends Joueur {

    /**
    * methode qui demande une colonne a un joueur afin de savoir dans quelle colonne on va inserer un jeton
    * @param nbcol parametre qui correspond au nombre de colonnes que la grille possede
    * @return un entier qui correspond a la colonne que le joueur a choisi au clavier
    */
    @Override
    public int jouer(int nbcol) {
      System.out.println("Quelle colonne jouer ? (1-"+nbcol+")");
      System.out.println("A tout moment vous pouvez sauvegarder ou quitter en ecrivant sauvegarde ou quit");
      String rep="";
      boolean nb_valide = false;
      int nb=0;
      Scanner sc = new Scanner(System.in);
      // On pose la question tant que la colonne n'est pas valide
      while(!nb_valide) {
        try {
          nb = sc.nextInt();
          if(nb > nbcol) {
            System.out.println("Merci de donner un nombre plus petit ou égale à "+nbcol);
          } else if(nb < 1) {
            System.out.println("Merci de donner un nombre plus grand ou égale à "+1);
          } else {
            nb_valide = true;
          }
        } catch (InputMismatchException e) {
          System.out.println("Merci de donner un nombre valide");
          sc.nextLine();
        }
      }
      return nb-1;
    }

    /**
    * methode qui permet de resumer le joueur en une chaine de caractere
    * @return une chaine de caractere avec l'id de l'equipe du joueur
    */
    @Override
    public String toString() {
      return "Joueur reel (equipe "+getTeamId()+")";
    }

  }
