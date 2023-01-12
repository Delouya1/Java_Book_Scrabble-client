package test.file_search;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class LFU implements CacheReplacementPolicy {
    private final LinkedHashMap<String, Integer> cache;
    private int min;

    public LFU() {
        this.cache = new LinkedHashMap<>();
        this.min = 0;
    }

    public void add(String word) {
        if (cache.containsKey(word)) {
            cache.put(word, cache.get(word) + 1);
        } else {
            cache.put(word, 1);
        }
        min = 1;
    }

    public String remove() {
        Set<Entry<String, Integer>> entrySet = cache.entrySet();
        String remove = null;
        for (Entry<String, Integer> entry : entrySet) {
            if (entry.getValue() == min) {
                remove = entry.getKey();
                break;
            }
        }
        cache.remove(remove);
        min++;
        return remove;
    }


}
