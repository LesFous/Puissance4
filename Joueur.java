import java.io.*;

public abstract class Joueur implements Serializable{
  /**
  * Attribut représentant l'équipe d'un joueur (unique)
  */
  private int id_equipe;

  /**
  * Attribut static qui compte le nombre d'instances
  */
  private static int nb_instances=0;

  /**
  * Attribut qui permet de suivre le nombre de victoires d'un joueurs
  */
  private int nb_victoires= 0;

  /**
  * Constructeur d'un joueur
  */
  public Joueur() {
    id_equipe = ++nb_instances;
  }

  /**
  * Getter de l'attribut equipe
  *
  * @return l'equipe
  */
  public int getTeamId() {
    return id_equipe;
  }

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
  public abstract int jouer(int nbcol) throws ActionPartieException;

  /**
  * Méthode appelée lorsqu'un joueur gagne une partie
  * permet d'augementer son score;
  */
  public void victoire(){
      nb_victoires++;
  }

  /**
  * Getter du score d'un joueur
  *
  * @return son score
  */
  public int getVictoire(){
      return nb_victoires;
  }

}
