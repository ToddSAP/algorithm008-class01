package Week_04.min_mutation;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class MinMutation {

    public static int minMutation_bidirectional(String start, String end, String[] bank) {
        HashSet<String> set = new HashSet<>(Arrays.asList(bank));
        if (!set.contains(end)) {
            return -1;
        }
        char[] four = {'A', 'C', 'G', 'T'};
        HashSet<String> positive = new HashSet<String>(){{add(start);}};
        HashSet<String> negative = new HashSet<String>(){{add(end);}};
        HashSet<String> tempNewSet = new HashSet<>();
        int step = 0;
        while (positive.size() > 0 && negative.size() > 0) {
            step++;
            // 每次判断正向是否比负向的元素更多。我们选择元素更小的那个继续更新。
            if (positive.size() > negative.size()) {
                HashSet<String> temp = new HashSet<>(positive);
                positive = negative;
                negative = temp;
            }

            // 遍历每个正向的元素。
            for (String item : positive) {
                String str;
                char[] tempStringChars = item.toCharArray();
                for (int i = tempStringChars.length - 1; i >= 0; --i) {
                    char oldChar = tempStringChars[i];
                    for (int j = 0; j < 4; ++j) {
                        tempStringChars[i] = four[j];
                        String newString = new String(tempStringChars);
                        if (negative.contains(newString)) {
                            return step;
                        } else if (set.contains(newString)) {
                            set.remove(newString);
                            tempNewSet.add(newString);
                        }
                    }
                    tempStringChars[i] = oldChar;
                }
            }
            positive = new HashSet<>(tempNewSet);
            tempNewSet.clear();
        }
        return -1;
    }

    public static int minMutation(String start, String end, String[] bank) {
        HashSet<String> set = new HashSet<>(Arrays.asList(bank));
        if (!set.contains(end)) {
            return -1;
        }
        char[] four = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        set.remove(start);
        int step = 0;
        while (queue.size() > 0) {
            step++;
            for (int count = queue.size(); count > 0; --count) {
                char[] temStringChars = queue.poll().toCharArray();
                for (int i = 0, len = temStringChars.length; i < len; ++i) {
                    char oldChar = temStringChars[i];
                    for (int j = 0; j < 4; ++j) {
                        temStringChars[i] = four[j];
                        String newGenetic = new String(temStringChars);
                        if (end.equals(newGenetic)) {
                            return step;
                        } else if (set.contains(newGenetic)) {
                            set.remove(newGenetic);
                            queue.offer(newGenetic);
                        }
                    }
                    temStringChars[i] = oldChar;
                }
            }
        }
        return -1;
    }

    @Test
    public void test_official() {
        //Given

        //When
        int result = MinMutation.minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"});
        Assert.assertEquals(result, 2);

        /*result = MinMutation.minMutation_bidirectional("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"});
        Assert.assertEquals(result, 2);*/


        result = MinMutation.minMutation_bidirectional("AAAAACCC", "AACCCCCC", new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"});
        Assert.assertEquals(result, 3);
    }


    public static int minMutation_exercise(String start, String end, String[] bank) {
        // 转换成基因序列集合，提升后续比对效率
        HashSet<String> geneSequences = new HashSet<>(Arrays.asList(bank));
        // 验证最终基因序列是否合法，当然，本题已假定必然合法，可不加
        if (!geneSequences.contains(end)) {
            return -1;
        }
        // 碱基集合
        char[] basePairs = new char[]{'A','C','G','T'};
        // 存放中间基因序列
        LinkedList<String> queue = new LinkedList<>();
        // 起始基因序列入队
        queue.offer(start);
        // 变化次数，即BFS走过的层数
        int step = 0;
        // 所有的中间序列都处理完毕，才意味着BFS走完了所有可能的路径
        while (!queue.isEmpty()) {
            // 进入下一层
            step++;
            // 一次最外层while需要把当前queue中的序列都处理掉，即处理BFS的完整的一层
            // 确定本次要出队多少中间序列
            int geneSequenceNumInQueue = queue.size();
            // 遍历并出队中间序列
            while (geneSequenceNumInQueue-- > 0) {
                String currGeneSequence = queue.pop();
                char[] genes = currGeneSequence.toCharArray();
                // 两层循环，替换每一位上的基因，并比对处理
                for (int i = 0; i < genes.length; i++) {
                    // 保存原始的基因
                    char oldGene = genes[i];
                    for (int j = 0; j < basePairs.length; j++) {
                        // 替换基因
                        genes[i] = basePairs[j];
                        String newGeneSequence = String.valueOf(genes);
                        // 验证新的序列是否合法
                        if (geneSequences.contains(newGeneSequence)) {
                            // 找到了最终序列
                            if (newGeneSequence.equals(end)) {
                                return step;
                            }
                            // 合法但不是最终序列，即发现了新的中间序列，加入队列
                            queue.offer(newGeneSequence);
                            // 从基因序列集合中删除，表示已访问过BFS的某顶点
                            geneSequences.remove(newGeneSequence);
                        }
                    }
                    // 恢复成原始的基因
                    genes[i] = oldGene;
                }
            }
        }
        return -1;
    }


    static int minStepCount = Integer.MAX_VALUE;
    public static int minMutation_dfs(String start, String end, String[] bank) {
        dfs(new HashSet<String>(), 0, start, end, bank);
        return (minStepCount == Integer.MAX_VALUE) ? -1 : minStepCount;
    }
    private static void dfs (HashSet<String> step, int stepCount,
                      String current, String end, String[] bank) {
        if (current.equals(end))
            minStepCount = Math.min(stepCount, minStepCount);
        for (String str : bank) {
            int diff = 0;
            for (int i = 0; i < str.length(); i++)
                if (current.charAt(i) != str.charAt(i))
                    if (++diff > 1) break;
            if (diff == 1 && !step.contains(str)) {
                step.add(str);
                dfs(step, stepCount + 1, str, end, bank);
                step.remove(str);
            }
        }
    }

    static int minStep = Integer.MAX_VALUE;
    public static int minMutation_dfs_exercise(String start, String end, String[] bank) {
        HashSet<String> visited = new HashSet<>();
        dfs_exercise(visited, 0, start, end, bank);
        return minStep == Integer.MAX_VALUE ? -1 : minStep;
    }

    private static void dfs_exercise(HashSet<String> visited, int step, String curr, String end, String[] bank) {
        if (curr.equals(end)) {
            minStep = Math.min(step, minStep);
        }
        // 找到所有和curr只差一个基因且未访问过的合法序列，然后继续dfs
        for (String valid : bank) {
            if (diff1(valid, curr) && !visited.contains(valid)) {
                visited.add(valid);
                dfs_exercise(visited, step+1, valid, end, bank);
                visited.remove(valid);
            }
        }
    }

    private static boolean diff1(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
            if (diff > 1) {
                break;
            }
        }
        return diff == 1;
    }

    @Test
    public void test_exercise() {
        //Given

        //When
        int result = MinMutation.minMutation_exercise("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"});
        Assert.assertEquals(result, 2);

        result = MinMutation.minMutation_dfs_exercise("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"});
        Assert.assertEquals(result, 2);
        //Then
    }

    @Test
    public void test_error() {
        //Given

        //When
        int result = MinMutation.minMutation_dfs_exercise("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"});
        Assert.assertEquals(result, 2);

        //Then
    }

}
