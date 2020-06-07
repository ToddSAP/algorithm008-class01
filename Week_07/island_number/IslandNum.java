package Week_07.island_number;

import org.junit.Assert;
import org.junit.Test;

public class IslandNum {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rowNum = grid.length, colNum = grid[0].length;
        if (colNum < 1) return 0;
        UF uf = new UF(rowNum);
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    if (rowNum - 1 > 0 && grid[i-1][j] == '1') uf.unite(i-1, j);
                    if (rowNum + 1 < rowNum && grid[i+1][j] == '1') uf.unite(i+1, j);
                    if (colNum - 1 > 0 && grid[i][j-1] == '1') uf.unite(i, j-1);
                    if (colNum + 1 < colNum && grid[i][j+1] == '1') uf.unite(i, j+1);
                }
            }
        }
        return uf.count;
    }

    static class UF {
        // 连通分量数量
        private int count;
        // 根节点关系列表
        private int[] parent;
        // 各集合中的元素个数
        private int[] size;

        public UF (int n) {
            count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void unite(int p, int q) {
            int rootP = search(p);
            int rootQ = search(q);
            if (rootP == rootQ) return;
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = parent[rootP];
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = parent[rootQ];
                size[rootQ] += size[rootP];
            }
            count--;
        }

        public int search(int p) {
            while (parent[p] != p) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }
    }

    @Test
    public void test_official() {
        //Given
        IslandNum islandNum = new IslandNum();
        int result = islandNum.numIslands(new char[][]{{1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1}});
        Assert.assertEquals(3, result);

        //When

        //Then
    }
}
