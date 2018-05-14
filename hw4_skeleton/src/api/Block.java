package api;

import java.awt.Color;

/**
 * Interface specifying an icon or block in a Tetris-like
 * game.
 */
public class Block
{
  /**
   * The color associated with this block.
   */
  private final Color color;
  
  /**
   * The magic status of this block.
   */
  private final boolean magic;
  
  /**
   * Constructs a Block with the given color that is not magic.
   * @param c 
   *   color to be associated with this block
   */
  public Block(Color c)
  {
    this.color = c;
    magic = false;
  }

  /**
   * Constructs a Block with the given color and magic state.
   * @param c 
   *   color to be associated with this block
   * @param magic
   *   true if this block should be in the magic state, false otherwise
   */
  public Block(Color c, boolean magic)
  {
    this.color = c;
    this.magic = magic;
  }
  
//  /**
//   * Copy constructor.
//   * @param existingBlock
//   */
//  public Block(Block existingBlock)
//  {
//    this.color = existingBlock.color;
//    this.magic = existingBlock.magic;
//  }
  
  /**
   * Returns the color associated with this block.
   * @return
   *   color for this block
   */
  public Color getColorHint()
  {
    return color;
  }

  /**
   * Determines whether this block has the same color and magic
   * state as the given block.
   * @param block
   *   given block
   * @return
   *   true if this block matches the given block
   */
  public boolean matches(Block block)
  {
    return (block != null && block.getColorHint() == this.color);
  }
  
  /**
   * Determines the magic state of this block.
   * @return
   *   true if this block is magic
   */
  public boolean isMagic()
  {
    return magic;
  }
  
  @Override
  public String toString()
  {
    String m = "";
    if (magic)
    {
      m = "*";
    }
    return getColorString() + m;
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != this.getClass())
    {
      return false;
    }
    Block other = (Block) obj;
    return color == other.color && magic == other.magic;
  }

  /**
   * Returns a string representation of this block's color
   * @return
   *   string representation of this block's color
   */
  private String getColorString()
  {
    if (color == Color.ORANGE) return "ORANGE";
    if (color == Color.BLUE) return "BLUE";
    if (color == Color.CYAN) return "CYAN";
    if (color == Color.YELLOW) return "YELLOW";
    if (color == Color.MAGENTA) return "MAGENTA";
    if (color == Color.GREEN) return "GREEN";
    if (color == Color.RED) return "RED";
    return "unknown";
  }
}
