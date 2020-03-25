public class JoueurOrdinateur extends Joueur {
  private static int tour = 0;

  @Override
  public  int jouer(int nbcol){
      return (int)(Math.random()*nbcol-1)+1;
  }


  @Override
  public String toString() {
    return "Ordinateur: (equipe "+getTeamId()+")";
  }

}
