package kvadrato.utils;
/**
 * Wyjątek rzucany przez grę.
 */
public class GameException extends Exception
{
  public Exception(){}
  public Exception(String msg){super(msg);}
  public Exception(Throwable cause){super(cause);}
  public Exception(String msg,Throwable cause){super(msg,cause);}
  public String getMessage(){return super.getMessage();}
}
