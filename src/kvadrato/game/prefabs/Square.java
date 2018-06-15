package kvadrato.game.prefabs;

import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.utils.vec2.Vec2drs;
import kvadrato.game.Entity;
import kvadrato.game.Prefab;
import kvadrato.game.ControlProxy;
import kvadrato.game.other.BgColor;
import kvadrato.game.components.Appearance;
import kvadrato.game.components.Physics;
import kvadrato.game.components.BgColorComponent;
import kvadrato.game.components.Control;
import kvadrato.game.components.EventSender;
import kvadrato.game.components.Locomotor;
import kvadrato.game.components.Camera;
import kvadrato.game.components.Collider;
import kvadrato.game.components.Health;
import kvadrato.game.appearance.AppearanceElement;
import kvadrato.game.appearance.SquareSquare;
import kvadrato.game.collision.ElementaryShape;

/**
 * Klasa kwadratu, którym steruje gracz.
 */
public class Square extends Prefab
{
  private final static Vec2d[]shape=new Vec2d[]
  {
    new Vec2d( .125, .125),
    new Vec2d(-.125, .125),
    new Vec2d(-.125,-.125),
    new Vec2d( .125,-.125)
  };
  public void makeEntity(Entity ent)
    throws GameException
  {

    ent.setTag("Square");
    ent.setTag("Creature");

    ent.addComponent("Physics");
    ent.addComponent("BgColorComponent");
    ent.addComponent("Camera");
    ent.addComponent("Collider");
    ent.addComponent("Appearance");
    ent.addComponent("Locomotor");
    ent.addComponent("Control");
    ent.addComponent("EventSender");
    ent.addComponent("Health");

    // Appearance
    {
      Appearance q=(Appearance)ent.getComponent("Appearance");
      q.setListFn(appearanceFn);
      q.setRenderDistanceFn(renderDistanceFn);
    }
    // Physics
    {
      Physics q=(Physics)ent.getComponent("Physics");
      q.setMass(1.0);
      q.setFn(physicsFn);
    }
    // Camera
    {
      Camera q=(Camera)ent.getComponent("Camera");
      q.setEyeFn(eyeFn);
    }
    // Locomotor
    {
      Locomotor q=(Locomotor)ent.getComponent("Locomotor");
      q.setLocomotorFn(locomotorFn);
    }
    // Collider
    {
      Collider q=(Collider)ent.getComponent("Collider");
      q.setShapeFn(shapeFn);
    }
    // Health
    {
      Health q=(Health)ent.getComponent("Health");
      q.setMax(150.);
      q.change(1000.);
      q.setOnDeathFn(onDeathFn);
      q.setOverTime(healthOverTimeFn);
    }
  }
  private final static Appearance.ListFnType
    appearanceFn
    =ent->
  {
    ArrayList<AppearanceElement> list=new ArrayList<AppearanceElement>();
    SquareSquare sqsq=new SquareSquare();
    sqsq.bgColor=
      ((BgColorComponent)ent.getComponent("BgColorComponent")).getColor();
    sqsq.x=0.0;
    sqsq.y=0.0;
    sqsq.angle=0.0;
    sqsq.scale=1.0;
    sqsq.drawOrder=50;
    list.add(sqsq);
    return list;
  };
  private final static Locomotor.LocomotorFnType locomotorFn=ent->
  {
    Control ctl=(Control)ent.getComponent("Control");
    Vec2dr a=new Vec2dr();
    boolean up=!ctl.get("up").equals("");
    boolean right=!ctl.get("right").equals("");
    boolean down=!ctl.get("down").equals("");
    boolean left=!ctl.get("left").equals("");
    if(up&&!down)
      a=a.addDR(new Vec2dr(8,0,0));
    if(!up&&down)
      a=a.addDR(new Vec2dr(-2,0,0));
    if(right&&!left)
      a=a.addDR(new Vec2dr(0,0,-12));
    if(!right&&left)
      a=a.addDR(new Vec2dr(0,0,12));

    Physics ph=(Physics)ent.getComponent("Physics");

    a=a.rotateD(ph.getPlace().angle);

    String cc=ctl.get("color");
    BgColorComponent sc=(BgColorComponent)ent.getComponent("BgColorComponent");
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
    return a;
  };
  private final static Physics.PhysicsFnType physicsFn=ent->
  {
    Physics physics=(Physics)ent.getComponent("Physics");
    Vec2dr v=physics.getVelocity();
    v=v.mulDR(-3.);
    return v;
  };
  private final static Camera.EyeFnType eyeFn=ent->
  {
    Physics physics=(Physics)ent.getComponent("Physics");
    Entity a=physics.getAnchor();
    if(a!=null)
    {
      Camera ac=(Camera)a.getComponent("Camera");
      if(ac!=null)
        return ac.getEye();
    }
    Vec2dr place=physics.getPlace();
    return new Vec2drs(place.x,place.y,place.angle-Math.PI/2.,1./1.2);
  };
  private final static Appearance.RenderDistanceFnType renderDistanceFn=ent->
  {
    return .25*1.41;
  };
  private final static Collider.ShapeFnType shapeFn=ent->
  {
    try
    {
      return new ElementaryShape(shape);
    }
    catch(GameException exc)
    {
      System.err.println("To tylko na razie!!!\nA oprócz tego, to jest błąd "+
      "w ścianie!");
    }
    return null;
  };
  private final static Health.OnDeathFnType onDeathFn=ent->
  {
    ((EventSender)ent.getComponent("EventSender")).send("gameOver");
  };
  private final static Health.OverTimeFnType healthOverTimeFn=ent->
  {
    ((Health)ent.getComponent("Health")).change(900.*ent.getDelta());
  };
}
