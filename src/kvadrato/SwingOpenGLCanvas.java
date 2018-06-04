/*package kvadrato;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;

public class SwingOpenGLCanvas
{
  GLProfile glProfile;
  GLCapabilities glCapabilities;
  GLCanvas glCanvas;

  SwingOpenGLCanvas()
  {
    glCapabilities=new GLCapabilities(glProfile);
    glProfile=GLProfile.getDefault();
    try
    {
      glCanvas.addGLEventListener(new GLEventListener()
      {
        @Override
        public void reshape(GLAutoDrawable glAutoDrawable,int x,int y,int w,int h)
        {
          OpenGLRenderer.set(glAutoDrawable.getGL().getGL2(),w,h);
        }
        @Override
        public void init(GLAutoDrawable glAutoDrawable)
        {

        }
        @Override
        public void dispose(GLAutoDrawable glAutoDrawable)
        {

        }
        @Override
        public void display(GLAutoDrawable glAutoDrawable)
        {
          OpenGLRenderer.render
          (
            glAutoDrawable.getGL().getGL2(),
            glAutoDrawable.getSurfaceWidth(),
            glAutoDrawable.getSurfaceHeight()
          );
        }
      });
    }
    catch(Throwable exc)
    {
      System.out.println("Kurwaaaaaaaaa");
    }
  }
  GLCanvas getGlCanvas()
  {
    return glCanvas;
  }
}
*/
