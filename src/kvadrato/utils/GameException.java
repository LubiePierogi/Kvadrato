package kvadrato.utils;
/**
 * Wyjątek rzucany przez grę.
 */
public class GameException extends Exception
{
  public GameException(){}
  public GameException(String msg){super(msg);}
  public GameException(Throwable cause){super(cause);}
  public GameException(String msg,Throwable cause){super(msg,cause);}
  public String getMessage(){return super.getMessage();}
}
