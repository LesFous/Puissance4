import java.util.Comparator;

class ComparateurTaillesColonnes implements Comparator<Colonne> {
  public int compare(Colonne c1, Colonne c2) {
    return c2.size() -  c1.size();
  }
}
