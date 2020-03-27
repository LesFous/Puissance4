import java.util.ArrayList;
import java.io.*;
/**
* Classe reprensentant une colonne de jeton remplie par les joueurs
*/
public class Colonne implements Serializable{

  /**
  * attribut representant la liste de jeton d'une colonne de puissance4
  */
  private ArrayList<Jeton> jetons;

  /**
  * Constructeur permettant de creer une colonne;
  */
  public Colonne() {
    jetons = new ArrayList<Jeton>();
  }

  /**
  * methode permetteant d'ajouter un jeton a la colonne de puissance4
  * @param j correspon au jeton que l'on va ajouter a la colonne
  */
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

  /**
  * methode qui renvoie la taille de la colonne de jetons
  * @return le nombre de jeton dans la colonne
  */
  public int size() {
    return jetons.size();
  }
}
