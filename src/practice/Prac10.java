package practice;

/**
 * 矩形覆盖
 * <p>
 * 题目描述
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * 注：要求覆盖2*n的大矩形，不是任意形状。
 * <p>
 * 比如n=3时，2*3的矩形块有3种覆盖方法：prac10_rect
 */
public class Prac10 {
    public int RectCover(int target) {
        if (target == 0) {
            return 0;
        } else if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return RectCover(target - 1) + RectCover(target - 2);
        }
    }

    public int RectCover2(int target) {
        if (target == 0) {
            return 0;
        } else if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            int a = 1;
            int b = 2;
            int c = 1;
            while (target > 2) {
                c = a + b;
                a = b;
                b = c;
                target--;
            }
            return c;
        }
    }
}
