package interview;

import java.util.Scanner;
import java.util.Stack;

/**
 * 将一个数字反向输出
 *
 * 携程面试题目
 */
public class Main4 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int num = sc.nextInt();
            getResult1(num);
        }
    }

    // 调用StringBuffer的reverse()方法
    public static void getResult(int num){
        StringBuffer sb = new StringBuffer();
        sb.append(num);
        System.out.println(sb.reverse().toString());
    }

    // 利用Stack或者Queue
    public static void getResult1(int num){
        char[] chars = String.valueOf(num).toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char c : chars){
            stack.push(c);
        }

        while(!stack.isEmpty()){
            System.out.print(stack.pop());
        }
        System.out.println();
    }

    // num%10得到num的个位数，num/10得到个位以上的数。
    public static void getResult2(int num){
        int res = 0;
        int flag = 10;
        while(true){
            int n = num % 10;
            res = res * 10 + n;
            num = num / 10;
            if(num == 0) {
                break;
            }
        }
        System.out.println(res);
    }
}
