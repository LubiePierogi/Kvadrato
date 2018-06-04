package kvadrato.game.components;

import kvadrato.game.Eye;
import kvadrato.game.Component;
import kvadrato.game.Vector2d;
import java.util.function.Supplier;

class Camera extends Component
{
  private Supplier<Eye> eyeFn;
  public void setFn(Supplier<Eye>fn)
  {
    eyeFn=fn;
  }

  Eye getEye()
  {
    Vector2d place;
    double angle;
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
        place=new Vector2d();
      }
      angle=0.0;
      scale=1.0;
    }
    else return eyeFn.get();
    return new Eye(place.x,place.y,angle,scale);
  }

  public void fix(){}
  public void doThings(){}
  public void update(){}
}
