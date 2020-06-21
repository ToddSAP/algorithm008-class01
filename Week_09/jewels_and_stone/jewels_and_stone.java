package Week_09.jewels_and_stone;

import java.util.HashMap;

public class jewels_and_stone {
    public int numJewelsInStones(String J, String S) {
        if (J == null || S == null || J.length() == 0 || S.length() == 0) return 0;
        HashMap<Character, Character> jewelMap = new HashMap<>();
        for (char c : J.toCharArray())
            jewelMap.put(c, c);
        int n = 0;
        for (char c : S.toCharArray())
            if (jewelMap.get(c) != null) n++;
        return n;
    }
}
