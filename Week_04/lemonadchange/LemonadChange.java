package Week_04.lemonadchange;

import org.junit.Assert;
import org.junit.Test;

public class LemonadChange {
    public static boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                five++;
            } else if (bills[i] == 10) {
                if (five == 0) return false;
                five--; ten++;
            } else {
                if (ten > 0 && five > 0) {
                    ten--; five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean lemonadeChange_enhance(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) five++;
            else if (bill == 10 && five > 0) {five--; ten++;}
            else if (bill == 20 && (ten > 0 && five > 0)) {five--;ten--;}
            else if (bill == 20 && (five >= 3)) five -=3;
            else return false;
        }
        return true;
    }

    @Test
    public void test_official() {
        //Given

        //When
        boolean result = LemonadChange.lemonadeChange(new int[]{5,5,5,10,20});
        Assert.assertTrue(result);

        result = LemonadChange.lemonadeChange(new int[]{10,10});
        Assert.assertTrue(!result);

        result = LemonadChange.lemonadeChange(new int[]{5,5,5,20});
        Assert.assertTrue(result);

        result = LemonadChange.lemonadeChange(new int[]{5,5,20});
        Assert.assertTrue(!result);
        //Then
    }
}
