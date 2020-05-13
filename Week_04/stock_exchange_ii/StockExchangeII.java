package Week_04.stock_exchange_ii;

import org.junit.Assert;
import org.junit.Test;

public class StockExchangeII {
    public static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) profit += prices[i] - prices[i-1];
        }
        return profit;
    }

    static int profit = 0;
    public static int maxProfit_dfs(int[] prices) {
        dfs(0, prices);
        return profit;
    }

    private static void dfs(int level, int[] prices) {
        if (level == prices.length-1) {
            return ;
        }

        if (level+1<prices.length && prices[level] < prices[level+1]) {
            profit = profit + (prices[level+1] - prices[level]);
        }
        dfs(level+1, prices);
    }


    @Test
    public void test_official() {
        //Given

        //When
        int profit = StockExchangeII.maxProfit(new int[]{7,1,5,3,6,4});
        Assert.assertEquals(profit, 7);

        profit = StockExchangeII.maxProfit(new int[]{1,2,3,4,5});
        Assert.assertEquals(profit, 4);

        profit = StockExchangeII.maxProfit_dfs(new int[]{7,1,5,3,6,4});
        Assert.assertEquals(profit, 7);

        profit = StockExchangeII.maxProfit_dfs(new int[]{1,2,3,4,5});
        Assert.assertEquals(profit, 4);
        //Then
    }
}
