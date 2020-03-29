import java.lang.*;

  public class ActionPartieException extends Exception {

    public static final int TYPE_SAUVEGARDE = 1;
    public static final int TYPE_ARRET = 2;
    public static final int TYPE_CONTINUER = 3;

    private int type;

      public ActionPartieException(String message){
        super(message);
        int val = Integer.parseInt(message);
        if(val == TYPE_SAUVEGARDE || val == TYPE_ARRET)
          type = val;
        else
          type = TYPE_CONTINUER;
      }

      public ActionPartieException(){
        super();
        type = TYPE_CONTINUER;
      }

      public int getAction() {
        return type;
      }
  }
