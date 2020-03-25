import java.io.*;

public class Sauvegarde {

  private String nom_fich;

  public Sauvegarde(String nom_fich) {
    this.nom_fich = nom_fich;
  }

  public void sauvegarder(Partie c) throws IOException {
    ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(nom_fich));
    writer.writeObject(c);
    writer.close();
  }

  public static Partie recuperer(String nom_fich) throws IOException, ClassNotFoundException {
    ObjectInputStream reader = new ObjectInputStream(new FileInputStream(nom_fich));
    Partie obj = null;
    obj = (Partie)(reader.readObject());
    reader.close();
    return obj;
  }
}
