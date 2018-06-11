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
  private Function<Entity,Double> renderDistanceFn;
  private Function<Entity,List<AppearanceElement>> listFn;
  public Appearance()
  {
    listFn=null;
    renderDistanceFn=null;
  }
  public void setFn(Function<Entity,List<AppearanceElement>> func)
  {
    listFn=func;
  }
  public void setRenderDistanceFn(Function<Entity,Double>func)
  {
    renderDistanceFn=func;
  }
  public List<AppearanceElement> getElements()
  {
    if(listFn!=null)
      return listFn.apply(getEntity());
    return null;
  }
  public double getRenderDistance()
  {
    if(renderDistanceFn!=null)
      return renderDistanceFn.apply(getEntity());
    return 0.0;
  }
  public void fix(){}
  public void doThings(){}
  public void update(){}
}
