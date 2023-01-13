package test.server;


import test.file_search.Dictionary;

import java.util.LinkedHashMap;

public class DictionaryManager {
    private static  DictionaryManager dictionaryManager;
    LinkedHashMap<String, Dictionary> dictionaryMap;

    private DictionaryManager() {
        dictionaryMap = new LinkedHashMap<>();
    }

    public boolean query( String...args) {
        boolean found = false;
        String s = initiateString(args);
        for (Dictionary d : dictionaryMap.values()) {
            if (d.query(s)) {
                found = true;
            }
        }
            return found;
        }



    public boolean challenge(String...args) {
        boolean found = false;
        String s = initiateString(args);
        for (Dictionary d : dictionaryMap.values()) {
            if (d.challenge(s)) {
                found = true;
            }
        }
        return found;
    }

    private String initiateString(String...args) {
        for (int i = 0; i < args.length - 1; i++) {
            if (!dictionaryMap.containsKey(args[i])) {
                Dictionary newDict = new Dictionary(args[i]);
                dictionaryMap.put(args[i], newDict);
            }
        }
        return args[args.length - 1];
    }

    public int getSize(){
        return dictionaryMap.size();
    }
    public static DictionaryManager get() {
           if (dictionaryManager == null) {
                dictionaryManager = new DictionaryManager();
            }
            return dictionaryManager;
    }


}
