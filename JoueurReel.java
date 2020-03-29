import java.util.Scanner;
import java.util.InputMismatchException;

  /**
  * classe qui correspond a un joueur reel de puissance4
  */
  public class JoueurReel extends Joueur {
    /**
    * Méthode appelée lorsqu'un joueur doit jouer,
    * avec laquelle le joueur choisit quelle colonne jouer.
    *
    * @param nbcol le nombre de colonnes qu'il peut choisir (permet de délimiter un intervalle)
    *
    * @throws ActionPartieException lorsqu'un joueur veut faire une action comme suavegarder ou arreter la partie au lieux de choisir une colonne
    *
    * @return la colonne choisie par le joueur pour jouer
    */
    @Override
    public int jouer(int nbcol) throws ActionPartieException {
      System.out.println("Quelle colonne jouer ? (1-"+nbcol+")");
      System.out.println("A tout moment vous pouvez sauvegarder avec sauvegarder, quitter avec arreter ou annuler le coup avec annuler");
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
          String action = sc.nextLine().toUpperCase();
          if(action.equals("SAUVEGARDER"))
            throw new ActionPartieException(""+ActionPartieException.TYPE_SAUVEGARDE);
          else if(action.equals("ARRETER"))
            throw new ActionPartieException(""+ActionPartieException.TYPE_ARRET);
            else if(action.equals("ANNULER"))
            throw new ActionPartieException(""+ActionPartieException.TYPE_RETOUR_ARRIERE);

          System.out.println("Merci de donner un nombre valide ou bien 'sauvegarder' ou 'arreter'");
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
