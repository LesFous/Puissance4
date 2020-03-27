import java.io.*;

public abstract class Joueur implements Serializable{
  private int id_equipe;
  private static int nb_instances=0;
  private int nb_victoires= 0;

  public Joueur() {
    id_equipe = ++nb_instances;
  }

  public int getTeamId() {
    return id_equipe;
  }

  public abstract int jouer(int nbcol);

  public void victoire(){
      nb_victoires++;
    }

  public int getVictoire(){
      return nb_victoires;
    }


}
