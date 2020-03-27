  /**
  * classe qui sert a tester toutes les methode du Puissance4
  */
  public class PrincipaleTests {
    /**
    * attribut booleen statique qui permet de savoir si tous les tests ont reussis
    */
    public static boolean succes_tests;

    /**
    * methode qui test le constructeur de la classe Grille
    */
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

    /**
    * methode qui permet de tester l'ajout d'un jeton dans une Grille
    */
    public void test_ajoutJetonGrille() {

    }

    /**
    * methode qui test le cas ou il y a un gagnant horizontalement
    */
    public void test_casGagnantHorizontal() {

    }

    /**
    * methode qui test le cas ou il y a un gagnat verticalement
    */
    public void test_casGagnantVertical() {

    }

    /**
    * methode qui test le cas ou il y a un gagnant dans une des diagonale
    */
    public void test_casGagnantDiagonal_1() {

    }

    /**
    * methode qui test le cas ou il y un gagnant dans une des diagonale
    */
    public void test_casGagnantDiagonal_2() {

    }

    /**
    * methode qui test s'il y a le bon nombre de ligne dans la grille
    */
    public void test_nbLignes() {

    }

    /**
    *  methode qui test si les colonnes de la Grille sont bien triees
    */
    public void test_colonnesTriees() {

    }

    private PrincipaleTests() {

    }

    /**
    * methode statique qui lance tous les tests
    */
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
