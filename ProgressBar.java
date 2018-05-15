import java.awt.*;

public class ProgressBar
{
  private GameSquare[] bar;
  private int numBars;
  public ProgressBar()
  {
    bar = new GameSquare[14];
    for (int i = 0; i < 14; i++) 
    {
      bar[i] = GameSquare.create(GameSquare.SquareType.BLOCK, GameLevelModel.NUM_ROWS + 1, 2 + i);
    } 
    numBars = 0;
  }
  public void addBar() { numBars++; }
  public void paintComponent(Graphics2D g2)
  {
    for (int i = 0; i < numBars; i++) {
      bar[i].paintComponent(g2);
    }
    Stroke previousSroke = g2.getStroke();
    g2.setStroke(new BasicStroke(4.0f));
    bar[0].paintLeftBorder(g2);
    bar[13].paintRightBorder(g2);
    for (int i = 0; i < 14; i++) {
      bar[i].paintTopBorder(g2);
      bar[i].paintBottomBorder(g2);
    }
    g2.setStroke(previousSroke);

  }
}