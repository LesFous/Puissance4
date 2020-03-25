import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

class Principale {
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
      } catch (Exception e) {
        System.out.println("Merci de donner un nombre valide");
      }
    }
    return nb;
  }


  /**
  * Methode principale lancee au moment ou le programme est execute
  *
  * @param args les arguments passes au moment de lancer le programme
  */
  public static void main(String[] args) {
    Grille g = null;
    Scanner scan = new Scanner(System.in);
    System.out.println("Voulez-vous reprendre votre sauvegarde ? (O/N)");
    String reponse = scan.nextLine();

    if (reponse.toUpperCase().equals("O")) {
      try {
        g = Sauvegarde.recuperer("Sauvegarde.txt");
      } catch (FileNotFoundException e) {
        System.out.println("Le fichier de lecture n'existe pas ");
        e.printStackTrace();
      }catch (ClassNotFoundException e) {
        System.out.println("Impossible de convertir la sauvegarde");
        e.printStackTrace();
      } catch (IOException e) {
        System.out.println("Probleme lors de la lecture");
        e.printStackTrace();
      }

    } else {
      int nb_colonnes, nb_joueurs, nb_ordinateurs;
      // Grille
      System.out.println("Grille :\nCombien de colonnes ?");
      nb_colonnes = demanderEntier(0, 40);

      // Joueurs
      System.out.println("Combien y a-t-il de joueurs ?");
      nb_joueurs = demanderEntier(0, null);
      System.out.println("Combien y a-t-il d'ordinateurs ?");
      nb_ordinateurs = demanderEntier(0, nb_joueurs);

      g = new Grille(nb_colonnes);
      Joueur[] joueurs = new Joueur[nb_joueurs];

      for(int i=0; i<nb_ordinateurs; i++)
        joueurs[i] = new JoueurOrdinateur();

      for(int i=nb_ordinateurs; i<nb_joueurs; i++)
        joueurs[i] = new JoueurReel();

      g.setJoueurs(joueurs);
    }
    // On lance la partie
    boolean partie_finie=false;
    while(!partie_finie) {
      g.faireJouerJoueurs();
      if(g.getGagnant() != -1) {
        System.out.println("Bravo !!\nL'équipe "+g.getGagnant()+" a gagné");
        partie_finie = true;

      } else {
        System.out.println("Voulez-vous sauvegarder ou continuer ou arreter ? (A/S/C)");
        reponse = scan.nextLine().toUpperCase();
        if (reponse.equals("S")){
          try{
            new Sauvegarde("Sauvegarde.txt").sauvegarder(g);
            partie_finie = true;
          }catch (IOException e){
            System.out.println("Probleme lors de l'ecriture");
            e.printStackTrace();
          }catch (Exception e){
            e.printStackTrace();
          }
        } else if(reponse.equals("A")){
          partie_finie = true;
        }
      }
    }
  }
}