package com.met.cache;

@SuppressWarnings("unchecked")
public class MetCacheFactory<K, V> {
	@SuppressWarnings("rawtypes")
	private static volatile MetCacheFactory metCache = null;
	private int DEFAULT_CACHE_SIZE = 30 * 1024 * 1024;
	private AbstractCache<K, V> cache = null;

	private MetCacheFactory() {

	}

	private MetCacheFactory(int size) {
		DEFAULT_CACHE_SIZE = size;
	}

	public MetCacheFactory<K, V> initCache(CacheEvictionPolicies policy) {
		if (policy == CacheEvictionPolicies.LRU)
			cache = new MetLRU<K, V>(DEFAULT_CACHE_SIZE);
		else if (policy == CacheEvictionPolicies.MRU)
			cache = new MetMRU<K, V>(DEFAULT_CACHE_SIZE);
		else
			throw new IllegalArgumentException("Invalid Eviction policy");
		return metCache;
	}

	public static <K, V> MetCacheFactory<K, V> getInstance() {
		if (metCache == null) {
			synchronized (MetCacheFactory.class) {
				if (metCache == null)
					metCache = new MetCacheFactory<K, V>();
			}
		}
		return metCache;
	}
	
	public AbstractCache<K, V> getCache() {
		return cache;
	}
	
	public static <K, V> MetCacheFactory<K, V> getInstance(int cacheSize) {
		if (metCache == null) {
			synchronized (MetCacheFactory.class) {
				if (metCache == null)
					metCache = new MetCacheFactory<K, V>(cacheSize);
			}
		}
		return metCache;
	}
}
