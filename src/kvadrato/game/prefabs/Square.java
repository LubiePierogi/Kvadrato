package kvadrato.game.prefabs;

import kvadrato.game.Prefab;
import kvadrato.game.Entity;
import kvadrato.game.components.Appearance;
import kvadrato.game.components.Physics;
import kvadrato.game.components.SquareComponent;
import kvadrato.game.appearance.SquareSquare;
import kvadrato.game.other.BackgroundColor;
import kvadrato.game.appearance.AppearanceElement;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import kvadrato.game.Transform;
import kvadrato.game.components.Control;
import kvadrato.game.ControlThing;
import kvadrato.game.components.Locomotor;

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
  private final static Function<Entity,Transform>locomotorFn=ent->
  {
    Control control=(Control)ent.getComponent("Control");
    ControlThing ct=control.getThing();
    Transform a=new Transform();
    if(ct!=null)
    {
      boolean up=!ct.get("up").equals("");
      boolean right=!ct.get("right").equals("");
      boolean down=!ct.get("down").equals("");
      boolean left=!ct.get("left").equals("");
      if(up&&!down)
        a=a.add(new Transform(1,0,0));
      if(!up&&down)
        a=a.add(new Transform(-.25,0,0));
      if(right&&!left)
        a=a.add(new Transform(0,0,-.8));
      if(!right&&left)
        a=a.add(new Transform(0,0,.8));


      String cc=ct.get("color");
      SquareComponent sc=(SquareComponent)ent.getComponent("SquareComponent");
      switch(cc)
      {
        case "q":
          sc.setColor(BackgroundColor.BLUE);
          break;
        case "w":
          sc.setColor(BackgroundColor.GREEN);
          break;
        case "e":
          sc.setColor(BackgroundColor.YELLOW);
          break;
        case "r":
          sc.setColor(BackgroundColor.CYAN);
          break;
        case "a":
          sc.setColor(BackgroundColor.PURPLE);
          break;
        case "s":
          sc.setColor(BackgroundColor.RED);
          break;
        case "d":
          sc.setColor(BackgroundColor.BROWN);
          break;
        case "f":
          sc.setColor(BackgroundColor.ORANGE);
          break;
      }
    }
    return a;
  };
  private final static Function<Entity,Transform>physicsFn=ent->
  {
    Physics physics=(Physics)ent.getComponent("Physics");
    Transform v=physics.getVelocity();
    v=v.mul(-.75);
    return v;
  };
  public void makeEntity(Entity ent)
    throws ClassNotFoundException,InstantiationException,IllegalAccessException
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
//      SquareSquare sqsq=new SquareSquare();
//      sqsq.colorOfSquare=BackgroundColor.WHITE;
//      q.addElement(new SquareSquare());

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
