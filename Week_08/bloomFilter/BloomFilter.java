package Week_08.bloomFilter;

import org.junit.Assert;
import org.junit.Test;

import java.util.BitSet;

public class BloomFilter<T extends String> {
    private final int SIZE = 64;
    private int[] offset = new int[]{1, 2, 3};
    private BitSet bitSet = new BitSet();
    public BloomFilter () {

    }

    public boolean add (T key) {
        for (int i : offset)
            if (!bitSet.get(hash(key, i)))
                bitSet.set(hash(key, i) % SIZE);
        return true;
    }

    public boolean contain (T key) {
        for (int i : offset) {
            if (!bitSet.get(hash(key, i) % SIZE)) return false;
        }
        return true;
    }

    private int hash (T key, int offset) {
        int h = 0;
        for (char c : key.toCharArray())
            h = (30 + offset) * h + c;
        return h;
    }

    @Test
    public void test_official() {
        //Given
        BloomFilter bloomFilter = new BloomFilter();

        //When
        bloomFilter.add("abc");
        Assert.assertTrue(bloomFilter.contain("abc"));
        Assert.assertFalse(bloomFilter.contain("bc"));
        bloomFilter.add("bc");
        Assert.assertTrue(bloomFilter.contain("bc"));
        //Then
    }

}
