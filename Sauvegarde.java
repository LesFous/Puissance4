import java.io.*;

public class Sauvegarde {

  private String nom_fich;

  public Sauvegarde(String nom_fich) {
    this.nom_fich = nom_fich;
  }

  public void sauvegarder(Grille c) throws IOException {
    ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(nom_fich));
    writer.writeObject(c);
    writer.close();
  }

  public static Grille recuperer(String nom_fich) throws IOException, ClassNotFoundException {
    ObjectInputStream reader = new ObjectInputStream(new FileInputStream(nom_fich));
    Grille obj = null;
    obj = (Grille)(reader.readObject());
    reader.close();
    return obj;
  }
}
