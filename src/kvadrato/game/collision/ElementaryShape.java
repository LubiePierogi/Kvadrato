package kvadrato.game.collision;

import java.util.Objects;
import java.util.List;
import java.util.Collections;

import kvadrato.utils.vec2.Vec2d;

public final class ElementaryShape
{
  final private List<Vec2d> vertices;
  public ElementaryShape(List<Vec2d> verts)
  {
    Objects.requireNonNull(verts);
    vertices=Collections.unmodifiableList(verts);
  }
  public List<Vec2d> getVertices()
  {
    return vertices;
  }
}
