import java.util.Vector;

public class BlockCollection extends GameSquareCollection
{
    public void BlockCollection(Vector<Block> c)
    {
        super(c);
    }

    public void shiftBlocks(Direction direction)
    {
        // TODO
    }

    public Boolean ableToShift(Direction direction)
    {
        // TODO
    }

    public String toString()
    {
        System.out.println("[Block Collection]");
        super.toString();
    }
}
