package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * 完美世界笔试题 —— 去除字符串中出现次数最多的字符并返回处理后的字符串
 */
public class Main6 {
    public String removeChar(String str) {
        char[] chars = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int max = 1;
        for (char c : chars) {
            if (map.containsKey(c)) {
                int num = map.get(c) + 1;
                max = max > num ? max : num;
                map.put(c, num);
            } else {
                map.put(c, 1);
            }
        }
        StringBuffer sb = new StringBuffer();
        for (char c : chars) {
            if (map.get(c) != max) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
