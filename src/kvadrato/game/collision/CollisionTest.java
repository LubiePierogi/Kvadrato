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

    Assert.assertEquals("(0,0) -> x",3.,ee.vertices.get(0).x,.00001);
    Assert.assertEquals("(0,0) -> y",3.,ee.vertices.get(0).y,.00001);
    Assert.assertEquals("(1,0) -> x",3.,ee.vertices.get(1).x,.00001);
    Assert.assertEquals("(1,0) -> y",5.,ee.vertices.get(1).y,.00001);
    Assert.assertEquals("(1,1) -> x",1.,ee.vertices.get(2).x,.00001);
    Assert.assertEquals("(1,1) -> y",5.,ee.vertices.get(2).y,.00001);
  }
  @Test
  public void castShape1()
    throws GameException
  {
    BakedShape shape=new BakedShape
    (
      new ElementaryShape
      (
        new Vec2d[]
        {
          new Vec2d(1,0),
          new Vec2d(0,1),
          new Vec2d(-.5,0),
          new Vec2d(0,-1),
        }
      ),
      new Vec2drs(0.,0.,0.,1.)
    );
    Projection p=CollisionComputer.castShape(shape,new Vec2d(1,0).spin90());
    Assert.assertEquals("Mniejsze",-1.,p.smaller,.00001);
    Assert.assertEquals("Większe" , 1.,p.greater,.00001);
  }
  @Test
  public void castShape2()
    throws GameException
  {
    BakedShape shape=new BakedShape
    (
      new ElementaryShape
      (
        new Vec2d[]
        {
          new Vec2d(1,0),
          new Vec2d(0,1),
          new Vec2d(-.5,0),
          new Vec2d(0,-1),
        }
      ),
      new Vec2drs(-1.,0.,Math.PI*.25,3.)
    );
    Projection p=CollisionComputer.castShape(shape,new Vec2d(0,1).spin90());
    Assert.assertEquals("Mniejsze",-Math.sqrt(2.)/2.*3.+1.,p.smaller,.00001);
    Assert.assertEquals("Większe" ,Math.sqrt(2.)/2.*3.+1.,p.greater,.00001);
  }
  @Test
  public void compute1()
    throws GameException
  {
    BakedShape shape1=new BakedShape
    (
      new ElementaryShape
      (
        new Vec2d[]
        {
          new Vec2d(1,0),
          new Vec2d(0,1),
          new Vec2d(-.5,0),
          new Vec2d(0,-1),
        }
      ),
      new Vec2drs(-1.,0.,Math.PI*.25,3.)
    );
    BakedShape shape2=new BakedShape
    (
      new ElementaryShape
      (
        new Vec2d[]
        {
          new Vec2d(30,0),
          new Vec2d(-30,0),
        }
      ),
      new Vec2drs(5,5,Math.PI*-.25,1.)
    );
    CollisionOccurrence co;
    co=CollisionComputer.computeFromBaked(shape1,shape2);
    if(co!=null)
    {
      Assert.assertEquals("Miało być zero albo null",0.0,co.translation.dist(),
      0.0001);
    }
  }
}
