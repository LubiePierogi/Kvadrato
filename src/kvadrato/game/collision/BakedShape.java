package kvadrato.game.collision;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.utils.vec2.Vec2drs;

public final class BakedShape
{
  // Bounding box
  double up;
  double right;
  double down;
  double left;

  List<Vec2d> vertices;

  public BakedShape(ElementaryShape sh,Vec2drs v)
  {
    up   =-1./0.;
    right=-1./0.;
    down = 1./0.;
    left = 1./0.;
    vertices=new ArrayList<Vec2d>();
    for(Vec2d vertex:sh.getVertices())
    {
      Vec2d nv=vertex.rotateD(v.angle).mulD(v.scale).addD(new Vec2d(v));
      vertices.add(nv);
      if(nv.y>up   )up   =nv.y;
      if(nv.x>right)right=nv.x;
      if(nv.y<down )down =nv.y;
      if(nv.x<left )left =nv.x;
    }
  }
}
