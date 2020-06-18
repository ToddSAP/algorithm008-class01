package Week_09.unique_word;

public class UniqueChar {
    public static int findUniqueChar (String s) {
        if (s == null || s.length() == 0) return -1;
        int[] chars = new int[256];
        for (int i = 0; i < s.length(); i++) chars[s.charAt(i)]++;
        for (int i = 0; i < s.length(); i++) if (chars[s.charAt(i)] == 1) return i;
        return -1;
    }
}
