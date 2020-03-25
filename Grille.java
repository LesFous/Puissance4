import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

/**
* Classe qui represente un grille de puissance 4
*
*/
public class Grille implements Serializable{
  /**
  * attribut qui represente toutes les colonnes de la grille
  */
  private List<Colonne> colonnes;


  /**
  * Constructeur qui permet de créer une grille d'une taille donnee
  *
  * @param nb_colonnes le nombre de colonnes
  */
  public Grille(int nb_colonnes) {
    if(nb_colonnes <= 0)
      throw new NullPointerException("Impossible de créer un grille avec "+nb_colonnes);
    colonnes = new ArrayList<Colonne>(nb_colonnes);
    for(int i=0; i<nb_colonnes; i++) {
      colonnes.add(new Colonne());
    }
  }

  /**
  * methode qui pertmet d'obtenir la liste des colonnes 
  * @return la liste de colonne de la grille
  */

  public List<Colonne> getColonnes(){
    return this.colonnes;
  }

  /**
  * Permet d'obtenir la liste des colones triées de la plus remplie a la moins remplie
  *
  * @return la listes des colonnes triées
  */
  public ArrayList<Colonne> getColonnesTriees() {
    ArrayList<Colonne> copie = new ArrayList<Colonne>(colonnes);
    Collections.<Colonne>sort(copie, new ComparateurTaillesColonnes());
    return copie;
  }

  /**
  * Methode pour connaître le nombre de lignes d'une grille en fonction de sa colonne la plus remplie
  *
  * @return le nombre de lignes
  */
  public int getNbLignes() {
    return getColonnesTriees().get(0).size();
  }

  /**
  * Methode qui permet d'afficher la grille du jeu avec ses jetons
  *
  */
  public void afficher() {
    afficherSepLigne();
    Jeton jeton;
    String jeton_str;
    int nb_lignes = Math.max(getNbLignes(), 1);

    // Pour chaque ligne
    for(int j=nb_lignes-1; j>=0; j--) {
      // Pour chaque colonne
      for (int i=0; i<colonnes.size(); i++) {
        // On recupere le jeton s'il y en a un
        if(j >= colonnes.get(i).size())
          jeton = null;
        else {
          jeton = colonnes.get(i).getJeton(j);
        }
        // On recupere la représentation du jeton
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

  /**
  * Methode privee qui permet d'afficher la séparation entre deux lignes
  *
  */
  private void afficherSepLigne() {
    for(int i=0; i < colonnes.size(); i++) {
      System.out.print("+-");
    }
    System.out.println("+");;
  }

}
