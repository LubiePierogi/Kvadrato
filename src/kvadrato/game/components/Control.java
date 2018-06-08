package kvadrato.game.components;

import kvadrato.game.Component;
import kvadrato.game.ControlThing;

public class Control extends Component
{

  private ControlThing thing;

  public void setThing(ControlThing t)
  {
    thing=t;
  }
  public ControlThing getThing()
  {
    return thing;
  }

  public void fix(){}
  public void doThings(){}
  public void update(){}
}
