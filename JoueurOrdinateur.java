
/**
* classe qui creer un ordinateur qui va jouer automatiquement au puissance4
*/
public class JoueurOrdinateur extends Joueur {
  /**
  * attribut static qui permet de compter le nombre de tour que l'ordinateur a joue
  */
  private static int tour = 0;

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
  public  int jouer(int nbcol) throws ActionPartieException {
      return (int)(Math.random()*nbcol-1)+1;
  }

  /**
  * methode qui permet de resumer l'ordinateur en chaine de caractere
  * @return une chaine de caractere avec l'id de l'equipe du joueur
  */
  @Override
  public String toString() {
    return "Ordinateur: (equipe "+getTeamId()+")";
  }

}
