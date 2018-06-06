package kvadrato.game.components;

import kvadrato.game.Component;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import kvadrato.game.appearance.AppearanceElement;

public class Appearance extends Component
{

  List<AppearanceElement> elements;


  public Appearance()
  {
    elements=new ArrayList<AppearanceElement>();
  }

  public void clear()
  {
    elements.clear();
  }

  public void addElement(AppearanceElement e)
  {
    elements.add(e);
  }

  public List<AppearanceElement> getElements()
  {
    return Collections.unmodifiableList(elements);
  }

  public void fix(){}
  public void doThings(){}
  public void update(){}
}
