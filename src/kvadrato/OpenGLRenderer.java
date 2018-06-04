/*package kvadrato;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

public class OpenGLRenderer
{
  static void set(GL2 gl2,int width,int height)
  {
    gl2.glMatrixMode(GL2.GL_PROJECTION);
    gl2.glLoadIdentity();

    GLU glu=new GLU();
    glu.gluOrtho2D(0.f,width,0.f,height);

    gl2.glMatrixMode(GL2.GL_MODELVIEW);
    gl2.glLoadIdentity();

    gl2.glViewport(0,0,width,height);
  }
  static void render(GL2 gl2,int width,int height)
  {
    gl2.glClear(GL.GL_COLOR_BUFFER_BIT);

    gl2.glLoadIdentity();
    gl2.glBegin(GL.GL_TRIANGLES);
    gl2.glColor3f(1,0,0);
    gl2.glVertex2f(0,0);
    gl2.glColor3f(0,1,0);
    gl2.glVertex2f(width,0);
    gl2.glColor3f(0,0,1);
    gl2.glVertex2f(width/2,height);
    gl2.glEnd();
  }
}
*/
