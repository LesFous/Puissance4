import java.util.ArrayList;
import java.io.*;
/**
* Classe reprensentant une colonne de jeton remplie par les joueurs
*/
public class Colonne implements Serializable{
  private ArrayList<Jeton> jetons;

  public Colonne() {
    jetons = new ArrayList<Jeton>();
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
