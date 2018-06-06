package kvadrato.game.appearance;

import kvadrato.game.Color;

public class Rectangle implements AppearanceElement
{
  public double centerX;
  public double centerY;
  public double x;
  public double y;
  public double angle;

  public Color color;

  /**
   * Ten odnośnik może być nullem, jeśli jest, to nie ma otoczki wokół
   * prostokąta.
   */
  public Color outline;
  public double outlineWidth;
}
