package test.file_search;

public interface CacheReplacementPolicy {
    void add(String word);

    String remove();
}
