package kvadrato.game.components;

import java.util.function.Function;

import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.utils.vec2.Vec2drs;
import kvadrato.game.Component;
import kvadrato.game.Entity;
import kvadrato.game.components.Camera;

public class Camera extends Component
{
  private Function<Entity,Vec2drs> eyeFn;
  public void setFn(Function<Entity,Vec2drs>fn)
  {
    eyeFn=fn;
  }
  public Vec2drs getEye()
  {
    Vec2dr place;
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
        place=new Vec2dr();
      }
      scale=1.0;
    }
    else return eyeFn.apply(this.getEntity());
    return new Vec2drs(place,scale);
  }
  public void fix(){}
  public void doThings(){}
  public void update(){}
}
