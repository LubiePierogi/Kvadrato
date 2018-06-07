package kvadrato.game;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import kvadrato.game.Transform;
import kvadrato.game.other.BackgroundColor;

import kvadrato.game.appearance.AppearanceElement;

public class ViewOfWorld
{
  BackgroundColor backgroundColor;
  List<AppearanceElement> things;
  //Eye eye;

  public BackgroundColor getBackground()
  {
    return backgroundColor;
  }
  public List<AppearanceElement> getThings()
  {
    return Collections.unmodifiableList(things);
  }
  //public Eye getEye()
  //{
  //  return eye;
  //}
  ViewOfWorld()
  {
    backgroundColor=BackgroundColor.WHITE; // Domyślny kolor to biały.
    things=new ArrayList();
  }
  // Settery nie są tu potrzebne, bo i tak ustawia tamte pola funkcja z klasy
  // World, a one wszystkie są w jednym pakiecie.
}
