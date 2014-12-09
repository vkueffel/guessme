package net.kueffel.guessme.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleLRUCache{
	
	private Map<Object, Object> cache;
	

	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public SimpleLRUCache(final int maxEntries) {
		cache = new LinkedHashMap<Object, Object>(maxEntries+1, .75F, true) {
		    // This method is called just after a new entry has been added
			public boolean removeEldestEntry(Map.Entry eldest) {
		        return size() > maxEntries;
		    }
		};
		cache = (Map)Collections.synchronizedMap(cache);
	}


	public void put(Serializable key, Object value) {
		cache.put(key, value);
		
	}

	public Object get(Serializable key) {
		return cache.get(key);
	}

	public void remove(Serializable key) {
		cache.remove(key);
		
	}

	public void flush() {
		cache.clear();
	}
}
