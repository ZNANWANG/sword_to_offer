package practice;

import java.util.ArrayList;

/**
 * 顺时针打印矩阵
 * <p>
 * 题目描述
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵：
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class Prac19 {
    /**
     * 注意到每圈的起始点都满足横坐标和纵坐标相等
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null) {
            return null;
        }
        int start = 0;
        int columns = matrix[0].length;
        int rows = matrix.length;
        ArrayList<Integer> list = new ArrayList<>();
        while (columns > start * 2 && rows > start * 2) {
            printRing(matrix, columns, rows, start, list);
            start++;
        }
        return list;
    }

    /**
     * 注意到每一圈的行数和列数都递减2，每一圈都是对称的，因为每一圈起点横坐标和纵坐标都递增1，因此可以算出每一圈最后一行和最后一列
     * 的索引为endX = rows - start - 1和endY = columns - start - 1.
     * 例如：对于5x5的矩阵，start可以看作前几圈用掉的列数，用columns减去start得到的就是最后一列所位于的列数，将得到的数再减一就得
     * 到最后一列的索引；同理，start也可看做前几圈用掉的行数。
     * 第一圈： start = 0, rows = 5, rows - start = 5 （代表最后一行是第五行），
     * endX = rows - start - 1 = 4 （代表最后一行索引）
     * start = 0, columns = 5, columns - start = 5 （代表最后一列是第五列），
     * endY = columns - start - 1 = 4 （代表最后一列索引）
     * 第二圈： start = 1, rows = 5, rows - start = 4 （代表最后一行是第五行），
     * endX = rows - start - 1 = 3 （代表最后一行索引）
     * start = 1, columns = 5, columns - start = 4 （代表最后一列是第五列），
     * endY = columns - start - 1 = 3 （代表最后一列索引）
     * 第三圈： start = 2, rows = 5, rows - start = 3 （代表最后一行是第五行），
     * endX = rows - start - 1 = 2 （代表最后一行索引）
     * start = 2, columns = 5, columns - start = 3 （代表最后一列是第五列），
     * endY = columns - start - 1 = 2 （代表最后一列索引）
     */
    public void printRing(int[][] matrix, int columns, int rows, int start, ArrayList<Integer> list) {
        int endX = rows - start - 1;
        int endY = columns - start - 1;

        // 向右走是一定会进行的，即使是一圈只有一个元素的特殊情况
        for (int i = start; i <= endY; i++) {
            list.add(matrix[start][i]);
        }

        // 向下走则必须保证最后一行大于起始行（注意到存在一圈是单独一行或者一个元素的情况）
        if (start < endX) {
            for (int i = start + 1; i <= endX; i++) {
                list.add(matrix[i][endY]);
            }
        }

        // 向左走必须同时保证最后一行大于起始行并且最后一列大于起始列（注意到存在一圈是单独一列的情况），因此要保证能向左走，一圈至
        // 少要是2x2的矩阵
        if (start < endY && start < endX) {
            for (int i = endY - 1; i >= start; i--) {
                list.add(matrix[endX][i]);
            }
        }

        // 向上走必须同时保证最后一行大于起始行加一并且最后一列大于起始列，因此要保证能向左走，一圈至少要是3x2的矩阵
        if (start < endY && start < endX - 1) {
            for (int i = endX - 1; i > start; i--) {
                list.add(matrix[i][start]);
            }
        }
    }


    // 走的方向：向右、向下、向左、向上
    private final int[] dx = {0, 1, 0, -1};
    private final int[] dy = {1, 0, -1, 0};

    /**
     * 迷宫走法
     */
    public ArrayList<Integer> printMatrix1(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        boolean[][] visited = new boolean[n][m];
        ArrayList<Integer> list = new ArrayList<>();

        int x = 0, y = 0, dir = 0;
        while (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y]) {
            list.add(matrix[x][y]);
            visited[x][y] = true;

            // 试着继续向dir的方向走
            while (x + dx[dir] >= 0 && x + dx[dir] < n && y + dy[dir] >= 0 && y + dy[dir] < m && !visited[x + dx[dir]][y + dy[dir]]) {
                x += dx[dir];
                y += dy[dir];
                list.add(matrix[x][y]);
                visited[x][y] = true;
            }
            // 走不动了换方向
            dir = (dir + 1) % 4;
            x += dx[dir];
            y += dy[dir];
        }
        return list;
    }

    /**
     *
     */
    public ArrayList<Integer> printMatrix2(int[][] matrix) {

        ArrayList<Integer> result = new ArrayList<>();
        if (matrix == null) return result;

        int low = 0;
        int high = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (low <= high && left <= right) {

            //向右
            for (int i = left; i <= right; i++)
                result.add(matrix[low][i]);

            //向下
            for (int i = low + 1; i <= high; i++)
                result.add(matrix[i][right]);

            //向左 有可能出现特殊的情况只有一行，为了避免重复访问
            if (low < high) {
                for (int i = right - 1; i >= left; i--)
                    result.add(matrix[high][i]);
            }

            //向上 有可能出现特殊的情况只有一列，为了避免重复访问
            if (left < right) {
                for (int i = high - 1; i >= low + 1; i--)
                    result.add(matrix[i][left]);
            }

            low++;
            high--;
            left++;
            right--;
        }
        return result;
    }
}
