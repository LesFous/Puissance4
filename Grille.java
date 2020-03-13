class Grille {
  private Colonne colonnes[];
  private int nb_lignes;

  public Grille(int nb_lignes, nb_colonnes) {
    colonnes = new Colonne[nb_colonnes];
    for(int i=0; i<nb_colonnes; i++) {
      colonnes[i] = new Colonne(nb_lignes);
    }
    this.nb_lignes=nb_lignes;
  }

  public void afficher() {
    afficherSepLigne();
    Jeton jeton;
    String jeton_str;
    for(int i=0; i<nb_colonnes; i++) {
      for(int j=0; j<nb_lignes; j++) {
        jeton = colonnes[i].getJeton();
        if(jeton == null)
          jeton_str = " ";
        else
          jeton_str = jeton.toString();
        System.out.print("|"+jeton_str);
      }
      System.out.println("|");
      afficherSepLigne();
    }
  }

  public void afficherSepLigne() {
    for(int i=0; i < colonnes.length; i++) {
      System.out.print("+-");
    }
    System.out.println("+");;
  }

  public Grille Trier(){
    return this;
  }
}
