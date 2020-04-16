package practice;

import java.util.*;

/**
 * 数组中出现次数超过一半的数字
 *
 * 题目描述
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出
 * 现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class Prac28 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] strs = str.split("\\s+");
            int[] array = new int[strs.length];
            for (int i = 0; i < strs.length; i++) {
                array[i] = Integer.parseInt(strs[i]);
            }
            System.out.println(MoreThanHalfNum_Solution6(array));
        }
    }

    /**
     * 思路：利用hashmap计数
     */
    public static int MoreThanHalfNum_Solution(int[] array) {
        if (array.length > 0 && array != null) {
            int length = array.length / 2 + 1;
            Map<Integer, Integer> map = new LinkedHashMap<>();
            for (int i = 0; i < array.length; i++) {
                int current = array[i];
                if (map.containsKey(current)) {
                    map.put(current, map.get(current) + 1);
                } else {
                    map.put(current, 1);
                }
                if (map.get(current) >= length) {
                    return current;
                }
            }
            return 0;
        }
        return 0;
    }

    /**
     * 思路：数组排序后，如果符合条件的数存在，则一定是数组中间那个数。（比如：1，2，2，2，3；或2，2，2，3，4；或2，3，4，4，4等等）
     * 这种方法虽然容易理解，但由于涉及到快排sort，其时间复杂度为O(NlogN)并非最优。
     */
    public int MoreThanHalfNum_Solution1(int[] array) {
        if (array.length > 0 && array != null) {
            int len = array.length;
            int count = 0;
            Arrays.sort(array);
            int num = array[len / 2];
            for (int i = 0; i < len; i++) {
                if (num == array[i])
                    count++;
            }
            if (count <= (len / 2)) {
                num = 0;
            }
            return num;
        }
        return 0;
    }

    /**
     * 思路：因为还是得先排序，所以时间复杂度高。
     */
    public static int MoreThanHalfNum_Solution2(int [] array) {
        if (array.length > 0 && array != null) {
            ArrayList<Integer> list = new ArrayList<Integer>(array.length);
            for(int a : array){
                list.add(a);
            }
            Collections.sort(list);
            int length = array.length / 2 + 1;
            for(int i = 0; i < array.length; i++){
                int current = array[i];
                if(list.lastIndexOf(current) - list.indexOf(current) + 1 >= length){
                    return current;
                }
            }
            return 0;
        }
        return 0;
    }

    /**
     * 思路：如果有符合条件的数字，则它出现的次数比其他所有数字出现的次数和还要多。
     * 在遍历数组时保存两个值：一是数组中一个数字，一是次数。遍历下一个数字时，若它与之前保存的数字相同，则次数加1，否则次数减1；若
     * 次数为0，则保存下一个数字，并将次数置为1。遍历结束后，所保存的数字即为所求。然后再判断它是否符合条件即可。
     */
    public int MoreThanHalfNum_Solution3(int [] array) {
        if (array.length > 0 && array != null) {
            int result = array[0];
            int times = 1;
            for(int a : array){
                if(times == 0){
                    result = a;
                } else if(a == result){
                    times++;
                } else if(a != result){
                    times--;
                }
            }
            times = 0;
            for(int a : array){
                if(a == result){
                    times++;
                }
                if(times > array.length / 2){
                    return a;
                }
            }
            return 0;
        }
        return 0;
    }

    /**
     * 思路：如果有符合条件的数字，则它出现的次数比其他所有数字出现的次数和还要多。不管数组长度是奇数还是偶数，假如有符合条件的数，
     * 那么这个数一定是剩下的一个或者一对，注意最后要遍历一遍计数出现的次数，看是否真的大于数组的半长。
     *
     * 采用阵地攻守的思想：
     * 第一个数字作为第一个士兵，守阵地；count = 1；
     * 遇到相同元素，count++;
     * 遇到不相同元素，即为敌人，同归于尽,count--；当遇到count为0的情况，又以新的i值作为守阵地的士兵，继续下去，到最后还留在阵地上
     * 的士兵，有可能是主元素。
     * 再加一次循环，记录这个士兵的个数看是否大于数组一般即可。
     *
     * 例如：[1,2,3,2,4,2,5,2,3] -> 3（但是3不是目标数）
     */
    public static int MoreThanHalfNum_Solution4(int [] array) {
        if(array.length > 0 && array != null){
            String[] clone = new String[array.length];
            for(int i = 0; i < array.length; i++){
                clone[i] = String.valueOf(array[i]);
            }
            for(int i = 0; i < clone.length; i++){
                if(clone[i] == null){
                    continue;
                }
                for(int j = i + 1; j < clone.length; j++){
                    if(!clone[i].equals(clone[j]) && clone[j] != null){
                        clone[i] = null;
                        clone[j] = null;
                        break;
                    }
                }
            }

            int result = 0;
            for(String s : clone){
                if(s!=null){
                    result = Integer.parseInt(s);
                }
            }

            int times = 0;
            for(int a : array){
                if(a == result){
                    times++;
                }
            }
            return times > array.length / 2 ? result : 0;
        }
        return 0;
    }

    /**
     * 思路：符合条件的数一定是排好序的数组的中位数，因此利用快排的分割方法找到中间位置的数，这个数就有可能是目标数。
     *
     * 注；中位数是指把一列数按大小顺序排列好后，处于最中间位置的那个数就是这列数的中位数。 平均数是把所有的数全加起来,再去除以数的个
     * 数，得出的结果是这列数的平均数。
     */
    public int MoreThanHalfNum_Solution5(int [] array) {
        if(array.length > 0 && array != null){
            int start = 0;
            int end = array.length - 1;
            int middle = array.length / 2;
            int index = partition(array, start, end);
            while(index != middle) {
                if (index > middle) {
                    index = partition(array, start, index - 1);
                } else if (index < middle) {
                    index = partition(array, index + 1, end);
                }
            }

            int result = array[index];
            int times = 0;
            for(int i : array){
                if(i == result){
                    times++;
                }
            }
            return times > array.length / 2 ? result : 0;
        }
        return 0;
    }

    /**
     * 不推荐下面写法，虽然只需一次partition就可以完成，但是partition方法有问题，正确写法参见Test2 - quickSort2
     */
    public static int MoreThanHalfNum_Solution6(int [] array) {
        if(array.length > 0 && array != null){
            int start = 0;
            int end = array.length - 1;
            int middle = array.length / 2;
            partition(array, middle, start, end);
            int result = array[middle];
            int times = 0;
            for(int i : array){
                if(i == result){
                    times++;
                }
            }
            return times > array.length / 2 ? result : 0;
        }
        return 0;
    }

    public static void partition(int[] array, int index, int start, int end){
        int i = start;
        int j = end;
        int pivot = array[index];
        while(i < j){
            while(array[j] >= pivot && i < j){
                j--;
            }
            while(array[i] <= pivot && i < j){
                i++;
            }
            swap(array, i, j);
            System.out.println("i: " + i + " j: " + j);
        }
        swap(array, i, start);
    }

    public static int partition(int[] array, int start, int end){
        int index = random(start, end);
        swap(array, index, end);
        int small = start - 1;
        for(index = start; index < end; index++){
            if(array[start] < array[index]){
                small++;
                if(small < index){
                    swap(array, index, small);
                }
            }
        }
        small++;
        swap(array, small, end);
        return small;
    }

    public static void swap(int[] array, int x, int y){
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    public static int random(int x, int y){
        int max = Math.max(x, y);
        int min = Math.min(x, y);
        return (int)Math.random() * (max - min + 1) + min;
    }
}
