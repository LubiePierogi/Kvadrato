package kvadrato.game.components;

import java.util.function.Function;
import kvadrato.game.components.Physics;
import kvadrato.game.Component;
import kvadrato.game.Transform;
import kvadrato.game.Entity;

public class Locomotor extends Component
{
  private Function<Entity,Transform> fn;


  public void setFn(Function<Entity,Transform> func)
  {
    fn=func;
  }


  public Transform getAcceleration()
  {
    System.out.println("getAcceleration 1");
    Transform q;
    System.out.println("getAcceleration 2");
    if(fn!=null)
      q=//return
       fn.apply(this.getEntity());
    else q=//return
     new
    Transform();
    System.out.print("[][][] "+q.x+"\n[][][] "+q.y+'\n');
    return q;
  }

  public void fix(){}
  public void doThings()
  {
    System.out.println("ewropjpifjpojopwej");
    Entity e=getEntity();
    Physics q=(Physics)e.getComponent("Physics");
    if(q==null)
      return;
    q.accelerate(getAcceleration());
  }
  public void update(){}
}
