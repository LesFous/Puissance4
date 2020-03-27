
    /**
    * classe qui creer un ordinateur qui va jouer automatiquement au puissance4
    */
    public class JoueurOrdinateur extends Joueur {

    /**
    * attribut static qui permet de compter le nombre de tour que l'ordinateur a joue
    */
    private static int tour = 0;

    /**
    * methode qui permet de donner une colonne au hasard afin que l'ordinateur joue
    * @param nbcol parametre correspondant au nombre de colonnes dans la grille
    * @return un entier qui correpond a la colonne dans lequel l'ordinateur a joue
    */
    @Override
    public  int jouer(int nbcol){
        return (int)(Math.random()*nbcol-1)+1;
    }

    /**
    * methode qui permet de resumer l'ordinateur en chaine de caractere
    * @return une chaine de caractere avec l'id de l'equipe du joueur
    */
    @Override
    public String toString() {
      return "Ordinateur: (equipe "+getTeamId()+")";
    }

  }
