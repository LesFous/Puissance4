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
  public void ajouter(Jeton j) throws ArgumentInvalideException{
    if(j != null)
      jetons.add(j);
    else
      throw new ArgumentInvalideException("Impossible d'ajouter un jeton NULL");
  }

  /**
  * Méthode pour vider une colonne de ses jetons
  */
  public void vider() {
    jetons.clear();
  }

  /**
  * methode qui permet de supprimer un jeton dans une colonnes passee en parametre
  * @param i correspond a la colone ou le jeton sera supprime 
  */
  public void remove(int i){
    jetons.remove(i);
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
