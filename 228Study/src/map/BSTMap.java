//package map;
//
//import java.util.Iterator;
//
//import binarySearchTree.BSTSet;
//
////Simple implementation of a Map based on
////a binary search tree.
//
//
//public class BSTMap<K extends Comparable<? super K>, V> 
//implements SimpleMap<K, V>
//{
//	// The key-value pairs in this Map.
//	private BSTSet<MapEntry> entrySet = new BSTSet<MapEntry>();
//
//	// Each entry has a key and a value.
//	// Entries are compared by key only.
//	private class MapEntry implements Comparable<MapEntry>
//	{
//		public K key;
//		public V value;
//
//		@Override
//		public int compareTo(MapEntry rhs)
//		{
//			return key.compareTo(rhs.key);
//		}
//		
//		public MapEntry(K key, V value)
//		{
//			this.key = key;
//			this.value = value;
//		}
//	}
//	
//	@Override
//	public int size()
//	{
//		return entrySet.size();
//	}
//	
//	@Override
//	public V get(K key)
//	{
//		// Find node for entry with matching
//		// key (value is ignored)
//		BSTSet<MapEntry>.Node n = 
//				entrySet.findEntry(new MapEntry(key, null));
//		if (n != null)
//		{
//			return n.data.value;
//		}
//		return null;
//	}
//	
//	@Override
//	public boolean containsKey(K key)
//	{
//		return entrySet.findEntry(new MapEntry(key, null)) != null;
//	}
//	
//	@Override
//	public Iterator<K> keyIterator()
//	{
//		return new SimpleMapIterator();
//	}
//	
//	@Override
//	public V put(K key, V value)
//	{
//		// Find node for entry with matching
//		// key (value is ignored)
//		BSTSet<MapEntry>.Node n = entrySet.findEntry(new MapEntry(key, null));
//	
//		V ret = null;
//		if (n != null)
//		{
//			// key is already present, overwrite
//			// value
//			ret = n.data.value;
//			n.data.value = value;
//		}
//		else
//		{
//			entrySet.add(new MapEntry(key, value));
//		}
//		
//		return ret;
//	}
//			
//	@Override
//	public V remove(K key)
//	{
//		// Find node for entry with matching
//		// key (value is ignored)
//		BSTSet<MapEntry>.Node n = entrySet.findEntry(new MapEntry(key, null));
//		V ret = null;
//			
//		if (n != null)
//		{
//			ret = n.data.value;
//			entrySet.unlinkNode(n);
//		}
//		return ret;
//	}
//			
//	// Iterator delegates to binary search
//	// tree iterator.
//	private class SimpleMapIterator implements Iterator<K>
//	{
//		private Iterator<MapEntry> iter = entrySet.iterator();
//			
//		@Override
//		public boolean hasNext()
//		{
//			return iter.hasNext();
//		}
//			
//		@Override
//		public K next()
//		{
//			return iter.next().key;
//		}
//			
//		@Override
//		public void remove()
//		{
//			iter.remove();
//		}
//	}
//}