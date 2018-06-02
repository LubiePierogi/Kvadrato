package kvadrato.game.collision;
import kvadrato.game.Vector2d;
public final class Collision
{
  public final Vector2d center;
  public final double volume;
  private Collision(Vector2d center,double volume)
  {
    this.center=center;
    this.volume=volume;
    // Na razie dajemy nic.
  }
  public static Collision compute(Shape first,Shape second)
  {
    return null;
  }
}
