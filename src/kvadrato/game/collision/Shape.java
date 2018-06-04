package kvadrato.game.collision;
import kvadrato.game.Vector2d;
import java.util.List;
import java.util.Collections;

public final class Shape
{
  final private List<Vector2d> vertices;
  public Shape(List<Vector2d> verts)
  {
    vertices=Collections.unmodifiableList(verts);
  }
  public List<Vector2d> getVertices()
  {
    return vertices;
  }
}
