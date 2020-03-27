class PrincipaleTests {
  public static boolean succes_tests;

  public void test_constructeurGrille() {
    try {
      Grille g = new Grille(4);
      if(g.getColonnes().size() != 4) {
        succes_tests = false;
        System.out.println("Apres new Grille(4), la grille n'a pas 4 colonnes");
      }
    } catch(Exception e) {
      succes_tests = false;
      System.out.println("new Grille(4) et getColonnes().size() ne devraient pas generer d'exceptions !");
      e.printStackTrace();
    }
    try {
      Grille g = new Grille(-1);
      System.out.println("new Grille(-1) devrait generer une erreur !");
      succes_tests = false;
    } catch (Exception e) {

    }
  }

  public void test_ajoutJetonGrille() {

  }

  public void test_casGagnantHorizontal() {

  }

  public void test_casGagnantVertical() {

  }

  public void test_casGagnantDiagonal_1() {

  }

  public void test_casGagnantDiagonal_2() {

  }

  public void test_nbLignes() {

  }

  public void test_colonnesTriees() {

  }

  private PrincipaleTests() {

  }

  public static void lancerTests() {
    PrincipaleTests tests = new PrincipaleTests();
    succes_tests = true;
    tests.test_constructeurGrille();
    tests.test_ajoutJetonGrille();
    tests.test_casGagnantHorizontal();
    tests.test_casGagnantVertical();
    tests.test_casGagnantDiagonal_1();
    tests.test_casGagnantDiagonal_2();
  }

}
