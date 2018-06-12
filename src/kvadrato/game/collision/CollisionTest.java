package kvadrato.game.collision;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import kvadrato.utils.GameException;
import kvadrato.utils.vec2.Vec2d;
import kvadrato.utils.vec2.Vec2dr;
import kvadrato.utils.vec2.Vec2drs;

public class CollisionTest
{
  @Test
  public void doNothing()
  {
    Assert.assertEquals(1,1);
  }
  @Test
  public void bakeShape1()
    throws GameException
  {
    List<Vec2d> list=new ArrayList<Vec2d>();
    list.add(new Vec2d(0,0));
    list.add(new Vec2d(1,0));
    list.add(new Vec2d(1,1));
    ElementaryShape e=new ElementaryShape(list);
    BakedShape ee=new BakedShape(e,new Vec2drs(3,3,Math.PI*.5,2));

    Assert.assertEquals(3.,ee.vertices.get(0).x,.0001);
    Assert.assertEquals(3.,ee.vertices.get(0).y,.0001);
    Assert.assertEquals(3.,ee.vertices.get(1).x,.0001);
    Assert.assertEquals(5.,ee.vertices.get(1).y,.0001);
    Assert.assertEquals(1.,ee.vertices.get(2).x,.0001);
    Assert.assertEquals(5.,ee.vertices.get(2).y,.0001);
  }
}
