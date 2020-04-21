package practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 机器人的运动范围
 * <p>
 * 题目描述
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐
 * 标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为
 * 3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class Prac66 {
    public int movingCount(int threshold, int rows, int cols) {
        if(rows == 0 || cols == 0 || threshold == 0){
            return 0;
        }
        Node start = new Node(0, 0);
        Queue<Node> nodes = new LinkedList<>();
        return 0;
    }
}

class Node{
    int x = 0;
    int y = 0;
    int num = 0;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setNum(int num){
        this.num = num;
    }

    public int getNum(){
        return num;
    }
}
