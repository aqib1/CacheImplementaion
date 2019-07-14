package com.met.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public abstract class AbstractCache<K, V> {

	int sizeOfCache = 0;
	HashMap<K, V> data = null;
	LinkedList<K> evictionList = null;

	public abstract V getValue(K key);
	public abstract AbstractCache<K, V> putValue(K key, V value);
	
	public AbstractCache(int sizeOfCache){
		this.sizeOfCache = sizeOfCache;
		data = new HashMap<>(sizeOfCache);
		evictionList = new LinkedList<>();
	}
	
	void dataEviction() throws NoSuchElementException{
		K key = evictionList.removeLast();
		data.remove(key);	
	}
	
	boolean isCacheFull() {
		return data.size() == sizeOfCache;
	}
	
	void invalidate(K key) {
		this.data.remove(key);
		this.evictionList.remove(key);
	}
	
	void cacheClean() {
		evictionList.clear();
		data.clear();
	}
	
	public HashMap<K, V> getData() {
		return data;
	}
}
