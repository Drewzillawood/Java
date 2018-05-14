package api;

/**
 * Immutable type to encapsulate an int value representing
 * a color or icon in a video game.  The int value is referred
 * to as the "type" of the Jewel.
 */
public class Jewel
{
  /**
   * Type of this jewel.
   */
  private final int type;
  
  /**
   * Constructs a jewel of the given type.
   * @param type
   *   type for this jewel
   */
  public Jewel(int type)
  {
    this.type = type;
  }
  
  /**
   * Returns the type or int value of this jewel.
   * @return
   *   type of this jewel
   */
  public int getType()
  {
    return type;
  }
  
  /**
   * Determines whether this jewel is equal to the given jewel.
   * @return
   *   true if the given object is a Jewel with the same
   *   type as this one
   */
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != this.getClass()) return false;
    Jewel other = (Jewel) obj;
    return type == other.type;
  }

  /**
   * Returns the type or int value of this Jewel as a string.
   * @return
   *   string representation of this object
   */
  public String toString()
  {
    return "" + type;
  }
}

