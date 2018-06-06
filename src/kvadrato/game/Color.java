package kvadrato.game;

public class Color
{
  public final short red;
  public final short green;
  public final short blue;
  Color(short r,short g,short b)
  {
    red=r;
    green=g;
    blue=b;
  }
  Color(int r,int g, int b)
  {
    red=(short)r;
    green=(short)g;
    blue=(short)b;
  }
  Color()
  {
    red=0;green=0;blue=0;
  }
}
