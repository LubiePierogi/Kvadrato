package kvadrato.game.components;

import kvadrato.game.Component;
import kvadrato.game.ControlProxy;

public class Control extends Component
{
  private ControlProxy cp;
  public void setProxy(ControlProxy t)
  {
    cp=t;
  }
  public String get(String what)
  {
    if(cp!=null)
      return cp.get(what);
    return "";
  }
  public void fix(){}
  public void doThings(){}
  public void update(){}
}
