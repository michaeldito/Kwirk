import java.util.Vector;

public abstract class GameSquareCollection
{
    protected ArrayList<GameSquare> collection;

    public GameSquareCollection()
    {
        collection = new ArrayList<GameSquare>();
    }

    protected void add(GameSquare g)
    {
        collection.add(g);
    }

    public String toString()
    {
        String collectionStr = "";
        for (int i = 0; i < collection.size(); i++) {
            collectionStr += "\t[Collection " + i + "]:\n";
            for (int j = 0; j < collection[i].size(); j++) {
                collectionStr += "\t\t" + collection[i][j] + "\n";
            }
        }
        return "Size: " + collection.size() + "\n" + collectionStr;
    }
}