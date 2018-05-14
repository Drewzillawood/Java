package api;

/**
 * Reduction operation combines an object of type T with a 
 * string to produce a new or updated result of type T.
 * @param <T>
 */
public interface Combiner<T>
{
  /**
   * Combines an object of type T with a string to produce
   * a new or updated result of type T.
   * @param obj
   *   the given object
   * @param s
   *   a string
   * @return
   *   result obtained by combining obj with s
   */   
  T combine(T obj, String s);
}