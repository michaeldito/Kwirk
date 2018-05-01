import java.util.ArrayList;

public class GameLevelModel
{
    public enum Direction { UP, DOWN, LEFT, RIGHT };

    public final int NUM_ROWS = 16;
    public final int NUM_COLS = 18;
    
    private GameSquare[][] grid;
    private int playerRow;
    private int playerColumn;
    private ArrayList<HoleCollection> holes;
    private ArrayList<TurnstileCollection> turnstiles;
    private ArrayList<BlockCollection> blocks;

    public GameLevelModel(GameSquare[][] g, int pR, int pC, 
        ArrayList<HoleCollection> hc, ArrayList<TurnstileCollection> tc, 
        ArrayList<BlockCollection> bc)
    {
        grid = new GameSquare[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < NUM_ROWS; i++)
            for (int j = 0; j < NUM_COLS; j++)
                grid[i][j] = g[i][j];

        playerRow = pR;
        playerColumn = pC;

        holes = new ArrayList<HoleCollection>();
        for (int i = 0; i < hc.size(); i++)
            holes.add(new HoleCollection());
            for (int j = 0; j < hc.get(i).size(); j++)
                holes.get(i).add(hc.get(i).get(j));

        turnstiles = new ArrayList<TurnstileCollection>();
        for (int i = 0; i < tc.size(); i++)
            turnstiles.add(new TurnstileCollection());
            for (int j = 0; j < tc.get(i).size(); j++)
                turnstiles.get(i).add(tc.get(i).get(j));

        blocks = new ArrayList<BlockCollection>();
        for (int i = 0; i < hc.size(); i++)
            holes.add(new HoleCollection());
            for (int j = 0; j < hc.get(i).size(); j++)
                holes.get(i).add(hc.get(i).get(j));
    }

    public GameLevelModel(GameLevelModel model)
    {
        grid = model.grid;
        playerRow = model.playerRow;
        playerCol = model.playerCol;
        holes = model.holes;
        turnstiles = model.turnstiles;
        blocks = model.blocks;
    }

    public void movePlayer(Direction direction)
    {
        // TODO
    }

    public SquareType getNeighborSquareType(Direction direction)
    {
        // TODO
    }

    public TurnstileCollection findTurnstileCollectionContaining(int r, int c)
    {
        // TODO
    }

    public BlockCollection findBlockCollectionContaining(int r, int c)
    {
        // TODO
    }

    public String toString()
    {
        String gridStr = "";
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                gridStr += grid[i][j].getStrValue() + " ";
            }
            gridStr += "\n";
        }
        return "[GameLevelModel]\n" +
        "playerRow = " + playerRow + "; playerCol = " + playerColumn + "\n" +
        holes + turnstiles + blocks + "\nGrid\n" + gridStr;
    }

    public void display(Graphics g)
    {
        // TODO
    }
}