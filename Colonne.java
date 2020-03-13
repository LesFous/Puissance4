import java.util.ArrayList;
/**
* Classe reprensentant une colonne de jeton remplie par les joueurs
*/
public class Colonne {
  private ArrayList<Jeton> jetons;



  /**
  * Methode qui renvoie un Jeton a l indice i
  * @param i correspond a l index du tableu jetons
  * @return le jeton a la place indiquee
  */
  public Jeton getJeton(int i){
    return this.jetons.get(i);
  }
}
