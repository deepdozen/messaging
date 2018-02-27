package com.app.processing.sale;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Alex
 * 
 * Class implements multiset or bag data structure.
 * It stores count of equal sales.
 * Sales are equal if they have equal product type.
 *
 */

public class SaleBag {
	private Map<Sale, Integer> map;

	public SaleBag() {
		map = new HashMap<>();
	}

	public boolean add(Sale key) {
		Integer v = map.get(key);
		if (v == null)
			map.put(key, 1);
		else
			map.put(key, v + 1);
		return true;
	}

	public boolean add(Sale key, int count) {
		Integer v = map.get(key);
		if (v == null)
			map.put(key, count);
		else
			map.put(key, v + count);
		return true;
	}

	public int getCount(Sale key) {
		Integer v = map.get(key);
		if (v == null)
			return 0;
		else
			return v;
	}

	public Set<Sale> keySet() {
		return map.keySet();
	}
	
	public void remove(Sale key) {
		map.remove(key);
	}
	
	public void clear() {
		map.clear();
	}
}
