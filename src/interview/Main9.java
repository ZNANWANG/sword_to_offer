package interview;

/**
 * 广发银行笔试题目
 * 十三进制（“0123456789CGB"）转三进制("012")
 */
public class Main9 {
    public static void main(String[] args) {
        String str = "C"; // 101
        str = "15"; // 200
        str = "0"; // 0
        str = "BBBBBBBBBB"; // 1002020102202110101
        Main9 test = new Main9();
        test.numberCovert3(str);
    }

    /**
     * 原算法 —— 80%
     */
    public String numberCovert(String number) {
        if (number == null || number.length() == 0) {
            return number;
        }

        // 十三进制转十进制
        char[] numbers = number.toCharArray();
        int res = 0;
        int base = 1;
        for (int i = numbers.length - 1; i >= 0; i--) {
            char c = numbers[i];
            int digit = -1;
            if (c >= '0' && c <= '9') {
                digit = c - '0';
            } else if (c == 'C') {
                digit = 10;
            } else if (c == 'G') {
                digit = 11;
            } else if (c == 'B') {
                digit = 12;
            }
            res += digit * base;
            base *= 13;
        }
        System.out.println(res);

        // 十进制转三进制
        String result = "";
        if (res != 0) {
            StringBuffer sb = new StringBuffer();
            while (res != 0) {
                int num = res % 3;
                res /= 3;
                sb.append(num);
            }
            result = sb.reverse().toString();
        } else {
            result = "0";
        }
        System.out.println(result);

        return result;
    }

    /**
     * 修改后的算法 —— 未测试
     */
    public String numberCovert3(String number) {
        if (number == null || number.length() == 0) {
            return number;
        }

        // 十三进制转十进制
        char[] numbers = number.toCharArray();
        int res = 0;
        int base = 1;
        for (int i = numbers.length - 1; i >= 0; i--) {
            char c = numbers[i];
            int digit = -1;
            if (c >= '0' && c <= '9') {
                digit = c - '0';
            } else if (c == 'C') {
                digit = 10;
            } else if (c == 'G') {
                digit = 11;
            } else if (c == 'B') {
                digit = 12;
            }
            res += digit * base;
            base *= 13;
        }
        System.out.println(res);

        // 十进制转三进制
        String result = "";
        StringBuffer sb = new StringBuffer();
        while (res / 3 >= 1) {
            int num = res % 3;
            res /= 3;
            sb.append(num);
        }
        sb.append(res);
        result = sb.reverse().toString();
        System.out.println(result);

        return result;
    }

    /**
     * 递归将y转为x进制数字
     */
    public static void convert(int x, int y) {
        if (x < 0 || y < 0) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        convertHelper(x, y, sb);
        System.out.println(sb.toString());
    }

    public static void convertHelper(int x, int y, StringBuffer sb) {
        if (y / x >= 1) {
            convertHelper(x, y / x, sb);
            sb.append(y % x);
//            System.out.print(y % x);
        } else {
            sb.append(y);
//            System.out.print(y);
        }
    }
}
