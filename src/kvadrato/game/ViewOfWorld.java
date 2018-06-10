package kvadrato.game;

import java.util.Objects;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.utils.vec2.Vec2drs;
import kvadrato.game.other.BgColor;
import kvadrato.game.components.Physics;
import kvadrato.game.components.Camera;
import kvadrato.game.components.Appearance;
import kvadrato.game.components.BgColorComponent;
import kvadrato.game.appearance.AppearanceElement;

public class ViewOfWorld
{
  BgColor bgColor;
  List<AppearanceElement> things;

  public BgColor getBgColor()
  {
    return bgColor;
  }
  public List<AppearanceElement> getThings()
  {
    return Collections.unmodifiableList(things);
  }
  /**
   * Daje obiekt, gdzie są rzeczy potrzebne
   * do narysowania świata, i graficzne opakowanie może to wykorzystać
   * do narysowania świata.
   * @param world Świat, którego jest drany widok.
   * @param ent Jednoskta, dla której ma być brany widok.
   * @param distance Największa odległość, z jakiej są brane rzeczy.
   */
  ViewOfWorld(World world,Entity ent,double distance)
    throws GameException
  {
    Objects.requireNonNull(world);
    Vec2drs eye;
    double trueDistance;
    if(ent!=null)
    {
      if(ent.getWorld()!=world)
        throw new GameException();
      eye=getEntEye(ent);
      bgColor=getEntBgColor(ent);
    }
    else
      eye=new Vec2drs();
    trueDistance=distance/eye.scale;
    things=new ArrayList<AppearanceElement>();
    for(Entity x:world.ents)
    {
      addEnt(x,eye,trueDistance);
    }
  }
  private static Vec2drs getEntEye(Entity ent)
  {
    Camera camera=(Camera)ent.getComponent("Camera");
    if(camera==null)
      return new Vec2drs();
    return camera.getEye();
  }
  private static BgColor getEntBgColor(Entity ent)
  {
    BgColorComponent b=(BgColorComponent)ent.getComponent("BgColorComponent");
    if(b==null)
      return BgColor.WHITE;
    return b.getColor();
  }
  private void addEnt(Entity ent,Vec2drs eye,double dist)
  {
    Physics physics=(Physics)ent.getComponent("Physics");
    if(physics==null)
      return;
    Vec2dr place=physics.getPlace();
    Vec2d diff=new Vec2d(eye).subD(new Vec2d(place));
    if(diff.dist()>dist)
      return;
    Appearance appearance=(Appearance)ent.getComponent("Appearance");
    if(appearance==null)
      return;
    List<AppearanceElement>list=appearance.getElements();
    if(list==null)
      return;
    for(AppearanceElement x:list)
    {
      addAe(x,eye,place);
    }
  }
  private void addAe(AppearanceElement ae,Vec2drs eye,Vec2dr place)
  {
    Vec2dr tr=new Vec2dr(ae.x,ae.y,ae.angle);
    Vec2dr ey=new Vec2dr(eye.x,eye.y,0);
    tr=tr.subDR(ey);
    tr=tr.addDR(place);
    ae.x=tr.x;
    ae.y=tr.y;
    ae.angle=tr.angle;
    ae.scale=tr.angle;
    things.add(ae);
  }
}
