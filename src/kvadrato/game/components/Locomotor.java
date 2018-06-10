package kvadrato.game.components;

import java.util.function.Function;
import kvadrato.game.components.Physics;
import kvadrato.game.Component;
import kvadrato.game.Transform;
import kvadrato.game.Entity;

public class Locomotor extends Component
{
  /**
   * Funkcja zwracająca przyspieszenie, które sam na sobie wywołuje jednostka.
   *
   * @return przyspiesznie, może być null, to wtedy i tak jest zamieniane na 0.
   */
  private Function<Entity,Vec2r> fn;
  public void setFn(Function<Entity,Vec2r> func)
  {
    fn=func;
  }
  public Vec2r getAcceleration()
  {
    Transform q;
    if(fn!=null)
      q=fn.apply(this.getEntity());
    else q=new Transform();
    return q;
  }

  public void fix(){}
  public void doThings()
  {
    //System.out.println("ewropjpifjpojopwej");
    Entity e=getEntity();
    Physics q=(Physics)e.getComponent("Physics");
    if(q==null)
      return;
    q.accelerate(getAcceleration());
  }
  public void update(){}
}
