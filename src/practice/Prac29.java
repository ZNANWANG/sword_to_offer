package practice;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 最小的k个数
 *
 * 题目描述
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 */
public class Prac29 {
    public static void main(String[] args){
        int[] input = new int[]{4,5,1,6,2,7,3,8};
        int k = 4;
        System.out.println(GetLeastNumbers_Solution(input, k));
    }

    /**
     * 思路
     * k个数后，每轮构造最大堆，每个元素和堆顶元素（堆的最大值）相比，如果比堆顶元素小就替换堆顶元素。
     */
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if(input != null && input.length >= k && k > 0){
            int[] temp = new int[k];
            int count = 0;
            for(int i = 0; i < input.length; i++) {
                int cur = input[i];
                if (count < k) {
                    temp[count] = cur;
                    count++;
                } else {
                    adjustHeap(temp);
                    int max = temp[0];
                    if(cur < max){
                        temp[0] = cur;
                    }
                }
            }
            for(int a : temp){
                result.add(a);
            }
        }
        return result;
    }

    public static void adjustHeap(int[] array){
        for(int i = array.length / 2 - 1; i >= 0; i--){
            int parent = i;
            System.out.println("i: " + i);
            int leftChild = i * 2 + 1;
            System.out.println("left child index: " + leftChild);
            System.out.println("length: " + array.length);
            while(leftChild < array.length){
                int rightChild = leftChild + 1;
                if(rightChild < array.length && array[rightChild] > array[leftChild]){
                    leftChild = rightChild;
                }

                System.out.println("left child: " + array[leftChild]);
                System.out.println("parent: " + array[parent]);
                if(array[leftChild] < array[parent]) {
                    break;
                }
                swap(array, leftChild, parent);
                parent = leftChild;
                leftChild = parent * 2 + 1;
                System.out.println(Arrays.toString(array));
            }
        }
        System.out.println("heap: " + Arrays.toString(array));
    }

    public static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
