import java.io.*;
/**
* Classe reprensentant un jeton d un joueur
*/
public class Jeton implements Serializable {

  /**
  * attribut reprensentant le numero du joueur
  */
  private int id;

  /**
  * Contructeur de l objet jeton
  * @param joueur correspond au joueur possedant le jeton
  */
  public Jeton(int joueur){
    try {
      this.id = joueur;
    }catch(Exception e){
      e.printStackTrace();
      System.out.println("Ce n'est pas un nombre valide");
    }
  }

  /**
  * Methode retournant l entier du joueur
  * @return la valeur entiere de l attribut id
  */
  public int getTeamId(){
    return this.id;
  }

  /**
  * Methode retournant l entier du joueur en chaine de caractere
  * @return la valeur entiere de l attribut id en chaine de caractere
  */
  @Override
  public String toString(){
    return ""+this.id;
  }
}
