import java.util.Scanner;
import java.util.InputMismatchException;

public class JoueurReel extends Joueur {

  @Override
  public int jouer(int nbcol) {
    System.out.println("Quelle colonne jouer ? (1-"+nbcol+")");
    boolean nb_valide = false;
    int nb=0;
    Scanner sc = new Scanner(System.in);
    // On pose la question tant que la colonne n'est pas valide
    while(!nb_valide) {
      try {
        nb = sc.nextInt();
        if(nb > nbcol) {
          System.out.println("Merci de donner un nombre plus petit ou égale à "+nbcol);
        } else if(nb < 1) {
          System.out.println("Merci de donner un nombre plus grand ou égale à "+1);
        } else {
          nb_valide = true;
        }
      } catch (InputMismatchException e) {
        System.out.println("Merci de donner un nombre valide");
        sc.nextLine();
      }
    }
    return nb-1;
  }

  @Override
  public String toString() {
    return "Joueur reel (equipe "+getTeamId()+")";
  }

}
