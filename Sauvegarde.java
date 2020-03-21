import java.io.*;

public class Sauvegarde {

  private String fich_sauv;

  public Sauvegarde(String txt){
    this.fich_sauv = txt;
  }

    public void sauvegarder(Grille c, Joueur[] j) throws IOException, Exception {
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(this.fich_sauv));
        writer.writeObject(c);
        writer.writeObject(j);
        writer.close();
    }

    public static <T> T recupererObjet(String nom_fich) throws IOException,Exception {
        T obj = null;
        ObjectInputStream Reader= new ObjectInputStream(new FileInputStream(nom_fich));
        obj = (T)(Reader.readObject());
        Reader.close();
        return obj;
    }
}
