package kvadrato.game.collision;

import java.util.Objects;
import java.util.List;
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
    if(v.size()<3)
      throw new GameException();
    vertices=Collections.unmodifiableList(v);
  }
  public List<Vec2d> getVertices()
  {
    return vertices;
  }
}
