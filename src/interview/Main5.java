package interview;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 完美世界笔试题 —— 输出旋转数组
 * <p>
 * 例如：3x3数组输出如下：
 * 1 2 3
 * 8 9 4
 * 7 6 5
 */
public class Main5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] res = build2DMatrix(n);
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
    }

    public static int[][] build2DMatrix(int n) {
        // write code here
        int[][] res = new int[n][n];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        res[0][0] = 1;
        int count = 0;
        int curX = 0;
        int curY = 0;
        System.out.println("start");
        for (int i = 2; i <= n * n; i++) {
            int tempX = curX + dir[count][0];
            int tempY = curY + dir[count][1];
            if (tempX >= 0 && tempX < n && tempY >= 0 && tempY < n && res[tempX][tempY] == 0) {
                curX += dir[count][0];
                curY += dir[count][1];
                res[curX][curY] = i;
            } else {
                count = (count + 1) % 4;
                curX += dir[count][0];
                curY += dir[count][1];
                res[curX][curY] = i;
            }
            System.out.println(curX + " " + curY);
        }
        return res;
    }
}
