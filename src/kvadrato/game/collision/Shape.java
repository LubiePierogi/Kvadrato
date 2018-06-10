package kvadrato.game.collision;

import java.util.List;
import java.util.Collections;

import kvadrato.utils.vec2.Vec2d;

public final class Shape
{
  final private List<Vec2d> vertices;
  public Shape(List<Vec2d> verts)
  {
    vertices=Collections.unmodifiableList(verts);
  }
  public List<Vec2d> getVertices()
  {
    return vertices;
  }
}
