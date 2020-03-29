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
      } catch (ArgumentInvalideException e) {
        System.out.println("new Grille(4) et getColonnes().size() ne devraient pas generer d'exceptions !");
        e.printStackTrace();
      }

      try {
        Grille g = new Grille(-1);
        System.out.println("new Grille(-1) devrait generer une erreur !");
        succes_tests = false;
      } catch (ArgumentInvalideException e) {
        // Normal
      }
    }

    /**
    * methode qui test s'il y a le bon nombre de ligne dans la grille
    */
    public static void test_nbLignes() {
      Grille g;
      try {
        g = new Grille(4);
      } catch(ArgumentInvalideException e) {
        succes_tests = false;
        System.out.println("new Gille(4) ne devrait pas générer d'exception:");
        e.printStackTrace();
        return;
      }

      if(g.getNbLignes() != 0) {
        System.out.println("A son initianilisation, la grille devrait n'avoir aucune lignes");
        succes_tests = false;
      } else {
        try {
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
        } catch (ArgumentInvalideException e) {
          System.out.println("ajouterJeton(...) ne devrait pas générer d'Execption");
          e.printStackTrace();
          succes_tests = false;
        }
      }
    }

    /**
    * methode qui permet de tester l'ajout d'un jeton dans une Grille
    */
    public static void test_ajoutJetonGrille() {
      Grille g;
      try {
        g = new Grille(4);
      } catch(ArgumentInvalideException e) {
        succes_tests = false;
        System.out.println("new Gille(4) ne devrait pas générer d'exception:");
        e.printStackTrace();
        return;
      }
      try {
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
      } catch(ArgumentInvalideException e2) {
        System.out.println("ajouterJeton(...) ne devrait pas générer d'Execption");
        succes_tests = false;
        e2.printStackTrace();
      }
    }

    /**
    * methode qui test le cas ou il y a un gagnant horizontalement
    */
    public static void test_casGagnantHorizontal() {
      Grille g;
      try {
        g = new Grille(4);
      } catch(ArgumentInvalideException e) {
        succes_tests = false;
        System.out.println("new Gille(4) ne devrait pas générer d'exception:");
        e.printStackTrace();
        return;
      }
      Joueur[] joueurs = new Joueur[2];
      joueurs[0] = (new JoueurReel());
      joueurs[1] = (new JoueurReel());

      Partie p;
      try {
        p = new Partie(g, joueurs);

      } catch(ArgumentInvalideException e) {
        System.out.println("new Partie(g, joueurs) ne devrait pas générer d'Execption");
        succes_tests = false;
        e.printStackTrace();
        return;
      }

      // Grille :
      // 1 1 1 1
      // 1 2 1 1
      try {
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
      } catch(ArgumentInvalideException e) {
        System.out.println("ajouterJeton(...) ne devrait pas générer d'Execption");
        succes_tests = false;
        e.printStackTrace();
      }
    }

    /**
    * methode qui test le cas ou il y a un gagnat verticalement
    */
    public static void test_casGagnantVertical() {
      Grille g;
      try {
        g = new Grille(2);
      } catch(ArgumentInvalideException e) {
        succes_tests = false;
        System.out.println("new Gille(2) ne devrait pas générer d'exception:");
        e.printStackTrace();
        return;
      }
      Joueur[] joueurs = new Joueur[2];
      joueurs[0] = (new JoueurReel());
      joueurs[1] = (new JoueurReel());

      Partie p;
      try {
        p = new Partie(g, joueurs);

      } catch(ArgumentInvalideException e) {
        System.out.println("new Partie(g, joueurs) ne devrait pas générer d'Execption");
        succes_tests = false;
        e.printStackTrace();
        return;
      }
      // Grille :
      //  1 .
      //  1 .
      //  1 .
      //  1 .
      //  2 .
      //  1 .
      try {
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

      } catch(ArgumentInvalideException e) {
        System.out.println("ajouterJeton(...) ne devrait pas générer d'Execption");
        succes_tests = false;
        e.printStackTrace();
      }
    }

    /**
    * methode qui test le cas ou il y a un gagnant dans une des diagonale
    */
    public static void test_casGagnantDiagonale_1() {
      Grille g;
      try {
        g = new Grille(6);
      } catch(ArgumentInvalideException e) {
        succes_tests = false;
        System.out.println("new Gille(6) ne devrait pas générer d'exception:");
        e.printStackTrace();
        return;
      }
      Joueur[] joueurs = new Joueur[2];
      joueurs[0] = (new JoueurReel());
      joueurs[1] = (new JoueurReel());

      Partie p;
      try {
        p = new Partie(g, joueurs);

      } catch(ArgumentInvalideException e) {
        System.out.println("new Partie(g, joueurs) ne devrait pas générer d'Execption");
        succes_tests = false;
        e.printStackTrace();
        return;
      }

      // . . . . . 1
      // . . . . 1 2
      // . . . 1 2 2
      // . . 1 2 2 1
      // . 2 1 2 1 1
      // 1 1 1 2 2 2

      try {
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
      } catch(ArgumentInvalideException e) {
        System.out.println("verifierCoup(...) ne devrait pas générer d'erreur");
      }

    }

    /**
    * methode qui test le cas ou il y un gagnant dans une des diagonale
    */
    public static void test_casGagnantDiagonale_2() {
      Grille g;
      try {
        g = new Grille(6);
      } catch(ArgumentInvalideException e) {
        succes_tests = false;
        System.out.println("new Gille(6) ne devrait pas générer d'exception:");
        e.printStackTrace();
        return;
      }
      Joueur[] joueurs = new Joueur[2];
      joueurs[0] = (new JoueurReel());
      joueurs[1] = (new JoueurReel());

      Partie p;
      try {
        p = new Partie(g, joueurs);

      } catch(ArgumentInvalideException e) {
        System.out.println("new Partie(g, joueurs) ne devrait pas générer d'Execption");
        succes_tests = false;
        e.printStackTrace();
        return;
      }

      // 1 . . . . .
      // 2 1 . . . .
      // 2 2 1 . . .
      // 1 2 2 1 . .
      // 1 1 2 1 2 .
      // 2 2 2 1 1 1

      try {
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

      } catch(ArgumentInvalideException e) {
        System.out.println("ajouterJeton(...) ne devrait pas générer d'Execption");
        succes_tests = false;
        e.printStackTrace();
      }


    }


    /**
    *  methode qui test si les colonnes de la Grille sont bien triees
    */
    public static void test_colonnesTriees() {
      // . . 1 . .
      // . 2 2 . 2
      // . 1 1 1 2
      Grille g;
      try {
        g = new Grille(5);
      } catch(ArgumentInvalideException e) {
        succes_tests = false;
        System.out.println("new Gille(5) ne devrait pas générer d'exception:");
        e.printStackTrace();
        return;
      }

      try {
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

      } catch(ArgumentInvalideException e) {
        System.out.println("ajouterJeton(...) ne devrait pas générer d'Execption");
        succes_tests = false;
        e.printStackTrace();
      }
    }

    /**
    * Methode qui test si une grille peut changer de taille
    */
    public static void test_setNbColonnes() {
      Grille g;
      try {
        g = new Grille(7);
      } catch(ArgumentInvalideException e) {
        succes_tests = false;
        System.out.println("new Gille(7) ne devrait pas générer d'exception:");
        e.printStackTrace();
        return;
      }
      try {
        g.ajouterJeton(new Jeton(1), 3);
      } catch(Exception e) {
        System.out.println("g.ajouterJeton(new Jeton(1), 3); ne devrait pas générer d'Exception:");
        e.printStackTrace();
      }
      try {
        g.setNbColonnes(5);
      } catch(Exception e) {
        System.out.println("setNbColonnes(5) ne devrait pas générer d'Exception:");
        e.printStackTrace();
      }
      if(g.getColonnes().size() != 5) {
        erreurGrille(g, "Apres setNbColonnes(5), la Grille avec 7 colonnes ne devrait en avoir plus que 5, obtenu :"+g.getColonnes().size());
      } else if(g.getColonnes().get(3).size() != 1) {
        erreurGrille(g, "Apres setNbColonnes(5), la Grille avec 7 colonnes et un jeton colonne 3, a perdu son jeton");
      }
      try {
        g.setNbColonnes(9);
      } catch(Exception e) {
        System.out.println("setNbColonnes(5) ne devrait pas générer d'Exception:");
        e.printStackTrace();
      }
      if(g.getColonnes().size() != 9) {
        erreurGrille(g, "Apres setNbColonnes(9), la Grille avec 5 colonnes ne devrait en 9, obtenu :"+g.getColonnes().size());
      } else if(g.getColonnes().get(3).size() != 1) {
        erreurGrille(g, "Apres setNbColonnes(9), la Grille avec 5 colonnes et un jeton colonne 3, a perdu son jeton");
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
      test_setNbColonnes();
      if(!succes_tests)
        System.out.println("Problèmes dans le changement du nb de colonnes\n\n");
    }

  }
