package Week_07.friend_cycle;

import org.junit.Assert;
import org.junit.Test;

public class friend_cycle {
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        int n = M.length;
        UF uf = new UF(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (M[i][j] == 1) {
                    uf.unite(i, j);
                }
            }
        }
        return uf.count;
    }

    static class UF {
        private int count;
        private int[] parent;
        private int[] size;

        public UF (int n) {
           this.count = n;
           parent = new int[n];
           size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void unite (int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += rootP;
            }
            count--;
        }

        private int find (int x) {
            while (parent[x] != x) {
                // 路径压缩
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
    }

    @Test
    public void test_official() {
        //Given

        //When
        friend_cycle cycle = new friend_cycle();
        int result = cycle.findCircleNum(new int[][]{{1,1,0}, {1,1,0}, {0,0,1}});
        Assert.assertEquals(result, 2);

        //Then
    }
}
