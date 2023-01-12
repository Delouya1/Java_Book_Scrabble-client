package test.file_search;

import java.util.LinkedHashMap;

public class LRU implements CacheReplacementPolicy {
    private final LinkedHashMap<String, Integer> cache;

    public LRU() {
        this.cache = new LinkedHashMap<>();
    }

    public void add(String word) {
        if (cache.containsKey(word)) {
            cache.remove(word);
            cache.put(word, 1);
        } else {
            cache.put(word, 1);
        }
    }

    public String remove() {
        String remove = cache.keySet().iterator().next();
        cache.remove(remove);
        return remove;
    }


}

