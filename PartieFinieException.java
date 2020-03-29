/**
* classe qui permet de creer une PartieFinieException
*/
public class PartieFinieException extends Exception {
  /**
  * constructeur permettant de creer une PartieFinieException
  */
  public PartieFinieException() {
    super();
  }

  /**
  * constructeur permettant de creer une PartieFinieException avec un String passee en parametre
  * @param mess correspond au message de l'exception 
  */
  public PartieFinieException(String mess) {
    super(mess);
  }
}
