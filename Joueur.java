public abstract class Joueur{
  private int team_id;
  private static int team_count=0;

  public Joueur() {
    team_id = ++team_count;
  }

  public int getTeamId() {
    return team_id;
  }

  public abstract int jouer();
}
