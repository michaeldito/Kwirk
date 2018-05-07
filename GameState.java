import java.util.Stack;

public class GameState
{
    private Stack<GameLevelModel> states;

    public GameState()
    {
        states = new Stack<GameLevelModel>();
    }
    
    public GameLevelModel getPreviousState()
    {
        return states.pop();
    }

    public void addNewState(GameLevelModel model)
    {
        states.push(model);
    }

    public void clear()
    {
        states = new Stack<GameLevelModel>();
    }
}