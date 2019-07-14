import com.met.cache.CacheEvictionPolicies;
import com.met.cache.MetCacheFactory;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MetCacheFactory.getInstance(3)
		.initCache(CacheEvictionPolicies.LRU)
		.getCache().putValue(1, 12)
		.putValue(2, 13)
		.putValue(3, 14)
		.putValue(4, 15);
		// => data was [12,13,14] while adding 15 -> cache become full and least recently used 12 was removed
		// => new data is now [13,14,15]
		System.out.println(MetCacheFactory.getInstance().getCache().getData());
		//Now we access 13 so it become most frequently and list is now [14,15,13] 
		System.out.println(MetCacheFactory.getInstance().getCache().getValue(2));
		//=> adding new value will cause threshold again and removed least frequent 14
		MetCacheFactory.getInstance().getCache().putValue(5, 11);
		System.out.println(MetCacheFactory.getInstance().getCache().getData());
	}
}
