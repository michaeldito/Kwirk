public class Tile
{
  public enum TileType { BLANK, BRICK, STAIRS, HOLE, BLOCK, TURNSTILE };
  public String[] TileNames = { "BLANK", "BRICK", "STAIRS", "HOLE", "BLOCK", "TURNSTILE" };
  public String[] TypeCodes = { "BA", "BR", "SR", "HL", "BL", "T" };
  protected TileType type;

  public Tile() {
    type = TileType.BLANK;
  }

  public Tile(TileType tileType) {
    type = tileType;
  }

  public String GetTypeName() {
    return TileNames[type];
  }

  public String GetTypeCode() {
    return TypeCodes[type];
  }
}
