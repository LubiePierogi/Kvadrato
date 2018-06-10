package kvadrato.game.collision;

import kvadrato.utils.vec2.Vec2d;

public final class Collision
{
  public final Vec2d center;
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
