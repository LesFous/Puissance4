import java.lang.*;

  /**
  * classe qui permet de créer un exception personnalisee
  */
  public class ActionPartieException extends Exception {
    /**
    * attribut entier final et statique correspondant au type sauvegarde
    */
    public static final int TYPE_SAUVEGARDE = 1;

    /**
    *  attribut entier final et statique correspondant au type arret
    */
    public static final int TYPE_ARRET = 2;

    /**
    *  attribut entier final et statique correspondant au type continuer
    */
    public static final int TYPE_CONTINUER = 3;

    /**
    *  attribut entier final et statique correspondant au type retour en arriere
    */
    public static final int TYPE_RETOUR_ARRIERE=4;

    /**
    * attribut entier correspondant au type d'exception
    */
    private int type;

      /**
      * contructeur d'une ActionPartieException avec un String passe en parametre
      * @param message parametre correspondant au type d'exception
      */
      public ActionPartieException(String message){
        super(message);
        int val = Integer.parseInt(message);
        if(val == TYPE_SAUVEGARDE || val == TYPE_ARRET || val == TYPE_RETOUR_ARRIERE)
          type = val;
        else
          type = TYPE_CONTINUER;
      }

      /**
      * constructeur d'une ActionPartieException sans parametre
      */
      public ActionPartieException(){
        super();
        type = TYPE_CONTINUER;
      }

      /**
      * methode qui retourne le type d'exception sous la forme d'un entier
      * @return l'entier du type d'exception
      */
      public int getAction() {
        return type;
      }
  }
