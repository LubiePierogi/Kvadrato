package kvadrato.game.components;

import kvadrato.game.Component;
import kvadrato.game.EventProxy;

public class EventSender extends Component
{
  private EventProxy ep;
  public void setProxy(EventProxy t)
  {
    ep=t;
  }
  public void send(String what)
  {
    if(ep!=null)
      ep.send(what);
  }
  public void fix(){}
  public void doThings(){}
  public void update(){}
}
