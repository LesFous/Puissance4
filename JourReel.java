import java.util.Scanner;

class JoueurReel extends Joueur {

  @Override
  public int jouer(int nbcol) {
    System.out.println("Quelle colonne jouer ? ");
    Scanner sc = new Scanner(System.in);
    return sc.nextInt()-1;
  }

  @Override
  public String toString() {
    return "Joueur reel (equipe "+getTeamId()+")";
  }

}
