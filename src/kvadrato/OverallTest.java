package kvadrato;

import org.junit.Assert;
import org.junit.Test;

import kvadrato.utils.GameException;

public class OverallTest
{
  @Test
  public void doNothing()
  {
    Assert.assertEquals(1,1);
  }
  @Test
  public void creatingModelThatDoesntCrash()
  {
    try
    {
      Model model=new Model();
    }
    catch(GameException exc)
    {
      Assert.fail("GameException:\n"+exc.getMessage());
    }
  }
}
