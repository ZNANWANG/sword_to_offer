package practice;

import java.lang.reflect.Array;
import java.time.Year;
import java.util.*;

/**
 * 机器人的运动范围
 * <p>
 * 题目描述
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐
 * 标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为
 * 3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class Prac66 {
    public static void main(String[] args) {
        int[][] matrix = intiMatrix(3);
        display(matrix);
        System.out.println(routeBFS2(matrix));
        System.out.println(movingCount1(5, 10, 10)); // 21
    }

    public static void display(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public static int[][] intiMatrix(int length) {
        int[][] matrix = new int[length][length];
        int index = 1;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = index;
                index++;
            }
        }
        return matrix;
    }

    /**
     * 思路：
     * 递归求解
     */
    public int movingCount(int threshold, int rows, int cols) {
        if (rows == 0 || cols == 0 || threshold == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[rows][cols];
        return helper(threshold, 0, 0, rows, cols, visited);
    }

    public static int helper(int threshold, int row, int col, int rows, int cols, boolean[][] visited) {
        if (row < 0 || col < 0 || row >= rows || col >= cols) {
            return 0;
        }
        if (check(row, col, threshold) && !visited[row][col]) {
            visited[row][col] = true;
            return helper(threshold, row + 1, col, rows, cols, visited)
                    + helper(threshold, row - 1, col, rows, cols, visited)
                    + helper(threshold, row, col + 1, rows, cols, visited)
                    + helper(threshold, row, col, rows, cols + 1, visited)
                    + 1;
        }
        return 0;
    }

    public static boolean check(int col, int row, int threshold) {
        int sum = 0;
        while (col != 0) {
            sum += col % 10;
            col = col / 10;
        }
        while (row != 0) {
            sum += row % 10;
            row = row / 10;
        }
        if (sum <= threshold) {
            return true;
        }
        return false;
    }

    /**
     * BFS —— 迷宫寻址
     */
    public static int movingCount1(int threshold, int rows, int cols) {
        if (rows == 0 && cols == 0 || threshold < 1) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        Node start = new Node(0, 0);
        queue.offer(start);
        boolean[][] visited = new boolean[rows][cols];
        visited[0][0] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            count++;
            if (checkXY(curNode.x + 1, curNode.y, rows, cols, threshold, visited)) {
                Node newNode = new Node(curNode.x + 1, curNode.y);
                queue.offer(newNode);
                visited[newNode.x][newNode.y] = true;
            }

            if (checkXY(curNode.x, curNode.y + 1, rows, cols, threshold, visited)) {
                Node newNode = new Node(curNode.x, curNode.y + 1);
                queue.offer(newNode);
                visited[newNode.x][newNode.y] = true;
            }

            if (checkXY(curNode.x - 1, curNode.y, rows, cols, threshold, visited)) {
                Node newNode = new Node(curNode.x - 1, curNode.y);
                queue.offer(newNode);
                visited[newNode.x][newNode.y] = true;
            }

            if (checkXY(curNode.x, curNode.y - 1, rows, cols, threshold, visited)) {
                Node newNode = new Node(curNode.x, curNode.y - 1);
                queue.offer(newNode);
                visited[newNode.x][newNode.y] = true;
            }
        }
        return count;
    }

    public static boolean checkXY(int row, int col, int rows, int cols, int threshold, boolean[][] visited) {
        if (row >= 0 && row < rows && col >= 0 && col < cols && !visited[row][col]) {
            int sum = 0;
            while (col != 0) {
                sum += col % 10;
                col = col / 10;
            }
            while (row != 0) {
                sum += row % 10;
                row = row / 10;
            }
            if (sum <= threshold) {
                return true;
            }
        }
        return false;
    }

    /**
     * BFS迷宫寻路
     */
    public static ArrayList<ArrayList<Integer>> routeBFS(int[][] matrix) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (matrix == null) {
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        Node start = new Node(0, 0);
        Node end = new Node(matrix.length - 1, matrix[0].length - 1);
        queue.offer(start);

        Queue<ArrayList<Integer>> paths = new LinkedList<>();
        ArrayList<Integer> path = new ArrayList<>();
        path.add(matrix[start.x][start.y]);
        paths.offer(path);

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            ArrayList<Integer> curPath = paths.poll();
            System.out.println("current node: " + "x: " + curNode.x + " y: " + curNode.y);
            System.out.println("current path: " + curPath);
            if (curNode.equals(end)) {
                result.add(curPath);
                continue;
            }

            if (isValid(matrix, curNode.x + 1, curNode.y)) {
                Node newNode = new Node(curNode.x + 1, curNode.y);
                queue.offer(newNode);
                ArrayList<Integer> newPath = new ArrayList<>(curPath);
                newPath.add(matrix[newNode.x][newNode.y]);
                paths.offer(newPath);
            }

            if (isValid(matrix, curNode.x, curNode.y + 1)) {
                Node newNode = new Node(curNode.x, curNode.y + 1);
                queue.offer(newNode);
                ArrayList<Integer> newPath = new ArrayList<>(curPath);
                newPath.add(matrix[newNode.x][newNode.y]);
                paths.offer(newPath);
            }
        }
        return result;
    }

    /**
     * BFS —— 迷宫寻路（可走四个方向，避免走回头路）
     */
    public static ArrayList<ArrayList<Integer>> routeBFS2(int[][] matrix) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (matrix == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        Node start = new Node(0, 0);
        Node end = new Node(matrix.length - 1, matrix[0].length - 1);
        queue.offer(start);
        Queue<ArrayList<Integer>> paths = new LinkedList<>();
        ArrayList<Integer> path = new ArrayList<>();
        path.add(matrix[start.x][start.y]);
        paths.offer(path);
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            ArrayList<Integer> curPath = paths.poll();
            System.out.println("current node: " + "x: " + curNode.x + " y: " + curNode.y);
            System.out.println("current path: " + curPath);
            visited[curNode.x][curNode.y] = true;
            if (curNode.equals(end)) {
                result.add(curPath);
                continue;
            }
            if (isValid(matrix, curNode.x + 1, curNode.y) && !visited[curNode.x + 1][curNode.y]) {
                Node newNode = new Node(curNode.x + 1, curNode.y);
                queue.offer(newNode);
                ArrayList<Integer> newPath = new ArrayList<>();
                newPath.addAll(curPath);
                newPath.add(matrix[newNode.x][newNode.y]);
                paths.add(newPath);
            }

            if (isValid(matrix, curNode.x - 1, curNode.y) && !visited[curNode.x - 1][curNode.y]) {
                Node newNode = new Node(curNode.x - 1, curNode.y);
                queue.offer(newNode);
                ArrayList<Integer> newPath = new ArrayList<>();
                newPath.addAll(curPath);
                newPath.add(matrix[newNode.x][newNode.y]);
                paths.add(newPath);
            }

            if (isValid(matrix, curNode.x, curNode.y + 1) && !visited[curNode.x][curNode.y + 1]) {
                Node newNode = new Node(curNode.x, curNode.y + 1);
                queue.offer(newNode);
                ArrayList<Integer> newPath = new ArrayList<>();
                newPath.addAll(curPath);
                newPath.add(matrix[newNode.x][newNode.y]);
                paths.add(newPath);
            }

            if (isValid(matrix, curNode.x, curNode.y - 1) && !visited[curNode.x][curNode.y - 1]) {
                Node newNode = new Node(curNode.x, curNode.y - 1);
                queue.offer(newNode);
                ArrayList<Integer> newPath = new ArrayList<>();
                newPath.addAll(curPath);
                newPath.add(matrix[newNode.x][newNode.y]);
                paths.add(newPath);
            }
        }
        return result;
    }

    /**
     * DFS —— 迷宫寻路
     */
    public static ArrayList<ArrayList<Integer>> routeDFS(int[][] matrix) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (matrix == null) {
            return result;
        }

        Stack<Node> stack = new Stack<>();
        Node start = new Node(0, 0);
        Node end = new Node(matrix.length - 1, matrix[0].length - 1);
        stack.push(start);

        Stack<ArrayList<Integer>> paths = new Stack<>();
        ArrayList<Integer> path = new ArrayList<>();
        path.add(matrix[start.x][start.y]);
        paths.push(path);

        while (!stack.isEmpty()) {
            Node curNode = stack.pop();
            ArrayList<Integer> curPath = paths.pop();
            System.out.println("current node: " + "x: " + curNode.x + " y: " + curNode.y);
            System.out.println("current path: " + curPath);
            if (curNode.equals(end)) {
                result.add(curPath);
                continue;
            }

            if (isValid(matrix, curNode.x + 1, curNode.y)) {
                Node newNode = new Node(curNode.x + 1, curNode.y);
                stack.push(newNode);
                ArrayList<Integer> newPath = new ArrayList<>(curPath);
                newPath.add(matrix[newNode.x][newNode.y]);
                paths.push(newPath);
            }

            if (isValid(matrix, curNode.x, curNode.y + 1)) {
                Node newNode = new Node(curNode.x, curNode.y + 1);
                stack.push(newNode);
                ArrayList<Integer> newPath = new ArrayList<>(curPath);
                newPath.add(matrix[newNode.x][newNode.y]);
                paths.push(newPath);
            }
        }
        return result;
    }

    public static boolean isValid(int[][] matrix, int x, int y) {
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }

    /**
     * 递归解法
     * 注：递归是有顺序的，本质上和DFS很像，不断贪心向目标靠近，失败了就返回上一层继续找，当前分支囊括了所有结点延伸的所有可能，意味
     * 其他分支没必要中途插入此分支的任何一段，因为所有满足条件可能都穷尽了仍然找不到目标，再次重走一遍依旧是徒然，所以设置visited
     * 一是为了防止走回头路，二是防止当前分支走其他走不通的分支的老路，做无用功。
     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || matrix.length == 0 || str == null || str.length == 0 || matrix.length != rows * cols || rows <= 0 || cols <= 0 || rows * cols < str.length) {
            return false;
        }

        boolean[] visited = new boolean[rows * cols];
        int pathLength = 0;

        for (int i = 0; i <= rows - 1; i++) {
            for (int j = 0; j <= cols - 1; j++) {
                if (hasPathCore(matrix, rows, cols, str, i, j, visited, pathLength)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasPathCore(char[] matrix, int rows, int cols, char[] str, int row, int col, boolean[] visited, int pathLength) {
        boolean flag = false;

        if (row >= 0 && row < rows && col >= 0 && col < cols && !visited[row * cols + col] && matrix[row * cols + col] == str[pathLength]) {
            pathLength++;
            visited[row * cols + col] = true;
            if (pathLength == str.length) {
                return true;
            }
            flag = hasPathCore(matrix, rows, cols, str, row, col + 1, visited, pathLength) ||
                    hasPathCore(matrix, rows, cols, str, row + 1, col, visited, pathLength) ||
                    hasPathCore(matrix, rows, cols, str, row, col - 1, visited, pathLength) ||
                    hasPathCore(matrix, rows, cols, str, row - 1, col, visited, pathLength);

            if (!flag) {
                pathLength--;
                visited[row * cols + col] = false;
            }
        }
        return flag;
    }

    /**
     * 某个大佬的解法，讲得比较详细有条理，摘下来以供参考，绝不是面向Ctrl + C编程。
     */
    public boolean hasPath1(char[] matrix, int rows, int cols, char[] str) {
        //标志位，初始化为false
        boolean[] flag = new boolean[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //循环遍历二维数组，找到起点等于str第一个元素的值，再递归判断四周是否有符合条件的----回溯法
                if (judge(matrix, i, j, rows, cols, flag, str, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    //judge(初始矩阵，索引行坐标i，索引纵坐标j，矩阵行数，矩阵列数，待判断的字符串，字符串索引初始为0即先判断字符串的第一位)
    private boolean judge(char[] matrix, int i, int j, int rows, int cols, boolean[] flag, char[] str, int k) {
        //先根据i和j计算匹配的第一个元素转为一维数组的位置
        int index = i * cols + j;
        //递归终止条件
        if (i < 0 || j < 0 || i >= rows || j >= cols || matrix[index] != str[k] || flag[index] == true)
            return false;
        //若k已经到达str末尾了，说明之前的都已经匹配成功了，直接返回true即可
        if (k == str.length - 1)
            return true;
        //要走的第一个位置置为true，表示已经走过了
        flag[index] = true;

        //回溯，递归寻找，每次找到了就给k加一，找不到，还原
        if (judge(matrix, i - 1, j, rows, cols, flag, str, k + 1) ||
                judge(matrix, i + 1, j, rows, cols, flag, str, k + 1) ||
                judge(matrix, i, j - 1, rows, cols, flag, str, k + 1) ||
                judge(matrix, i, j + 1, rows, cols, flag, str, k + 1)) {
            return true;
        }
        //走到这，说明这一条路不通，还原，再试其他的路径
        flag[index] = false;
        return false;
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Node node) {
        return this.x == node.x && this.y == node.y;
    }
}