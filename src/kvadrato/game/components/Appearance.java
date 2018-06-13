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
  private RenderDistanceFnType renderDistanceFn;
  public static interface RenderDistanceFnType
  {double call(Entity e);}

  private ListFnType listFn;
  public static interface ListFnType
  {List<AppearanceElement> call(Entity e);}

  public Appearance()
  {
    listFn=null;
    renderDistanceFn=null;
  }
  public void setListFn(ListFnType func)
  {
    listFn=func;
  }
  public void setRenderDistanceFn(RenderDistanceFnType func)
  {
    renderDistanceFn=func;
  }
  public List<AppearanceElement> getElements()
  {
    if(listFn!=null)
      return listFn.call(getEntity());
    return null;
  }
  public double getRenderDistance()
  {
    if(renderDistanceFn!=null)
      return renderDistanceFn.call(getEntity());
    return 0.0;
  }
  public void fix(){}
  public void doThings(){}
  public void update(){}
}
