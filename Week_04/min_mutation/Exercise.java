package Week_04.min_mutation;

import java.util.HashSet;

public class Exercise {

    static int minStep = Integer.MAX_VALUE;
    public static int minMutation_dfs(String start, String end, String[] bank) {
        dfs(new HashSet<>(), 0, start, end, bank);
        return minStep == Integer.MAX_VALUE ? -1 : minStep;
    }

    private static void dfs(HashSet<String> visited, int step, String curr, String end, String[] bank) {
        if (curr.equals(end))  minStep = Math.min(step, minStep);
        for (String s : bank) {
            int diff = 0;
            for (int i = 0; i < s.length(); i++) {
                if (curr.charAt(i) != s.charAt(i)) diff++;
                if (diff > 1) break;
            }
            if (diff == 1 && !visited.contains(s)) {
                visited.add(s);
                dfs(visited, step+1, s, end, bank);
                visited.remove(s);
            }
        }
    }
}
