import java.io.*;

public class Sauvegarde {

  private String grille_sauv;

  private String equipe_sauv;

  public Sauvegarde(String txt,String tx){
    this.grille_sauv = txt;
    this.equipe_sauv = tx;
  }

    public void sauvegarder(Grille c, Joueur[] j){
    try{
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(this.grille_sauv));
        writer.writeObject(c);
        writer.close();
        ObjectOutputStream writer2 = new ObjectOutputStream(new FileOutputStream(this.equipe_sauv));
        writer2.writeObject(j);
        writer2.close();
      }catch (FileNotFoundException e){
        System.out.println("Le fichier d'ecriture n'existe pas ");
        e.printStackTrace();
      }catch(IOException e){
        System.out.println("Problème lors de l'ecriture");
        e.printStackTrace();
      }
    }

    public static Grille reprendrePartie(String txt){
      Grille c = new Grille(5,5);
      try{
        ObjectInputStream Reader = new ObjectInputStream(new FileInputStream(txt));
         c = (Grille)(Reader.readObject());
        Reader.close();

      }catch (FileNotFoundException e){
        System.out.println("Le fichier de lecture n'existe pas ");
        e.printStackTrace();
      }catch(IOException e){
        System.out.println("Problème lors de la lecture");
        e.printStackTrace();
      }catch (Exception e){
        e.printStackTrace();
      }
      return c;
    }

    public static Joueur[] reprendreJoueurs(String txt){
      Joueur[] equipe = new Joueur[0];
      try{
        ObjectInputStream Reader= new ObjectInputStream(new FileInputStream(txt));
        equipe = (Joueur[])(Reader.readObject());
        Reader.close();
      }catch (FileNotFoundException e){
        System.out.println("Le fichier de lecture n'existe pas ");
        e.printStackTrace();
      }catch (IOException e ){
        System.out.println("Prbleme lors de la lecture");
        e.printStackTrace();
      }catch (Exception e){
        e.printStackTrace();
      }
      return equipe;
    }

    public static <T> T recupererObjet(String nom_fich) {
      T obj = null;
      try{
        ObjectInputStream Reader= new ObjectInputStream(new FileInputStream(nom_fich));
        obj = (T)(Reader.readObject());
        Reader.close();
      }catch (FileNotFoundException e){
        System.out.println("Le fichier de lecture n'existe pas ");
        e.printStackTrace();
      }catch (IOException e ){
        System.out.println("Prbleme lors de la lecture");
        e.printStackTrace();
      }catch (Exception e){
        e.printStackTrace();
      }
      return obj;
    }
}
