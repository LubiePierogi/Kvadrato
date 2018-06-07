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

  //List<AppearanceElement> elements;

  private Function<Entity,List<AppearanceElement>> fn;

  public Appearance()
  {
    //elements=new ArrayList<AppearanceElement>();
    fn=null;
  }

  public void setFn(Function<Entity,List<AppearanceElement>> func)
  {
    fn=func;
  }
  public List<AppearanceElement> getElements()
  {
    //return Collections.unmodifiableList(elements);
    if(fn!=null)
      return fn.apply(getEntity());
    return null;
  }

  public void fix(){}
  public void doThings(){}
  public void update(){}
}
