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
        //last string is the search query
        String word = args[args.length - 1];
        //each arg in args is a dictionary name
        //for each dictionary name, check if it exists in the dictionaryMap
        //if it does, query the dictionary using the dictionary query method
        //if it doesn't, create a new dictionary and add it to the dictionaryMap
        //then query the dictionary using the dictionary query method
        for (int i = 0; i < args.length - 1; i++) {
            if (dictionaryMap.containsKey(args[i])) {
                if (dictionaryMap.get(args[i]).query(word)) {
                    return true;
                }
            } else {
                Dictionary dictionary = new Dictionary(args[i]);
                dictionaryMap.put(args[i], dictionary);
                if (dictionary.query(word)) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean challenge(String...args) {
        //last string is the search query
        String word = args[args.length - 1];
        //each arg in args is a dictionary name
        //for each dictionary name, check if it exists in the dictionaryMap
        //if it does, use the dictionary challenge method with the word
        //if it doesn't, create a new dictionary and add it to the dictionaryMap
        for (int i = 0; i < args.length - 1; i++) {
            String dictName = args[i];
            if (dictionaryMap.containsKey(dictName)) {
                if (dictionaryMap.get(dictName).challenge(word)) {
                    return true;
                }
            } else {
                Dictionary newDict = new Dictionary(dictName);
                dictionaryMap.put(dictName, newDict);
                if (newDict.challenge(word)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getSize(){
        return dictionaryMap.size();
    }
    public static DictionaryManager get() {
        if (dictionaryManager == null) {
            synchronized (DictionaryManager.class) {
                // Ensure that the instance hasn't yet been
                // initialized by another thread
                if (dictionaryManager == null) {
                    dictionaryManager = new DictionaryManager();
                }
            }
        }
        return dictionaryManager;
    }


}
