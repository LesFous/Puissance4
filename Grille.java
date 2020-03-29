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
  * Permet d'obtenir la liste des colonnes triées de la plus remplie a la moins remplie
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
  * Méthode pour enlever tous les jetons d'une grille
  */
  public void vider() {
    for(Colonne c: colonnes)
      c.vider();
  }

  /**
  * Méthode qui change le nombre de colonnes dans une Grille
  *
  * @param nb le nouveau nombre de colonnes
  */
  public void setNbColonnes(int nb) {
    if(nb <= 0)
      throw new NullPointerException("Impossible de changer le nombre de colonne d'une grille pour:"+nb);

    for(int i=colonnes.size(); i<nb; i++)
      colonnes.add(new Colonne());
    for(int i=colonnes.size()-1; i>=nb; i--)
      colonnes.remove(i);
  }

  /**
  * Methode qui permet d'afficher la grille du jeu avec ses jetons
  * Et les informations telles que le remplissage moyen ou les colonnes triees par remplissage
  *
  */
  public void afficher() {
    afficherSepLigne();
    Jeton jeton;
    String jeton_str;
    int nb_lignes = Math.max(getNbLignes(), 1);

    // Dessin de la grille
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
    // Affichage des informations
    ArrayList<Colonne> colonnes_triees = getColonnesTriees();
    StringBuilder builder = new StringBuilder();
    double somme_moy = 0f;
    builder.append("N°colonnes triées par remplissage (n°:nb jetons):\n");
    for(int i=0; i<colonnes_triees.size(); i++) {
      // le numero de la colonne + " " + sa taille
      builder.append(colonnes.indexOf(colonnes_triees.get(i))+1+":"+colonnes_triees.get(i).size()+"  ");
      somme_moy += colonnes_triees.get(i).size()*1.0f/nb_lignes;
    }
    System.out.println(somme_moy);
    if(colonnes.size() > 0)
      builder.append("\nRemplisage moyen : "+String.format("%.2f",somme_moy/colonnes.size(), 2));
    else
      builder.append("\nRemplisage moyen : 0");
    System.out.println(builder+"\n");
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

  /**
  * Methode privee qui permet d'ajouter un jeton a une colonne
  * Si le nombre de lignes de suffit pas, il est augmneté de un
  *
  * @param j Le jeton à ajouter (non null)
  * @param col Le numero de la colonne a laquelle on ajoute le Jeton (entre 0 et nb_colonnes compris)
  */
  public void ajouterJeton(Jeton j, int col) {
    if (col < 0 || col >= getColonnes().size()) {
      throw new IndexOutOfBoundsException("La colonne n'est pas valide :"+col);
    }
    getColonnes().get(col).ajouter(j);
  }

}
