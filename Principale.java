import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.io.IOException;

  /**
  * classe principale du puissance4 qui creer un puissance4 et fait jouer une liste de joueurs
  */
  public class Principale {
    private static int CODE_FIN_JOUEUR_A_GAGNE = 0;
    private static int CODE_FIN_SAUVEGARDE = 1;
    private static int CODE_FIN_ARRET = 2;

    /**
    * Méthode statique qui demande de saisir un entier tant que celui-ci n'est pas un entier
    * ou qu'il ne respecte pas les conditions de min et max
    *
    * @param min le nombre minimal acceptable (peut être 'null')
    * @param max le nombre maximal acceptable (peut être 'null')
    * @return un entier respectant le min et le max
    */
    public static int demanderEntier(Integer min, Integer max) {
      boolean nb_valide = false;
      int nb=0;
      Scanner sc = new Scanner(System.in);
      while(!nb_valide) {
        try {
          nb = sc.nextInt();
          if(max != null && nb > max) {
            System.out.println("Merci de donner un nombre plus petit ou égale à "+max);
          } else if(min != null && nb < min) {
            System.out.println("Merci de donner un nombre plus grand ou égale à "+min);
          } else {
            nb_valide = true;
          }
        } catch (InputMismatchException e) {
          System.out.println("Merci de donner un nombre valide");
          sc.nextLine();
        }
      }
      return nb;
    }
    /**
    * methode permettant de lancer une Partie de Puissance4
    * @param p correspond à la partie que l'on veut lancer
    * @return un code de fin de partie pour savoir comment elle s'est finie
    */
    private static int lancerPartie(Partie p) throws PartieFinieException{
      boolean partie_finie=false;
      int code_fin = CODE_FIN_JOUEUR_A_GAGNE;
      Scanner scan = new Scanner(System.in);
      String reponse="";
      // Tant que la partie n'est pas finie
      while(!partie_finie) {
        // On fait jouer les joueurs
        try {
          p.faireJouerJoueurs();

        } catch(ActionPartieException e) { // Si un joueur demande d'arreter la partie ou de la sauvegarder
          if(e.getAction() == ActionPartieException.TYPE_SAUVEGARDE) { // Sauvegarde
            code_fin = CODE_FIN_SAUVEGARDE;
            System.out.println("Sauvegarde ...");
            try{
              new Sauvegarde("Sauvegarde.txt").sauvegarder(p);
              partie_finie = true;
            }catch (IOException e2){
              System.out.println("Probleme lors de l'ecriture");
              e2.printStackTrace();
            }catch (Exception e2){
              e2.printStackTrace();
            }
            System.out.println("Sauvegarde réussie");

          } else if(e.getAction() == ActionPartieException.TYPE_ARRET) { // Arret de la partie
            code_fin = CODE_FIN_ARRET;
            partie_finie = true;
          }
        }

        if(p.getGagnant() != -1) { // Si apres que les joueurs aient joué, il y a une gagnant, la partie est finie
          System.out.println("Bravo !!\nL'équipe "+p.getGagnant()+" a gagné");
          partie_finie = true;
        }
      }
      return code_fin;
    }

    /**
    * Methode principale lancee au moment ou le programme est execute
    *
    * @param args les arguments passes au moment de lancer le programme
    */
    public static void main(String[] args) throws ArgumentInvalideException, PartieFinieException {
      if(args.length == 1) {
        if(args[0].equals("-tests")) {
          System.out.println("Lancement des tests");
          PrincipaleTests.lancerTests();
          if(PrincipaleTests.succes_tests)
            System.out.println("\nTous les tests se sont bien passés.");
          return;
        } else {
          System.out.println("option '"+args[0]+"' inconnue");
          return;
        }
      }

      Partie p= null;
      Scanner scan = new Scanner(System.in);
      System.out.println("Voulez-vous reprendre votre sauvegarde ? (O/N)");
      String reponse = scan.nextLine();

      if (reponse.toUpperCase().equals("O")) {
        try {
          p = Sauvegarde.recuperer("Sauvegarde.txt");
        } catch (FileNotFoundException e) {
          System.out.println("Le fichier de lecture n'existe pas ");
        } catch (ClassNotFoundException e) {
          System.out.println("Impossible de convertir la sauvegarde");
        } catch (IOException e) {
          System.out.println("Probleme lors de la lecture");
        }
      }

      if(p == null) { // Si on veut creer un partie ou si on a pas pu récupérer la sauvegarde
        System.out.println("\nCréation de la partie");
        int nb_colonnes, nb_joueurs, nb_ordinateurs;
        // Grille
        System.out.println("Grille :\nCombien de colonnes ?");
        nb_colonnes = demanderEntier(1, 50);

        // Joueurs
        System.out.println("Combien y a-t-il de joueurs ?");
        nb_joueurs = demanderEntier(0, null);
        System.out.println("Combien y a-t-il d'ordinateurs ?");
        nb_ordinateurs = demanderEntier(0, nb_joueurs);

        Joueur[] joueurs = new Joueur[nb_joueurs];

        for(int i=0; i<nb_joueurs-nb_ordinateurs; i++)
          joueurs[i] = new JoueurReel();

        for(int i=nb_joueurs-nb_ordinateurs; i<nb_joueurs; i++)
          joueurs[i] = new JoueurOrdinateur();

        p=new Partie(new Grille(nb_colonnes), joueurs);

      }
      // On lance la partie
      int nb_colonnes;
      boolean nouvelle_partie = false; // S'il faut creer une
      reponse = "O";
      while(reponse.toUpperCase().equals("O")){
        if(nouvelle_partie) {
          System.out.println("Grille :\nCombien de colonnes ?");
          nb_colonnes = demanderEntier(1, 50);
          p.rejouer(nb_colonnes);
        } else {
          nouvelle_partie = true;
        }

        if(lancerPartie(p) == CODE_FIN_JOUEUR_A_GAGNE) {
          System.out.println("Voulez-vous rejouer ? (O/N)");
          reponse = scan.nextLine();
        } else {
          break;
        }
      }
    }
  }
