package kvadrato.game.components;

import kvadrato.game.Component;
import kvadrato.game.ControlBroker;

public class Control extends Component
{
  private ControlProxy cp;
  public void setProxy(ControlProxy t)
  {
    cp=t;
  }
  public ControlThing getProxy()
  {
    return cp;
  }
  public String get(String what)
  {
    return cp.get(what);
  }
  public void fix(){}
  public void doThings(){}
  public void update(){}
}
