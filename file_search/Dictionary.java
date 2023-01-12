package test.file_search;

import java.io.BufferedReader;
import java.io.FileReader;


public class Dictionary {
    private final CacheManager exsits;
    private final CacheManager notExsits;
    private final BloomFilter bf;
    private final String[] files;

    public Dictionary(String... fileNames) {

        exsits = new CacheManager(400, new LRU());
        notExsits = new CacheManager(100, new LFU());
        bf = new BloomFilter(256, "MD5", "SHA1");
        files = fileNames;


        for (String fileName : fileNames) {
            try {
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] words = line.split(" ");
                    for (String word : words) {
                        bf.add(word);
                    }
                }
                fr.close();
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public boolean query(String word) {
        if (exsits.query(word)) {
            return true;
        }
        if (notExsits.query(word)) {
            return false;
        }

        if (bf.contains(word)) {
            exsits.add(word);
            return true;
        } else {
            notExsits.add(word);
            return false;
        }

    }


    public boolean challenge(String word) {
        boolean isFound = false;
        try {
            isFound = IOSearcher.search(word, files);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return isFound;
    }

}
