package kvadrato.game;

import java.util.Objects;
import java.util.Map;
import java.util.TreeMap;

public class EventProxy
{
  private EventListener eventListener;
  public static interface EventListener
  {void call(String s);}
  public EventProxy()
  {}
  public synchronized void send(String what)
  {
    if(eventListener!=null)
      eventListener.call(what);
  }
  public synchronized void setEventListener(EventListener el)
  {
    eventListener=el;
  }
}
