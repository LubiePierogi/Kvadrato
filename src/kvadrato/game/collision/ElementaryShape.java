package kvadrato.game.collision;

import java.util.Objects;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2d;

public final class ElementaryShape
{
  final private List<Vec2d> vertices;
  public ElementaryShape(List<Vec2d> v)
    throws GameException
  {
    try
    {
      Objects.requireNonNull(v);
    }catch(NullPointerException exc){throw new GameException(exc);}
    if(v.size()<2)
      throw new GameException();
    vertices=new ArrayList<Vec2d>(v);
  }
  public ElementaryShape(Vec2d[] v)
    throws GameException
  {
    try
    {
      Objects.requireNonNull(v);
    }catch(NullPointerException exc){throw new GameException(exc);}
    if(v.length<2)
      throw new GameException();
    vertices=new ArrayList<Vec2d>();
    for(int i=0;i<v.length;++i)
    {
      vertices.add(v[i]);
    }
  }
  public List<Vec2d> getVertices()
  {
    return vertices;
  }
}
