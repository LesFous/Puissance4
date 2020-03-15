public abstract class Joueur{
  private int id_equipe;
  private static int nb_instances=0;

  public Joueur() {
    id_equipe = ++nb_instances;
  }

  public int getTeamId() {
    return id_equipe;
  }

  public abstract int jouer();

}
