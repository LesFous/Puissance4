import java.util.ArrayList;

  /**
  * classe qui sert a tester toutes les methode du Puissance4
  */
  public class PrincipaleTests {
    /**
    * attribut booleen statique qui permet de savoir si tous les tests ont reussis
    */
    public static boolean succes_tests;

    private static void erreurGrille(Grille g, String mess) {
      succes_tests = false;
      System.out.println(mess);
      g.afficher();
    }

    /**
    * methode qui test le constructeur de la classe Grille
    */
    public static void test_constructeurGrille() {
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
    public static void test_nbLignes() {
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
          if(g.getNbLignes() != 1) {
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
    public static void test_ajoutJetonGrille() {
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
    public static void test_casGagnantHorizontal() {
      Grille g = new Grille(4);
      Partie p = new Partie(g);
      // Grille :
      // 1 1 1 1
      // 1 2 1 1
      g.ajouterJeton(new Jeton(1), 0);
      g.ajouterJeton(new Jeton(2), 1);
      g.ajouterJeton(new Jeton(1), 2);
      g.ajouterJeton(new Jeton(1), 3);
      if(p.verifierCoup(0) || p.verifierCoup(1) || p.verifierCoup(2) || p.verifierCoup(3))
        erreurGrille(g, "Dans cette grille, on ne devrait pas detecter de cas gagnants :");

      g.ajouterJeton(new Jeton(1), 0);
      g.ajouterJeton(new Jeton(1), 1);
      g.ajouterJeton(new Jeton(1), 2);
      g.ajouterJeton(new Jeton(1), 3);
      if(!(p.verifierCoup(0) && p.verifierCoup(1) && p.verifierCoup(2) && p.verifierCoup(3)))
        erreurGrille(g, "Dans cette grille, on devrait détecter un cas gagnant avec verifierCoup(0), verifierCoup(1), verifierCoup(2), verifierCoup(3)");
    }

    /**
    * methode qui test le cas ou il y a un gagnat verticalement
    */
    public static void test_casGagnantVertical() {
      Grille g = new Grille(2);
      Partie p = new Partie(g);
      // Grille :
      //  1 .
      //  1 .
      //  1 .
      //  1 .
      //  2 .
      //  1 .
      g.ajouterJeton(new Jeton(1), 0);
      if(p.verifierCoup(0))
        erreurGrille(g, "Dans ette grille, on ne devrait pas detecter de cas gagnants");

      g.ajouterJeton(new Jeton(2), 0);
      if(p.verifierCoup(0))
        erreurGrille(g, "Dans ette grille, on ne devrait pas detecter de cas gagnants");

      g.ajouterJeton(new Jeton(1), 0);
      if(p.verifierCoup(0))
        erreurGrille(g, "Dans ette grille, on ne devrait pas detecter de cas gagnants");

      g.ajouterJeton(new Jeton(1), 0);
      if(p.verifierCoup(0))
        erreurGrille(g, "Dans ette grille, on ne devrait pas detecter de cas gagnants");

      g.ajouterJeton(new Jeton(1), 0);
      if(p.verifierCoup(0))
        erreurGrille(g, "Dans ette grille, on ne devrait pas detecter de cas gagnants");

      g.ajouterJeton(new Jeton(1), 0);
      if(!p.verifierCoup(0))
        erreurGrille(g, "Dans ette grille, on devrait detecter des cas gagnants avec verifierCoup(0)");
    }

    /**
    * methode qui test le cas ou il y a un gagnant dans une des diagonale
    */
    public static void test_casGagnantDiagonale_1() {
      Grille g = new Grille(6);
      Partie p = new Partie(g);

      // . . . . . 1
      // . . . . 1 2
      // . . . 1 2 2
      // . . 1 2 2 1
      // . 2 1 2 1 1
      // 1 1 1 2 2 2

      g.ajouterJeton(new Jeton(1), 0);
      g.ajouterJeton(new Jeton(1), 1);
      g.ajouterJeton(new Jeton(1), 2);
      g.ajouterJeton(new Jeton(2), 3);
      g.ajouterJeton(new Jeton(2), 4);
      g.ajouterJeton(new Jeton(2), 5);

      for(int i = 0; i < 6; i++) {
        if(p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on ne devrait pas détecter de cas gagnant sur la colonne" + i);
      }

      g.ajouterJeton(new Jeton(2), 1);
      g.ajouterJeton(new Jeton(1), 2);
      g.ajouterJeton(new Jeton(2), 3);
      g.ajouterJeton(new Jeton(1), 4);
      g.ajouterJeton(new Jeton(1), 5);
      for(int i = 0; i < 6; i++) {
        if(p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on ne devrait pas détecter de cas gagnant sur la colonne" + i);
      }

      g.ajouterJeton(new Jeton(1), 2);
      g.ajouterJeton(new Jeton(2), 3);
      g.ajouterJeton(new Jeton(2), 4);
      g.ajouterJeton(new Jeton(1), 5);
      for(int i = 0; i < 6; i++) {
        if(p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on ne devrait pas détecter de cas gagnant sur la colonne" + i);
      }

      g.ajouterJeton(new Jeton(1), 3);
      g.ajouterJeton(new Jeton(2), 4);
      g.ajouterJeton(new Jeton(2), 5);
      for(int i = 0; i < 6; i++) {
        if(p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on ne devrait pas détecter de cas gagnant sur la colonne" + i);
      }

      g.ajouterJeton(new Jeton(1), 4);
      g.ajouterJeton(new Jeton(2), 5);
      for(int i = 0; i < 6; i++) {
        if(p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on ne devrait pas détecter de cas gagnant sur la colonne" + i);
      }

      g.ajouterJeton(new Jeton(1), 5);
      for(int i = 2; i < 6; i++) {
        if(!p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on devrait pas détecter des cas gagnant sur la colonne" + i);
      }
      for(int i = 0; i < 2; i++) {
        if(p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on ne devrait pas détecter de cas gagnant sur la colonne" + i);
      }

    }

    /**
    * methode qui test le cas ou il y un gagnant dans une des diagonale
    */
    public static void test_casGagnantDiagonale_2() {
      Grille g = new Grille(6);
      Partie p = new Partie(g);

      // 1 . . . . .
      // 2 1 . . . .
      // 2 2 1 . . .
      // 1 2 2 1 . .
      // 1 1 2 1 2 .
      // 2 2 2 1 1 1

      g.ajouterJeton(new Jeton(2), 0);
      g.ajouterJeton(new Jeton(2), 1);
      g.ajouterJeton(new Jeton(2), 2);
      g.ajouterJeton(new Jeton(1), 3);
      g.ajouterJeton(new Jeton(1), 4);
      g.ajouterJeton(new Jeton(1), 5);

      for(int i = 0; i < 6; i++) {
        if(p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on ne devrait pas détecter de cas gagnant sur la colonne" + i);
      }

      g.ajouterJeton(new Jeton(1), 1);
      g.ajouterJeton(new Jeton(2), 2);
      g.ajouterJeton(new Jeton(1), 3);
      g.ajouterJeton(new Jeton(2), 4);
      g.ajouterJeton(new Jeton(2), 5);
      for(int i = 0; i < 6; i++) {
        if(p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on ne devrait pas détecter de cas gagnant sur la colonne" + i);
      }

      g.ajouterJeton(new Jeton(2), 2);
      g.ajouterJeton(new Jeton(2), 3);
      g.ajouterJeton(new Jeton(1), 4);
      g.ajouterJeton(new Jeton(2), 5);
      for(int i = 0; i < 6; i++) {
        if(p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on ne devrait pas détecter de cas gagnant sur la colonne" + i);
      }

      g.ajouterJeton(new Jeton(2), 3);
      g.ajouterJeton(new Jeton(1), 4);
      g.ajouterJeton(new Jeton(1), 5);
      for(int i = 0; i < 6; i++) {
        if(p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on ne devrait pas détecter de cas gagnant sur la colonne" + i);
      }

      g.ajouterJeton(new Jeton(2), 4);
      g.ajouterJeton(new Jeton(1), 5);
      for(int i = 0; i < 6; i++) {
        if(p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on ne devrait pas détecter de cas gagnant sur la colonne" + i);
      }

      g.ajouterJeton(new Jeton(2), 5);
      for(int i = 2; i < 6; i++) {
        if(!p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on devrait pas détecter des cas gagnant sur la colonne" + i);
      }
      for(int i = 0; i < 2; i++) {
        if(p.verifierCoup(i))
          erreurGrille(g, "Attention, avec cette grille on ne devrait pas détecter de cas gagnant sur la colonne" + i);
      }

    }


    /**
    *  methode qui test si les colonnes de la Grille sont bien triees
    */
    public static void test_colonnesTriees() {
      // . . 1 . .
      // . 2 2 . 2
      // . 1 1 1 2
      Grille g = new Grille(5);
      g.ajouterJeton(new Jeton(1), 1);
      g.ajouterJeton(new Jeton(1), 2);
      g.ajouterJeton(new Jeton(1), 3);
      g.ajouterJeton(new Jeton(2), 4);

      g.ajouterJeton(new Jeton(2), 1);
      g.ajouterJeton(new Jeton(2), 2);
      g.ajouterJeton(new Jeton(2), 4);

      g.ajouterJeton(new Jeton(1), 2);

      ArrayList<Colonne> col = g.getColonnesTriees();
      if(!(col.get(0).size() == 3 && col.get(1).size() == 2 && col.get(2).size() == 2 && col.get(3).size() == 1 && col.get(4).size() == 0)) {
        System.out.println("L'ordre des colonnes une fois triées n'est pas bon :");
        for(int i=0; i<5; i++) {
          System.out.print(col.get(i).size()+" ");
        }
        System.out.println("\nAttendu : 3 2 2 1 0");
      }
    }

    /**
    * methode statique qui lance tous les tests
    */
    public static void lancerTests() {
      succes_tests = true;
      test_constructeurGrille();
      if(!succes_tests)
        System.out.println("Problèmes dans le constructeur\n\n");
      test_ajoutJetonGrille();
      if(!succes_tests)
        System.out.println("Problèmes dans l'ajout de Jeton\n\n");
      test_casGagnantHorizontal();
      if(!succes_tests)
        System.out.println("Problèmes dans la detection des cas (Horizontal)\n\n");
      test_casGagnantVertical();
      if(!succes_tests)
        System.out.println("Problèmes dans la detection des cas (Vertical)\n\n");
      test_casGagnantDiagonale_1();
      if(!succes_tests)
        System.out.println("Problèmes dans la detection des cas (Diagonale 1)\n\n");
      test_casGagnantDiagonale_2();
      if(!succes_tests)
        System.out.println("Problèmes dans la detection des cas (Diagonale 2)\n\n");
      test_nbLignes();
      if(!succes_tests)
        System.out.println("Problèmes dans le calcul du nombre de lignes\n\n");
    }

  }
