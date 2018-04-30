public class GameLevelModel
{
    final int NUM_ROWS = 16;
    final int NUM_COLS = 13;
    
    private GameSquare grid[NUM_ROWS][NUM_COLS];
    private int playerRow;
    private int playerColumn;
    private Vector<HoleCollection> holes;
    private Vector<TurnstileCollection> turnstiles;
    private Vector<BlockCollection> blocks;

    public GameLevelModel(GameSquare g[NUM_ROWS][NUM_COLS], int pR, int pC, Vector<HoleCollection> h, Vector<TurnstileCollection> t, Vector<BlockCollection> b)
    {
        grid = g;
        playerRow = pR;
        playerCol = pC;
        holes = h;
        turnstiles = t;
        blocks = b;
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
        System.out.println("[GameLevelModel]: playerRow(" + playerRow + "), playerCol(" + playerCol + ")");
        System.out.println(holes);
        System.out.println(turnstiles);
        System.out.println(blocks);
        System.out.println();
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                System.out.print(grid[i][j].getStrValue() + " ")
            }
        }
        System.out.println();
    }

    public void display(Graphics g)
    {
        // TODO
    }
}