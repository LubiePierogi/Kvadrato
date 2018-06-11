package kvadrato.game.components;

import kvadrato.game.Component;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import kvadrato.game.Entity;
import kvadrato.game.appearance.AppearanceElement;

public class Appearance extends Component
{
  private boolean ignoreDistance;
  private boolean ignoreDistanceNew;
  private Function<Entity,List<AppearanceElement>> fn;
  public Appearance()
  {
    fn=null;
    ignoreDistance=false;
    ignoreDistanceNew=false;
  }
  public void setFn(Function<Entity,List<AppearanceElement>> func)
  {
    fn=func;
  }
  public List<AppearanceElement> getElements()
  {
    if(fn!=null)
      return fn.apply(getEntity());
    return null;
  }
  public void setIgnoreDistance(boolean q)
  {
    ignoreDistanceNew=q;
  }
  public boolean getIgnoreDistance()
  {
    return ignoreDistance;
  }
  public void fix(){}
  public void doThings(){}
  public void update()
  {
    ignoreDistance=ignoreDistanceNew;
  }
}
