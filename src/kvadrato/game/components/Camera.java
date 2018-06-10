package kvadrato.game.components;

import java.util.function.Function;
import kvadrato.utils.Vec2;
import kvadrato.utils.Vec2r;
import kvadrato.utils.Vec2rs;
import kvadrato.game.Component;
import kvadrato.game.Entity;
import kvadrato.game.components.Camera;

public class Camera extends Component
{
  private Function<Entity,Vec2rs> eyeFn;
  public void setFn(Function<Entity,Vec2rs>fn)
  {
    eyeFn=fn;
  }
  public Vec2rs getEye()
  {
    Vec2r place;
    double scale;
    if(eyeFn==null)
    {
      Physics ph=(Physics)getEntity().getComponent("Physics");
      if(ph!=null)
      {
        place=ph.getPlace();
      }
      else
      {
        place=new Vec2r();
      }
      scale=1.0;
    }
    else return eyeFn.apply(this.getEntity());
    return new Vec2rs(place,scale);
  }
  public void fix(){}
  public void doThings(){}
  public void update(){}
}
