package test.file_search;
import java.util.LinkedHashMap;

public class CacheManager {
    private final int size;
    private final CacheReplacementPolicy crp;
    private final LinkedHashMap<String, Integer> cache;

    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.size = size;
        this.crp = crp;
        this.cache = new LinkedHashMap<>();
    }

    public boolean query(String word) {
        if (cache.containsKey(word)) {
            crp.add(word);
            return true;
        }
        return false;
    }

    public void add(String word) {
        crp.add(word);
        if (cache.containsKey(word)) {
            cache.put(word, cache.get(word) + 1);
        } else {
            cache.put(word, 1);
        }
        if (cache.size() > size) {
            String remove = crp.remove();
            cache.remove(remove);
        }
    }
}
