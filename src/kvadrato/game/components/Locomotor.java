package kvadrato.game.components;

import java.util.function.Supplier;
import kvadrato.game.components.Physics;
import kvadrato.game.Component;
import kvadrato.game.Transform;
import kvadrato.game.Entity;

public class Locomotor extends Component
{
  Supplier<Transform> fn;


  public void setFn(Supplier<Transform> func)
  {
    fn=func;
  }


  public Transform getAcceleration()
  {
    if(fn!=null)
      return fn.get();
    return new Transform();
  }

  public void fix(){}
  public void doThings()
  {
    Entity e=getEntity();
    Physics q=(Physics)e.getComponent("Physics");
    if(q==null)
      return;
    q.addVelocity(getAcceleration());
  }
  public void update(){}
}
