package test.file_search;

import java.io.BufferedReader;
import java.io.FileReader;


public class IOSearcher {
    public static boolean search(String word, String... fileNames) throws Exception {
        for (String fileName : fileNames) {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                for (String w : words) {
                    if (w.equals(word)) {
                        fr.close();
                        br.close();
                        return true;
                    }
                }
            }
            fr.close();
            br.close();
        }
        return false;
    }
}







