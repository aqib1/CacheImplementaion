package com.met.cache;

public class MetMRU<K,V> extends AbstractCache<K, V>{

	public MetMRU(int sizeOfCache) {
		super(sizeOfCache);
	}

	@Override
	public V getValue(K key) {
		boolean remove = this.evictionList.remove(key);
		if(remove) {
			this.evictionList.addLast(key);
			return this.data.get(key);
		}
		
		return null;
	}

	@Override
	public AbstractCache<K, V> putValue(K key, V value) {
		if(isCacheFull()) {
			this.dataEviction();
		}
		this.evictionList.addLast(key);
		this.data.put(key, value);
		return this;
	}

}
