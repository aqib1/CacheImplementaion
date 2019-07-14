package com.met.cache;

public class MetLRU<K, V> extends AbstractCache<K, V>{

	public MetLRU(int sizeOfCache) {
		super(sizeOfCache);
	}

	@Override
	public V getValue(K key) {
		boolean remove = this.evictionList.remove(key);
		if(remove) {
			this.evictionList.addFirst(key);
			return data.get(key);
		}
		return null;
	}

	@Override
	public AbstractCache<K, V> putValue(K key, V value) {
		if(isCacheFull()) {
			dataEviction();
		}
		this.evictionList.addFirst(key);
		this.data.put(key, value);
		return this;
	}

}
