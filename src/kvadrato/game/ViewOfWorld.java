package kvadrato.game;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import kvadrato.game.Transform;

import kvadrato.game.appearance.AppearanceElement;

public class ViewOfWorld
{
  Color backgroundColor;
  List<AppearanceElement> things;
  double scale;
  Transform place;

  public Color getBackground()
  {
    return backgroundColor;
  }
  public List<AppearanceElement> getThings()
  {
    return Collections.unmodifiableList(things);
  }
  public double getScale()
  {
    return scale;
  }
  public Transform getPlace()
  {
    return place;
  }
  ViewOfWorld()
  {
    backgroundColor=new Color(255,255,255); // Domyślny kolor to biały.
    things=new ArrayList();
  }
  // Settery nie są tu potrzebne, bo i tak ustawia tamte pola funkcja z klasy
  // World, a one wszystkie są w jednym pakiecie.
}
