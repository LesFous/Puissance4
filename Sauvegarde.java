import java.io.*;

public class Sauvegarde {

  private String fich_sauv;

  private String fich_continuer;

  public Sauvegarde(){
    this.fich_sauv = "sauv.txt";
  }

    public void sauvegarder(Grille c){
    try{
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(this.fich_sauv));
        writer.writeObject(c);
        writer.close();
      }catch (FileNotFoundException e){
        System.out.println("Le fichier d'ecriture n'existe pas ");
        e.printStackTrace();
      }catch(IOException e){
        System.out.println("Problème lors de l'ecriture");
        e.printStackTrace();
      }
    }

    public static Grille reprendre(String txt){
      Grille c = null;
      try{
        ObjectInputStream Reader = new ObjectInputStream(new FileInputStream(txt));
         c = (Grille)(Reader.readObject());
        Reader.close();

      }catch (FileNotFoundException e){
        System.out.println("Le fichier d'ecriture n'existe pas ");
        e.printStackTrace();
      }catch(IOException e){
        System.out.println("Problème lors de l'ecriture");
        e.printStackTrace();
      }catch (Exception e){
        e.printStackTrace();
      }
      return c;
    }
}
