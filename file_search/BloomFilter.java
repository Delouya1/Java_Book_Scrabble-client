package test.file_search;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.BitSet;


public class BloomFilter {
    private final BitSet bitSet;
    private final int size;
    private final MessageDigest[] digests;

    public BloomFilter(int size, String... algs) {
        this.size = size;
        this.bitSet = new BitSet(size);
        this.digests = new MessageDigest[algs.length];
        for (int i = 0; i < algs.length; i++) {
            try {
                digests[i] = MessageDigest.getInstance(algs[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void add(String word) {
        for (MessageDigest digest : digests) {
            byte[] bytes = digest.digest(word.getBytes());
            int hash = new BigInteger(bytes).intValue();
            bitSet.set(Math.abs(hash % size));
        }

    }

    public boolean contains(String word) {
        for (MessageDigest digest : digests) {
            byte[] bytes = digest.digest(word.getBytes());
            int hash = new BigInteger(bytes).intValue();
            if (!bitSet.get(Math.abs(hash % size))) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            if (bitSet.get(i)) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
        return sb.toString();
    }


}
