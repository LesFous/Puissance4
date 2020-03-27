import java.util.Comparator;

  /**
  * classe qui permet de comparer la taille de deux colonnes
  */
  public class ComparateurTaillesColonnes implements Comparator<Colonne> {

    /**
    * methode qui compare la taille de deux colonnes passees en parametre en renvoyant un entier
    * @param c1 correspond a une colonne de jeton
    * @param c2 correspond a une colonne de jeton
    * @return renvoie un entier correspondant au nombre de jeton d'ecart entre deux colonnes
    */
    public int compare(Colonne c1, Colonne c2) {
      return c2.size() -  c1.size();
    }
  }
