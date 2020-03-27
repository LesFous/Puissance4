import java.io.*;

  /**
  * classe qui permet de sauvegarder une partie ou de reprendre une partie deja existante
  */
  public class Sauvegarde {

    /**
    * attribut qui correspond au fichier ou la partie est enregistree
    */
    private String nom_fich;

    /**
    * constructeur d'une sauvegarde avec le nom du fichier passe en parametre
    * @param nom_fich correspond au nom du fichier ou va etre sauvegarde la partie
    */
    public Sauvegarde(String nom_fich) {
      this.nom_fich = nom_fich;
    }

    /**
    * methode qui permet de sauvegarder une partie passee en parametre
    * @param c correspond a la partie que l'on veut sauvegarder
    */
    public void sauvegarder(Partie c) throws IOException {
      ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(nom_fich));
      writer.writeObject(c);
      writer.close();
    }

    /**
    * methode statique permettant de reprendre une partie deja en cours a partir d'un fichier
    * @param nom_fich parametre correspondant au fichier ou est stockee la partie
    * @return la partie recuperee a partir du fichier
    */
    public static Partie recuperer(String nom_fich) throws IOException, ClassNotFoundException {
      ObjectInputStream reader = new ObjectInputStream(new FileInputStream(nom_fich));
      Partie obj = null;
      obj = (Partie)(reader.readObject());
      reader.close();
      return obj;
    }
  }
