package kvadrato.game.prefabs;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

import kvadrato.utils.vec2.Vec2dr;
import kvadrato.game.Entity;
import kvadrato.game.Prefab;
import kvadrato.game.ControlProxy;
import kvadrato.game.other.BgColor;
import kvadrato.game.components.Appearance;
import kvadrato.game.components.Physics;
import kvadrato.game.components.BgColorComponent;
import kvadrato.game.components.Control;
import kvadrato.game.components.Locomotor;
import kvadrato.game.appearance.SquareSquare;
import kvadrato.game.appearance.AppearanceElement;

/**
 * Klasa kwadratu, którym steruje gracz.
 */
public class Square extends Prefab
{
  private final static Function<Entity,List<AppearanceElement>>
    appearanceFn
    =ent->
  {
    ArrayList<AppearanceElement> list=new ArrayList<AppearanceElement>();
    SquareSquare sqsq=new SquareSquare();
    sqsq.colorOfSquare=
      ((SquareComponent)ent.getComponent("SquareComponent")).getColor();
    sqsq.x=0.0;
    sqsq.y=0.0;
    sqsq.angle=0.0;
    sqsq.scale=1.0;
    list.add(sqsq);
    return list;
  };
  private final static Function<Entity,Vec2dr>locomotorFn=ent->
  {
    Control ctl=(Control)ent.getComponent("Control");
    Vec2dr a=new Vec2dr();
    boolean up=!ct.get("up").equals("");
    boolean right=!ct.get("right").equals("");
    boolean down=!ct.get("down").equals("");
    boolean left=!ct.get("left").equals("");
    if(up&&!down)
      a=a.add(new Transform(1,0,0));
    if(!up&&down)
      a=a.add(new Transform(-.25,0,0));
    if(right&&!left)
      a=a.add(new Transform(0,0,-1.8));
    if(!right&&left)
      a=a.add(new Transform(0,0,1.8));

    String cc=ctl.get("color");
    SquareComponent sc=(SquareComponent)ent.getComponent("SquareComponent");
    switch(cc)
    {
      case "q":sc.setColor(BgColor.BLUE);break;
      case "w":sc.setColor(BgColor.GREEN);break;
      case "e":sc.setColor(BgColor.YELLOW);break;
      case "r":sc.setColor(BgColor.CYAN);break;
      case "a":sc.setColor(BgColor.PURPLE);break;
      case "s":sc.setColor(BgColor.RED);break;
      case "d":sc.setColor(BgColor.BROWN);break;
      case "f":sc.setColor(BgColor.ORANGE);break;
      }
    }
    return a;
  };
  private final static Function<Entity,Vec2dr>physicsFn=ent->
  {
    Physics physics=(Physics)ent.getComponent("Physics");
    Vec2dr v=physics.getVelocity();
    v=v.mul(-.75);
    return v;
  };
  public void makeEntity(Entity ent)
    throws GameException
  {
    ent.addComponent("Physics");
    ent.addComponent("SquareComponent");
    ent.addComponent("Camera");
    ent.addComponent("Collider");
    ent.addComponent("Appearance");
    ent.addComponent("Locomotor");
    ent.addComponent("Control");

    // Appearance
    {
      Appearance q=(Appearance)ent.getComponent("Appearance");
      q.setFn(appearanceFn);
    }

    // Physics
    {
      Physics q=(Physics)ent.getComponent("Physics");
      q.setType(Physics.Type.DYNAMIC);
      q.setMass(1.0);
      q.setFn(physicsFn);
    }
    // Camera
    {
      // W kamerze nic nie zmieniamy.
    }
    // Control
    {
      // Tu na razie też nic.
    }
    // SquareComponent
    {
      Locomotor q=(Locomotor)ent.getComponent("Locomotor");
      q.setFn(locomotorFn);
    }
  }
}
