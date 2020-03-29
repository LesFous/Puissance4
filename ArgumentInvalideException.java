/**
* classe qui permet de creer une ArgumentInvalideException
*/
public class ArgumentInvalideException extends Exception {
  /**
  * constructeur d'une ArgumentInvalideException avec un String passé en parametre
  * @param mess correspondant au message passe en parametre
  */
  public ArgumentInvalideException(String mess) {
    super(mess);
  }

  /**
  * contructeur d'une ArgumentInvalideException sans parametre
  */
  public ArgumentInvalideException() {
    super();
  }
}
