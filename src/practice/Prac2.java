package practice;

/**
 * 替换空格
 *
 * 题目描述
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class Prac2 {
    /**
     *思 路
     * 直接利用正则表达式进行替换
     */
    public String replaceSpace1(StringBuffer str) {
        String s = str.toString();
        s = s.replaceAll("\\s", "%20"); //注意必须赋值
        return s;
    }

    /**
     * 思路
     * StringBuffer填充
     */
    public String replaceSpace2(StringBuffer str) {
        String s = str.toString();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i<s.length();i++){
            char c = str.charAt(i);
            if(c == ' '){
                sb.append("%20");
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 思路
     * 利用String方法进行替换
     */
    public String replaceSpace3(StringBuffer str) {
        int spacenum = 0;//spacenum为计算空格数
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' ')
                spacenum++;
        }
        int indexold = str.length()-1; //indexold为为替换前的str下标
        int newlength = str.length() + spacenum*2;//计算空格转换成%20之后的str长度
        int indexnew = newlength-1;//indexold为为把空格替换为%20后的str下标
        str.setLength(newlength);//使str的长度扩大到转换成%20之后的长度,防止下标越界
        for(;indexold>=0 && indexold<newlength;--indexold){
            if(str.charAt(indexold) == ' '){  //
                str.setCharAt(indexnew--, '0');
                str.setCharAt(indexnew--, '2');
                str.setCharAt(indexnew--, '%');
            }else{
                str.setCharAt(indexnew--, str.charAt(indexold));
            }
        }
        return str.toString();
    }
}
