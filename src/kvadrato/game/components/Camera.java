package kvadrato.game.components;

import kvadrato.game.Eye;
import kvadrato.game.Component;
import kvadrato.game.Vector2d;
import kvadrato.game.Transform;
import java.util.function.Supplier;

public class Camera extends Component
{
  private Supplier<Eye> eyeFn;
  public void setFn(Supplier<Eye>fn)
  {
    eyeFn=fn;
  }

  public Eye getEye()
  {
    Transform place;
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
        place=new Transform();
      }
      scale=1.0;
    }
    else return eyeFn.get();
    return new Eye(place.x,place.y,place.angle,scale);
  }

  public void fix(){}
  public void doThings(){}
  public void update(){}
}
