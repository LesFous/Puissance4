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
    * methode qui test s'il y a le bon nombre de ligne dans la grille
    */
    public void test_nbLignes() {
      Grille g = new Grille(4);
      if(g.getNbLignes() != 0) {
        System.out.println("A son initianilisation, la grille devrait n'avoir aucune lignes");
        succes_tests = false;
      } else {
        g.ajouterJeton(new Jeton(1), 2);
        if(g.getNbLignes() != 1) {
          System.out.println("Apres l'ajout d'un jeton, le nombre de lignes devrait être égal à 1");
          succes_tests = false;
        } else {
          g.ajouterJeton(new Jeton(2), 3);
          if(g.getNbLignes() != -1) {
            System.out.println("Apres l'ajout de 2 jetons sur 2 colonnes différentes le nombre de lignes devrait être égal à 1");
            succes_tests = false;
          } else {
            g.ajouterJeton(new Jeton(1), 2);
            if(g.getNbLignes() != 2) {
              System.out.println("Apres l'ajout de 2 jetons sur la même colonne, le nombre de lignes devrait être égal à 2");
              succes_tests = false;
            }
          }
        }
      }
    }

    /**
    * methode qui permet de tester l'ajout d'un jeton dans une Grille
    */
    public void test_ajoutJetonGrille() {
      Grille g = new Grille(4);
      g.ajouterJeton(new Jeton(1), 1);
      if(g.getColonnes().get(1).size() != 1) {
        System.out.println("g.ajouterJeton(new Jeton(1), 1) devrait avoir ajouté un jeton dans la colonne 1 mais la taille de la colonne n'est pas 1");
        succes_tests = false;
      } else {
        try {
          g.ajouterJeton(new Jeton(2), -1);
          System.out.println("Ajouter un jeton à la colonne -1 devrait générer une errer");
          succes_tests = false;
        } catch(Exception e) {
        }
      }
    }

    /**
    * methode qui test le cas ou il y a un gagnant horizontalement
    */
    public void test_casGagnantHorizontal() {
      Grille g = new Grille(4);
      Partie p = new Partie(g);
      // 1 1 1 1
      // 1 2 1 1
      g.ajouterJeton(new Jeton(1), 0);
      g.ajouterJeton(new Jeton(2), 1);
      g.ajouterJeton(new Jeton(1), 2);
      g.ajouterJeton(new Jeton(1), 3);
      if(p.verifierCoup(0) || p.verifierCoup(1) || p.verifierCoup(2) || p.verifierCoup(3)) {
        System.out.println("Attention la configuration |1|2|1|1| ne devrait pas détecter de cas gagnants");
        succes_tests = false;
      }
      g.ajouterJeton(new Jeton(1), 0);
      g.ajouterJeton(new Jeton(1), 1);
      g.ajouterJeton(new Jeton(1), 2);
      g.ajouterJeton(new Jeton(1), 3);
      if(!(p.verifierCoup(0) && p.verifierCoup(1) && p.verifierCoup(2) && p.verifierCoup(3))) {
        System.out.println("Attention la configuration\n|1|1|1|1|\n|1|2|1|1|\nDevrait détecter des cas gagnants avec verifierCoup(0), verifierCoup(1), verifierCoup(2), verifierCoup(3)");
        succes_tests = false;
      }
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
