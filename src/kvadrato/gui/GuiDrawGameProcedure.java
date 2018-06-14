package kvadrato.gui;

import javafx.scene.canvas.Canvas;

import kvadrato.utils.GameException;

public interface GuiDrawGameProcedure
{
  void call(Canvas canvas)throws GameException;
}
