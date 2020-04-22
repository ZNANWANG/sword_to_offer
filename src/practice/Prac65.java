package practice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 题目描述
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵
 * 中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如
 * | a b c e |
 * | s f c s |
 * | a d e e |
 * 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不
 * 能再次进入该格子。
 */
public class Prac65 {
    public static void main(String[] args){
        String str1 = "ABCESFCSADEE";
        str1 = "AAAA";
        String str2 = "ABCCED";
        str2 = "AAAA";
        int rows = 2;
        int cols = 2;
        System.out.println(hasPathDFS(str1.toCharArray(), rows, cols, str2.toCharArray()));
    }

    /**
     * 注意比较BFS和DFS的特性
     * 注意：1、用BFS通过不了，BFS一层一层找，而且不能回头，同层数之间的元素不能相互访问，导致寻找的图形是一个中心向外扩散的菱形，
     * DFS则优先向目标靠近，找不到则返回上一层继续查找，同层数之间的元素有机会能相互访问，查找图形可以是蛇形。
     * 2、用BFS寻路可以找到最短路径，而DFS不一定能，DFS还有可能陷入环中导致死循环。
     */
    public static boolean hasPathBFS(char[] matrix, int rows, int cols, char[] str) {
        if(rows == 0 && cols == 0 || matrix == null || str == null)
            return false;
        char[][] maze = new char[rows][cols];
        int index = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                maze[i][j] = matrix[index++];
            }
        }

        //display(maze);

        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                char current = maze[i][j];
                if(current == str[0]){
                    Queue<Node> queue = new LinkedList<>();
                    Node first = new Node(i, j);
                    queue.offer(first);
                    Queue<Integer> indices = new LinkedList<>();
                    indices.offer(0);
                    boolean[][] visited = new boolean[rows][cols];
                    while(!queue.isEmpty()){
                        Node cur = queue.poll();
                        //System.out.println("x: " + cur.x + " y: " + cur.y);
                        visited[cur.x][cur.y] = true;
                        //display(visited);
                        int depth = indices.poll();
                        //System.out.println("depth: " + depth);
                        if(depth == str.length - 1){
                            return true;
                        }
                        if(isValid(cur.x + 1, cur.y, rows, cols, visited, depth + 1, maze, str)){
                            queue.offer(new Node(cur.x + 1, cur.y));
                            //System.out.println("x: " + cur.x + " y: " + cur.y + "--->" + " x: " + (cur.x + 1) + " y: " + cur.y);
                            indices.offer(depth + 1);
                        }
                        if(isValid(cur.x - 1, cur.y, rows, cols, visited, depth + 1, maze, str)){
                            queue.offer(new Node(cur.x - 1, cur.y));
                            //System.out.println("x: " + cur.x + " y: " + cur.y + "--->" + " x: " + (cur.x - 1) + " y: " + cur.y);
                            indices.offer(depth + 1);
                        }
                        if(isValid(cur.x, cur.y + 1, rows, cols, visited, depth + 1, maze, str)){
                            queue.offer(new Node(cur.x, cur.y + 1));
                            //System.out.println("x: " + cur.x + " y: " + cur.y + "--->" + " x: " + cur.x + " y: " + (cur.y + 1));
                            indices.offer(depth + 1);
                        }
                        if(isValid(cur.x, cur.y - 1, rows, cols, visited, depth + 1, maze, str)){
                            queue.offer(new Node(cur.x, cur.y - 1));
                            //System.out.println("x: " + cur.x + " y: " + cur.y + "--->" + " x: " + cur.x + " y: " + (cur.y - 1));
                            indices.offer(depth + 1);
                        }
                    }
                    //System.out.println();
                }
            }
        }
        return false;
    }

    public static boolean hasPathDFS(char[] matrix, int rows, int cols, char[] str) {
        if(rows == 0 && cols == 0 || matrix == null || str == null)
            return false;
        char[][] maze = new char[rows][cols];
        int index = 0;

        // 初始化迷宫矩阵
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                maze[i][j] = matrix[index++];
            }
        }
        //display(maze);

        // 遍历矩阵，找到和字符数组中第一个字符相等的字符，并开始DFS查找是否有匹配的路径。
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                char current = maze[i][j];
                if(current == str[0]){
                    Stack<Node> stack = new Stack<>();
                    Node first = new Node(i, j);
                    stack.push(first);
                    Stack<Integer> indices = new Stack<>(); // 记录遍历的层数，方便和字符数组相应位置的字符比对。
                    indices.push(0);
                    boolean[][] visited = new boolean[rows][cols]; // 记录走过的位置，防止走回头路。
                    while(!stack.isEmpty()){
                        Node cur = stack.pop();
                        //System.out.println("x: " + cur.x + " y: " + cur.y);
                        // 注意visited变true的操作不能放在添加节点的时候，因为添加的时候实际上还没访问，但是就已经禁止访问了，所以会出错。
                        visited[cur.x][cur.y] = true;
                        //display(visited);
                        int depth = indices.pop();
                        //System.out.println("depth: " + depth);
                        if(depth == str.length - 1){ // 找到最后一个字符则返回true
                            return true;
                        }
                        if(isValid(cur.x + 1, cur.y, rows, cols, visited, depth + 1, maze, str)){ // 向下
                            stack.push(new Node(cur.x + 1, cur.y));
                            //System.out.println("x: " + cur.x + " y: " + cur.y + "--->" + " x: " + (cur.x + 1) + " y: " + cur.y);
                            indices.push(depth + 1);
                        }
                        if(isValid(cur.x - 1, cur.y, rows, cols, visited, depth + 1, maze, str)){ // 向上
                            stack.push(new Node(cur.x - 1, cur.y));
                            //System.out.println("x: " + cur.x + " y: " + cur.y + "--->" + " x: " + (cur.x - 1) + " y: " + cur.y);
                            indices.push(depth + 1);
                        }
                        if(isValid(cur.x, cur.y + 1, rows, cols, visited, depth + 1, maze, str)){ // 向右
                            stack.push(new Node(cur.x, cur.y + 1));
                            //System.out.println("x: " + cur.x + " y: " + cur.y + "--->" + " x: " + cur.x + " y: " + (cur.y + 1));
                            indices.push(depth + 1);
                        }
                        if(isValid(cur.x, cur.y - 1, rows, cols, visited, depth + 1, maze, str)){ // 向左
                            stack.push(new Node(cur.x, cur.y - 1));
                            //System.out.println("x: " + cur.x + " y: " + cur.y + "--->" + " x: " + cur.x + " y: " + (cur.y - 1));
                            indices.push(depth + 1);
                        }
                    }
                    //System.out.println();
                }
            }
        }
        return false;
    }

    public static boolean isValid(int row, int col, int rows, int cols, boolean[][] visited, int index, char[][] maze, char[] str){
        if(row < 0 || row >= rows || col < 0 || col >= cols || index >= str.length || visited[row][col]){
            return false;
        }
        char c = maze[row][col];
        if(c != str[index]){
            return false;
        }
        return true;
    }

    public static void display(char[][] maze){
        for(int i = 0; i < maze.length; i++){
            System.out.println(Arrays.toString(maze[i]));
        }
    }

    public static void display(boolean[][] visited){
        for(int i = 0; i < visited.length; i++){
            System.out.println(Arrays.toString(visited[i]));
        }
    }
}
