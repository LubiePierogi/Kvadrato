package kvadrato.game.components;

import java.util.function.Function;

import kvadrato.utils.vec2.Vec2dr;
import kvadrato.game.Entity;
import kvadrato.game.Component;
import kvadrato.game.components.Physics;

public class Locomotor extends Component
{
  /**
   * Funkcja zwracająca przyspieszenie, które sam na sobie wywołuje jednostka.
   *
   * @return przyspiesznie, może być null, to wtedy i tak jest zamieniane na 0.
   */
  private Function<Entity,Vec2dr> fn;
  public void setFn(Function<Entity,Vec2dr> func)
  {
    fn=func;
  }
  public Vec2dr getAcceleration()
  {
    Vec2dr q;
    if(fn!=null)
      q=fn.apply(this.getEntity());
    else q=new Vec2dr();
    return q;
  }

  public void fix(){}
  public void doThings()
  {
    Entity e=getEntity();
    Physics q=(Physics)e.getComponent("Physics");
    if(q==null)
      return;
    q.accelerate(getAcceleration());
  }
  public void update(){}
}
