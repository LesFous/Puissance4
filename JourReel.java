import java.util.Scanner;

class JoueurReel extends Joueur {

  @Override
  public int jouer() {
    Scanner sc = new Scanner(System.in);
    return sc.nextInt();
  }

}
