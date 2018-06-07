package kvadrato.game.prefabs;

import kvadrato.game.Prefab;
import kvadrato.game.Entity;
import kvadrato.game.components.Appearance;
import kvadrato.game.components.Physics;
import kvadrato.game.components.SquareComponent;
import kvadrato.game.appearance.SquareSquare;
import kvadrato.game.other.BackgroundColor;
import kvadrato.game.appearance.AppearanceElement;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * Klasa kwadratu, którym steruje gracz.
 */
public class Square extends Prefab
{
  private final static Function<Entity,List<AppearanceElement>>
    appearanceFn
    =ent->
  {
    ArrayList<AppearanceElement> list=new ArrayList<AppearanceElement>();
    SquareSquare sqsq=new SquareSquare();
    sqsq.colorOfSquare=
      ((SquareComponent)ent.getComponent("SquareComponent")).getColor();
    list.add(sqsq);
    return list;
  };
  public void makeEntity(Entity ent)
    throws ClassNotFoundException,InstantiationException,IllegalAccessException
  {
    ent.addComponent("Physics");
    ent.addComponent("SquareComponent");
    ent.addComponent("Camera");
    ent.addComponent("Collider");
    ent.addComponent("Appearance");


    // Appearance
    {
      Appearance q=(Appearance)ent.getComponent("Appearance");
      q.setFn(appearanceFn);
//      SquareSquare sqsq=new SquareSquare();
//      sqsq.colorOfSquare=BackgroundColor.WHITE;
//      q.addElement(new SquareSquare());

    }

    // Physics
    {
      Physics q=(Physics)ent.getComponent("Physics");
      q.setType(Physics.Type.DYNAMIC);
      q.setMass(1.0);
    }
    // Camera
    {
      // W kamerze nic nie zmieniamy.
    }
    // Control
    {
      // Tu na razie też nic.
    }
    // SquareComponent
    {

    }
  }
}
