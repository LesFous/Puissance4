import java.lang.*;

  public class ActionPartieException extends Exception {
    private String mess;
      public ActionPartieException(String message){
        super(message);
        mess = message;
      }

      public ActionPartieException(){
        super();
      }
  }
