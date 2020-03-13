import java.util.ArrayList;

class Colonne {
  private ArrayList<Jeton> jetons;

  public Colonne(int nb_lignes) {
    jetons = new ArrayList<Jeton>(nb_lignes);
  }

  public Jeton getJeton(int i){
    return this.jetons.get(i);
  }

  public int size() {
    return jetons.size();
  }
}
