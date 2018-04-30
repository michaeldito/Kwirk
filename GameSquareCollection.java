import java.util.Vector;

public abstract class GameSquareCollection
{
    protected Vector<GameSquare> collection;

    public GameSquareCollection(Vector<GameSquare> c)
    {
        collection = c;
    }

    protected String toString()
    {
        System.out.println("Size: " + collection.size());
        for (int i = 0; i < collection.size(); i++) {
            System.out.print("\t[Collection " + i + "]: ");
            for (int j = 0; j < collection[i].size(); j++) {
                System.out.println("\t\t" + collection[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}