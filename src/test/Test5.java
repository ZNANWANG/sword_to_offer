package test;

import java.util.ArrayList;
import java.util.Scanner;

public class Test5 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        str = " ";
        System.out.println(str.length());
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            System.out.println(c);
            System.out.println(String.valueOf(c).trim().equals(""));
        }
    }
}
