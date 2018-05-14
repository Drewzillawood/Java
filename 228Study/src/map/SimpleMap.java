package map;

import java.util.Iterator;

public interface SimpleMap<K, V> {
	
	public V put(K key, V value);

	public V get(K key);

	public V remove(K key);

	public boolean containsKey(K key);

	public int size();

	public Iterator<K> keyIterator();
	
}