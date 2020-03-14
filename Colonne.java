import java.util.ArrayList;
/**
* Classe reprensentant une colonne de jeton remplie par les joueurs
*/
public class Colonne {
  private ArrayList<Jeton> jetons;

  public Colonne(int nb_lignes) {
    jetons = new ArrayList<Jeton>(nb_lignes);
  }

  public void ajouter(Jeton j) {
    if(j != null)
      jetons.add(j);
    else
      throw new NullPointerException("Impossible d'ajouter un jeton NULL");
  }

  /**
  * Methode qui renvoie un Jeton a l indice i
  * @param i correspond a l index du tableu jetons
  * @return le jeton a la place indiquee
  */
  public Jeton getJeton(int i){
    return this.jetons.get(i);
  }

  public int size() {
    return jetons.size();
  }
}
